package jdk.INio;

import java.io.*;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;

/**
 * 使用映射文件和gathering写操作编写Http回复
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/7
 */
public class MappedHttp {

    private static final String INPUT_FILE = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "jdk" + File.separator + "INio" + File.separator + "mappedHttp.txt" ;
    private static final String OUTPUT_FILE = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "jdk" + File.separator + "INio" + File.separator + "mappedHttp.out" ;
    private static final String LINE_SEP = "\r\n";
    private static final String SERVER_ID = "Server: Ronsoft Dummy Server";
    private static final String HTTP_HEADER =
            "HTTP/1.0 200 OK" + LINE_SEP + SERVER_ID + LINE_SEP;
    private static final String HTTP_404_HEADER =
            "HTTP/1.0 404 Not Found" + LINE_SEP + SERVER_ID + LINE_SEP;
    private static final String MSG_404 = "Could not open file: ";

    public static void main(String[] args) throws IOException {
        ByteBuffer header = ByteBuffer.wrap(HTTP_HEADER.getBytes("UTF-8"));
        ByteBuffer msg = ByteBuffer.allocate(128);

        ByteBuffer[] gather = {header, msg, null};

        FileChannel  fileChannel = null;
        long contentLength = -1;
        String contentType = "unknown/unknown";
        try (
             FileInputStream inputStream = new FileInputStream(INPUT_FILE);
        ){
             fileChannel = inputStream.getChannel();
             MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

             gather[2] = mappedByteBuffer;

             contentLength = fileChannel.size();
             contentType = URLConnection.guessContentTypeFromName(INPUT_FILE);
        } catch (IOException e) {
            gather[0] = ByteBuffer.wrap(HTTP_404_HEADER.getBytes("UTF-8"));

            ByteBuffer failBody= ByteBuffer.allocate(128);
            String message = MSG_404 + e + LINE_SEP;
            failBody.put(message.getBytes("UTF-8"));
            failBody.flip();

            gather[2] = failBody;

            contentLength = message.length();
            contentType = "text/plain";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("CONTENT-LENGTH: " + contentLength).append(LINE_SEP)
            .append("CONTENT-TYPE: " + contentType).append(LINE_SEP);

        msg.put(builder.toString().getBytes("UTF-8"));
        msg.flip();
        try (
            FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        ){
            GatheringByteChannel gatheringByteChannel = outputStream.getChannel();

            while (gatheringByteChannel.write(gather) > 0) {

            }
            System.out.println("perfect,it's done");
        }
    }
}
