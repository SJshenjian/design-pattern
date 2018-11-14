package jdk.INio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 使用select()为多个通道提供服务
 * 选择过程进行扩展：使用线程池为通道提供服务
 *
 * @author Jian Shen
 * @version V1.1
 * @date 2018/11/13
 */
public class SelectSockets {

    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        new SelectSockets().go();
    }

    protected void go() throws IOException {
        System.out.println("Listening on port " + PORT);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int ready = selector.select(10000);
            if (ready == 0) {
                continue;
            }

            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    // 有新连接就注册进选择器
                    register(selector, socketChannel, SelectionKey.OP_READ);
                    sayHello(socketChannel);
                }

                if (selectionKey.isReadable()) {
                    readDataFromChannel(selectionKey);
                }
                iterator.remove();
            }
        }
    }

    private void register(Selector selector, SelectableChannel channel, int ops) throws IOException {
        if (channel == null) {
            return ;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    private ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

    protected void readDataFromChannel(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        byteBuffer.clear();
        while(socketChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            // 仅作示例，该操作又写入socketChannel，请根据实际用处进行编写
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
        socketChannel.close();
    }

    private void sayHello(SocketChannel socketChannel) throws IOException {
        byteBuffer.clear();
        byteBuffer.put("Hello World".getBytes("UTF-8"));
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }
}
