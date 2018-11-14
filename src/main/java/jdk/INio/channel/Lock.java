package jdk.INio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/6
 */
public class Lock {

    private static final String FILENAME = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "jdk" + File.separator + "INio" + File.separator + "lock.txt" ;

    private static final int SIZEOF_INT = 4;
    private static final int INDEX_START = 0;
    private static final int INDEX_COUNT = 10;
    private static final int SIZE = INDEX_START + SIZEOF_INT * INDEX_COUNT;

    private Random random = new Random();
    private ByteBuffer buffer = ByteBuffer.allocate(SIZE);
    private IntBuffer intBuffer = buffer.asIntBuffer();

    public static void main(String[] args) {
        System.out.println("请输入参数 -r 或者 -rw");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        Lock lock = new Lock();
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile(FILENAME, "-rw".equalsIgnoreCase(command) ? "rw" : "r");
                FileChannel fileChannel = randomAccessFile.getChannel();
        ){
            if ("-rw".equalsIgnoreCase(command)) {
                lock.update(fileChannel);
            } else {
                lock.query(fileChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulate a series of read-only queries while
     * holding a shared lock on the index area
     *
     * @param fileChannel
     * @throws IOException
     */
    private void query(FileChannel fileChannel) throws IOException, InterruptedException {
        while (true) {
            System.out.println("trying for shared lock...");

            FileLock fileLock = fileChannel.lock(INDEX_START, SIZE, true);

            int reps = random.nextInt(60) + 20;
            for (int i = 0; i < reps; i++) {
                int n = random.nextInt(INDEX_COUNT);
                int position = INDEX_START + (n * SIZEOF_INT);
                buffer.clear();

                fileChannel.read(buffer, position);
                int value = intBuffer.get(n);

                System.out.println("Index entry " + n + "=" + value);
                Thread.sleep(100);
            }
            fileLock.release();
            System.out.println("<sleeping>");
            Thread.sleep(random.nextInt(3000) + 500);
        }
    }

    private void update(FileChannel fileChannel) throws IOException, InterruptedException {
        int index = 1;
        while (true) {
            System.out.println("trying for exclusive lock...");

            FileLock fileLock = fileChannel.lock(INDEX_START, SIZE, false);

            intBuffer.clear();
            for (int i = 0; i < INDEX_COUNT; i++) {
                System.out.println(("Updating index " + i + "=" + (index++)));
                intBuffer.put(index);
                Thread.sleep(500);
            }
            buffer.clear();
            fileChannel.write(buffer, INDEX_START);

            fileLock.release();
            System.out.println("<sleeping>");
            Thread.sleep(random.nextInt(2000) + 500);
        }
    }
}
