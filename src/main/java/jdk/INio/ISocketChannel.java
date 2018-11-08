package jdk.INio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/8
 */
public class ISocketChannel {

    public static void main(String[] args) throws IOException, InterruptedException {
        // ServerSocketChannel
        ServerSocket serverSocket = null;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Object lockObj = serverSocketChannel.blockingLock();

        synchronized (lockObj) {
            boolean status = serverSocketChannel.isBlocking();
            serverSocketChannel.configureBlocking(false);
            serverSocket = serverSocketChannel.socket();
            serverSocketChannel.configureBlocking(status);
        }
        if (serverSocket != null) {
            serverSocket.bind(new InetSocketAddress(12345));
        }
        serverSocket.close();
    }
}
