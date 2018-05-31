package concurrent.cancelAndShutdown;

import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/29
 */
public class SimpleLogService {
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
    private final PrintWriter writer;
    private final LogThread logThread = new LogThread();
    private boolean isShutdown = false;
    private int reservations;

    public SimpleLogService(PrintWriter writer) {
        this.writer = writer;
    }

    public void start() {
        logThread.start();
    }

    public void stop() {
        synchronized(this) {
            isShutdown = true;
        }
        logThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized(this) {
            if (isShutdown) {
                throw new IllegalArgumentException();
            }
            reservations++;
        }
        queue.put(msg);
    }

    private class LogThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (SimpleLogService.this) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                    }
                    try {
                        String msg = queue.take();
                        synchronized (SimpleLogService.this) {
                            reservations--;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                writer.close();
            }
        }
    }
}
