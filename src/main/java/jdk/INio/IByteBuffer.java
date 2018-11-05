package jdk.INio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/8/28
 * @see https://blog.csdn.net/jameshadoop/article/details/51671953
 */
public class IByteBuffer {

    public static void main(String[] args) {
        System.out.println("--------TEST ALLOCATE---------");
        System.out.println("before allocate : " + Runtime.getRuntime().freeMemory());

        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(102400);
        System.out.println("buffer = " + buffer);

        System.out.println("after allocate : " + Runtime.getRuntime().freeMemory());

        java.nio.ByteBuffer directBuffer = java.nio.ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);

        System.out.println("after allocate : " + Runtime.getRuntime().freeMemory());

        System.out.println("----------TEST WRAP--------------");
        byte[] bytes = new byte[32];
        buffer = java.nio.ByteBuffer.wrap(bytes);
        System.out.println("buffer = " + buffer);

        buffer = java.nio.ByteBuffer.wrap(bytes, 10, 10);
        System.out.println("buffer = " + buffer);

        System.out.println("----------TEST RESET-----------------");
        buffer.clear();
        buffer.position(5);
        buffer.mark();
        buffer.position(10);
        System.out.println("before reset : " + buffer);
        buffer.reset();
        System.out.println("after reset : " + buffer);


        System.out.println("-----------TEST REWIND-----------------");
        buffer.clear();
        buffer.position(10);
        buffer.limit(15);
        System.out.println("before rewind : " + buffer);
        buffer.rewind();
        System.out.println("after rewind : " + buffer);

        System.out.println("---------TEST COMPACT--------------");
        buffer.clear();
        buffer.put("abcd".getBytes());
        System.out.println("before compact : " + buffer);
        System.out.println(new String(buffer.array()));
        buffer.flip();
        System.out.println("after flip : " + buffer);
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println("after three get : " + buffer);
        System.out.println("\t" + new String(buffer.array()));

        buffer.compact();
        System.out.println("after compact : " + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("-----------TEST GET----------------");
        buffer = ByteBuffer.allocate(32);
        buffer.put((byte)'a').put((byte)'b').put((byte)'c').put((byte)'d').put((byte)'e').put((byte)'f');
        System.out.println("before flip : " + buffer);
        System.out.println(new String(buffer.array()));

        buffer.flip(); // 转化为读模式

        System.out.println("before get : " + buffer);
        System.out.println((char)buffer.get()); // 影响position的值
        System.out.println("after get : " + buffer);

        System.out.println((char)buffer.get(2)); // 不影响position的值
        System.out.println("after get(index) : " + buffer);

        byte[] dst = new byte[10];
        buffer.get(dst, 0, 2); // 影响position的值
        System.out.println("after get(dst, 0, 2) : " + buffer);
        System.out.println("\t dst : " + new String(dst));
        System.out.println("buffer now is : " + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("-------------TEST PUT----------------");
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        System.out.println("before put(byte) : " + byteBuffer);
        byteBuffer.put((byte)'z');
        System.out.println("after put(byte) : " + byteBuffer);
        byteBuffer.put(3, (byte)'c');
        System.out.println("after put(2, byte) : " + byteBuffer);
        System.out.println("\t " + new String(byteBuffer.array()));
        // 这里的buffer是 abcdef[pos=3 lim=6 cap=32]
        byteBuffer.put(buffer);
        System.out.println("after put(buffer) : " + byteBuffer);
        System.out.println("\t " + new String(byteBuffer.array()));
    }
}
