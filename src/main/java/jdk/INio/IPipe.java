package jdk.INio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

/**
 * 工作线程对一个管道进行写操作
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/9
 */
public class IPipe {
    private WritableByteChannel writableByteChannel;
    private ReadableByteChannel readableByteChannel;

    public static void main(String[] args){
        IPipe iPipe = new IPipe();
        iPipe.execute();
    }

    public void execute() {
        try {
            writableByteChannel = Channels.newChannel(System.out);
            readableByteChannel = this.doWorker();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            while (readableByteChannel.read(buffer) > 0) {
                buffer.flip();
                writableByteChannel.write(buffer);
                buffer.clear();
            }
            writableByteChannel.close();
            readableByteChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReadableByteChannel doWorker(){
        try {
            Pipe pipe = Pipe.open();
            Thread worker = new Thread(new Worker(pipe.sink()));
            worker.start();
            return pipe.source();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class Worker implements Runnable {
        private WritableByteChannel writableByteChannel;

        public Worker(WritableByteChannel writableByteChannel) {
            this.writableByteChannel = writableByteChannel;
        }

        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            try {
                for (int i = 0; i < 10; i++) {
                    doSomeWork(buffer);
                    while (writableByteChannel.write(buffer) > 0) {

                    }
                }
                writableByteChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String [] products = {
                "No good deed goes unpunished",
                "To be, or what?",
                "No matter where you go, there you are",
                "Just say \"Yo\"",
                "My karma ran over my dogma"};
        private Random random = new Random( );

        private void doSomeWork (ByteBuffer buffer) {
            int product = random.nextInt (products.length);
            buffer.clear( );
            buffer.put (products [product].getBytes( ));
            buffer.put ("\r\n".getBytes( ));
            buffer.flip( );
         }
    }
}
