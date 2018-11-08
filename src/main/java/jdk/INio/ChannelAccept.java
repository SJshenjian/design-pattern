package jdk.INio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 非阻塞accept()使用示例
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/8
 */
public class ChannelAccept {

    public static void main(String[] args) throws IOException, InterruptedException {
        String greet = "hello, my girl";
        ByteBuffer buffer = ByteBuffer.wrap(greet.getBytes("UTF-8"));

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(12345));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                System.out.println("眯一会，就两秒。。。");
                Thread.sleep(2000);
            } else {
                System.out.println("Incoming connection from: " + socketChannel.socket().getRemoteSocketAddress());
                buffer.rewind();
                socketChannel.write(buffer);
                socketChannel.close();
            }
        }
    }
}
