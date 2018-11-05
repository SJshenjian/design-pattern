package jdk.INio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Demonstrate gathering write using many buffers.
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/5
 */
public class Gather {

    private static String newLine = System.getProperty("line.separator");
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("E:\\Project\\java\\design-pattern\\src\\main\\resources\\jdk\\INio\\gather.txt");

        ByteBuffer[] buffers = fillBuffer(5);

        GatheringByteChannel gatheringByteChannel = outputStream.getChannel();

        // 等待gather写操作完成
        while (gatheringByteChannel.write(buffers) > 0) {

        }
        outputStream.close();

        System.out.println("perfect,it's done");
    }

    /**
     * 模拟发散的缓冲区
     *
     * @param number 缓冲区个数
     * @return
     */
    private static ByteBuffer[] fillBuffer(int number) {
        List<ByteBuffer> buffers = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            buffers.add(randomBuffer(stringOne, " "));
            buffers.add(randomBuffer(stringTwo, " "));
            buffers.add(randomBuffer(stringThree, newLine));
        }

        ByteBuffer[] byteBuffers = new ByteBuffer[buffers.size()];
        buffers.toArray(byteBuffers);
        return byteBuffers;
    }

    private static ByteBuffer randomBuffer(String[] strings, String suffix) {
        String string = strings[random.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buffer = ByteBuffer.allocate(total);

        buffer.put(string.getBytes());
        buffer.flip();

        return buffer;
    }

    private static String[] stringOne = {"Aggregate", "Enable", "Leverage", "Facilitate", "Reinvent", "Harness"};

    private static String[] stringTwo = {"cross-platform", "best-of-breed", "frictionless", "ubiquitous", "extensible",
            "compelling", "mission-critical", "collaborative", "integrated"};

    private static String[] stringThree = {"methodologies", "platforms", "schemas", "paradigms", "web services", "infrastructures"};
}
