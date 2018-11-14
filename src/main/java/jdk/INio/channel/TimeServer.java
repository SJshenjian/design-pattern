package jdk.INio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Provide RFC 868 time service (http://www.ietf.org/rfc/rfc0868.txt).
 * This code implements an RFC 868 listener to provide time
 * service. The defined port for time service is 37. On most
 * unix systems, root privilege is required to bind to ports
 * below 1024. You can either run this code as root or
 * provide another port number on the command line. Use
 * "-p port#" with TimeClient if you choose an alternate port.
 *
 * Note: The familiar rdate command on unix will probably not work
 * with this server. Most versions of rdate use TCP rather than UDP
 * to request the time.
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/8
 */
public class TimeServer {

    private static final int DEFAULT_PORT = 37;
    private static final long DIFF_1990 = 2208988800L;
    private DatagramChannel datagramChannel;

    public TimeServer() throws IOException {
        this.datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(DEFAULT_PORT));
        datagramChannel.configureBlocking(false);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        TimeServer timeServer = new TimeServer();
        timeServer.listen();
    }

    private void listen() throws IOException, InterruptedException {
        ByteBuffer longBuffer = ByteBuffer.allocate(8);
        longBuffer.position(4);
        ByteBuffer buffer = longBuffer.slice();

        while (true) {
            buffer.clear();
            SocketAddress socketAddress = datagramChannel.receive(buffer);
            if (socketAddress == null) {
                System.out.println("尚未连接，小憩2秒。。。");
                Thread.sleep(2000);
                continue;
            }
            System.out.println("获得连接" + socketAddress);

            buffer.clear();
            longBuffer.putLong(0, (System.currentTimeMillis() / 1000) + DIFF_1990);
            datagramChannel.send(buffer, socketAddress);
        }
    }
}
