package jdk.INio.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * 通道间复制数据
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/5
 */
public class ChannelCopy {

    public static void main(String[] args) throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dst = Channels.newChannel(System.out);
        channelCopyOne(source, dst);
        //channelCopyTwo(source, dst);
        source.close();
        dst.close();
    }

    /**
     * Channel copy method 1. This method copies data from the src
     * channel and writes it to the dest channel until EOF on src.
     * This implementation makes use of compact( ) on the temp buffer
     * to pack down the data if the buffer wasn't fully drained. This
     * may result in data copying, but minimizes system calls. It also
     * requires a cleanup loop to make sure all the data gets sent.
     */
    private static void channelCopyOne(ReadableByteChannel source, WritableByteChannel dst) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (source.read(buffer) != -1) {
            // Prepare the buffer to be drained
            buffer.flip();
            // Write to the channel; may block
            dst.write(buffer);
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear( )
            buffer.compact();
        }

        // EOF will leave buffer in fill state
        buffer.flip();
        // Make sure that the buffer is fully drained
        while (buffer.hasRemaining()) {
            dst.write(buffer);
        }
    }

    /**
     * Channel copy method 2. This method performs the same copy, but
     * assures the temp buffer is empty before reading more data. This
     * never requires data copying but may result in more systems calls.
     * No post-loop cleanup is needed because the buffer will be empty
     * when the loop is exited.
     */
    private static void channelCopyTwo(ReadableByteChannel source, WritableByteChannel dst) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (source.read(buffer) != -1) {
            // Prepare the buffer to be drained
            buffer.flip();
            // Make sure that the buffer was fully drained
            while (buffer.hasRemaining()) {
                dst.write(buffer);
            }
            // Make the buffer empty, ready for filling
            buffer.clear();
        }
    }
}