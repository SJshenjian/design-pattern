package jdk.INio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/9
 */
public class ISelector {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannelOne = SocketChannel.open();
        SocketChannel socketChannelTwo = SocketChannel.open();
        SocketChannel socketChannelThree = SocketChannel.open();

        socketChannelOne.configureBlocking(false);
        socketChannelTwo.configureBlocking(false);
        socketChannelThree.configureBlocking(false);

        socketChannelOne.register(selector, SelectionKey.OP_READ);
        socketChannelTwo.register(selector, SelectionKey.OP_WRITE);
        socketChannelThree.register(selector,  SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        int readyCount = selector.select(1000);
        System.out.println(readyCount);
    }
}
