package jdk.INio.selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用线程池为通道提供服务
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/14
 */
public class SelectSocketsThreadPool extends SelectSockets {

    private static final int THREAD_POOL_SIZE = 5;
    private ThreadPool threadPool = new ThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) throws IOException {
        new SelectSocketsThreadPool().go();
    }

    @Override
    protected void readDataFromChannel(SelectionKey selectionKey) {
        WorkerThread workerThread = threadPool.getWorker();
        if (workerThread == null) {
            // No threads available. Do nothing. The selection loop will keep calling this method
            // until a thread becomes available. This design could be improved.
            return ;
        }
        workerThread.serviceChannel(selectionKey);
    }


    private class ThreadPool {
        private List<WorkerThread> workers = new ArrayList<>();

        ThreadPool(int poolSize) {
            for (int i = 0; i < poolSize; i++) {
                WorkerThread workerThread = new WorkerThread(this);
                workerThread.setName("Worker " + (i + 1));
                workerThread.start();
                workers.add(workerThread);
            }
        }

        WorkerThread getWorker() {
            synchronized (workers) {
                if (workers.size() > 0) {
                    return workers.remove(0);
                }
                return null;
            }
        }

        void returnWorker(WorkerThread workerThread) {
            synchronized (workers) {
                workers.add(workerThread);
            }
        }
    }

    private class WorkerThread extends Thread {
        private ThreadPool threadPool;
        private SelectionKey selectionKey;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        public WorkerThread() {

        }

        public WorkerThread(ThreadPool threadPool) {
            this.threadPool = threadPool;
        }

        @Override
        public synchronized void run() {
            System.out.println(this.getName() + "is ready....");

            // 始终保持待工作状态
            while (true) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
                if (selectionKey == null) {
                    return;
                }

                try {
                    drainChannel(selectionKey);
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        selectionKey.channel().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    selectionKey.selector().wakeup();
                }
                selectionKey = null;
                // 归还线程至线程池，请记住，该线程仍然处于待工作状态
                threadPool.returnWorker(this);
            }
        }

        void drainChannel(SelectionKey selectionKey) throws IOException {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            byteBuffer.clear();
            while(socketChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                // 仅作示例，该操作又写入socketChannel，请根据实际用处进行编写
                while (byteBuffer.hasRemaining()) {
                    socketChannel.write(byteBuffer);
                }
                byteBuffer.clear();
            }
            socketChannel.close();
            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);
            selectionKey.selector().wakeup();
        }

        synchronized void serviceChannel(SelectionKey selectionKey) {
            this.selectionKey = selectionKey;
            selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_READ));
            this.notify();
        }
    }
}
