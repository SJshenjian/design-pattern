package jdk.INio.buffer;

import java.nio.CharBuffer;

/**
 * 填充释放缓冲区
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/2
 */
public class BufferFillDrain {

    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(500);
        while (fillBuffer(charBuffer)) {
            charBuffer.flip();
            drainBuffer(charBuffer);
            charBuffer.clear();
        }
    }

    private static void drainBuffer(CharBuffer charBuffer) {
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }
    }

    private static boolean fillBuffer(CharBuffer charBuffer) {
        if (index >= strings.length) {
            return false;
        }

        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            charBuffer.put(string.charAt(i));
        }

        return true;
    }

    private static int index = 0;

    private static String[] strings = {"A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-)
            "Help Me! Help Me!",};
}
