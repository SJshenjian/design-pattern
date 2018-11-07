package jdk.INio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * Test behavior of Memory mapped buffer types. Create a file, write
 * some data to it, then create three different types of mappings
 * to it. Observe the effects of changes through the buffer APIs
 * and updating the file directly. The data spans page boundaries
 * to illustrate the page-oriented nature of Copy-On-Write mappings.
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/7
 */
public class MapFile {

    public static void main(String[] args) throws IOException {
        File tempFile = File.createTempFile("mapFile", null);
        RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "rw");
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put("This is the file content".getBytes("UTF-8"));
        buffer.flip();

        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.write(buffer, 0);

        // Put something else in the file, starting at location 8192.
        // 8192 is 8 KB, almost certainly a different memory/FS page.
        // This may cause a file hole, depending on the
        // filesystem page size.
        buffer.clear();
        buffer.put("This is more file content".getBytes("UTF-8"));
        buffer.flip();
        fileChannel.write(buffer, 8192);

        MappedByteBuffer readOnlyBuffer = fileChannel.map(MapMode.READ_ONLY, 0, fileChannel.size());
        MappedByteBuffer readWriteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, fileChannel.size());
        MappedByteBuffer privateBuffer = fileChannel.map(MapMode.PRIVATE, 0, fileChannel.size());

        System.out.println ("Begin");
        showBuffers (readOnlyBuffer, readWriteBuffer, privateBuffer);

        privateBuffer.position(8);
        privateBuffer.put("COW".getBytes("UTF-8"));
        System.out.println ("Change to COW buffer");
        showBuffers (readOnlyBuffer, readWriteBuffer, privateBuffer);

        readWriteBuffer.position(18);
        readWriteBuffer.put(" R/W ".getBytes("UTF-8"));
        readWriteBuffer.position(8194);
        readWriteBuffer.put(" R/W ".getBytes("UTF-8"));
        readWriteBuffer.force();
        System.out.println ("Change to R/W buffer");
        showBuffers (readOnlyBuffer, readWriteBuffer, privateBuffer);

        buffer.clear();
        buffer.put("Channel Write".getBytes("UTF-8"));
        buffer.flip();
        fileChannel.write(buffer, 0);
        buffer.rewind();
        fileChannel.write(buffer, 8202);
        System.out.println ("Write on channel");
        showBuffers (readOnlyBuffer, readWriteBuffer, privateBuffer);

        privateBuffer.position(8207);
        privateBuffer.put(" COW ".getBytes("UTF-8"));
        System.out.println ("Second change to COW buffer");
        showBuffers (readOnlyBuffer, readWriteBuffer, privateBuffer);

        readWriteBuffer.position(0);
        readWriteBuffer.put(" R/W2 ".getBytes("UTF-8"));
        readWriteBuffer.position(8210);
        readWriteBuffer.put(" R/W2 ".getBytes("UTF-8"));
        readWriteBuffer.force();
        System.out.println ("Second change to R/W buffer");
        showBuffers (readOnlyBuffer, readWriteBuffer, privateBuffer);

        randomAccessFile.close();
    }

    private static void showBuffers(MappedByteBuffer readOnlyBuffer, MappedByteBuffer readWriteBuffer, MappedByteBuffer privateBuffer) {
        dumpBuffer ("R/O", readOnlyBuffer);
        dumpBuffer ("R/W", readWriteBuffer);
        dumpBuffer ("COW", privateBuffer);
        System.out.println ("");
    }

    private static void dumpBuffer(String prefix, MappedByteBuffer buffer) {
        System.out.print (prefix + ": '");
        int nulls = 0;
        int limit = buffer.limit( );
        for (int i = 0; i < limit; i++) {
            char c = (char) buffer.get (i);
            if (c == '\u0000') {
                nulls++;
                continue;
            }
            if (nulls != 0) {
                System.out.print ("|[" + nulls + " nulls]|");
                nulls = 0;
            }
            System.out.print (c);
        }
        System.out.println ("'");
    }
}
