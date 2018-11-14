package jdk.INio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.net.InetSocketAddress;
import java.util.*;

/**
 * 使用DatagramChannel的时间服务客户端
 * <p>
 * Request time service, per RFC 868. RFC 868
 * (http://www.ietf.org/rfc/rfc0868.txt) is a very simple time protocol
 * whereby one system can request the current time from another system.
 * Most Linux, BSD and Solaris systems provide RFC 868 time service
 * on port 37. This simple program will inter-operate with those.
 * The National Institute of Standards and Technology (NIST) operates
 * a public time server at time.nist.gov.
 * <p>
 * The RFC 868 protocol specifies a 32 bit unsigned value be sent,
 * representing the number of seconds since Jan 1, 1900. The Java
 * epoch begins on Jan 1, 1970 (same as unix) so an adjustment is
 * made by adding or subtracting 2,208,988,800 as appropriate. To
 * avoid shifting and masking, a four-byte slice of an
 * eight-byte buffer is used to send/recieve. But getLong( )
 * is done on the full eight bytes to get a long value.
 * <p>
 * When run, this program will issue time requests to each hostname
 * given on the command line, then enter a loop to receive packets.
 * Note that some requests or replies may be lost, which means
 * this code could block forever.
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/8
 */
public class TimeClient {
    private static final int DEFAULT_TIME_PORT = 37;
    private static final long DIFF_1900 = 2208988800L;
    protected int port = DEFAULT_TIME_PORT;
    protected List<InetSocketAddress> remoteHosts = new ArrayList<>();
    protected DatagramChannel channel;

    private static final List<String> ipAndPorts = new ArrayList<>();

    static {
        // 需要选择为可用的NTP时间服务器
        ipAndPorts.add("127.0.0.1:37");
        ipAndPorts.add("127.0.0.1:7777");
        ipAndPorts.add("127.0.0.1:8888");
        ipAndPorts.add("127.0.0.1:9999");
    }

    public TimeClient() throws IOException {
        for (int i = 0; i < ipAndPorts.size(); i++) {
            String[] ipPort = ipAndPorts.get(i).split(":");
            String ip = ipPort[0];
            int port = Integer.parseInt(ipPort[1]);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);
            if (inetSocketAddress == null) {
                System.out.println("Cannot resolve address: " + ipPort);
                continue;
            }
            remoteHosts.add(inetSocketAddress);
        }
        this.channel = DatagramChannel.open();
        channel.configureBlocking(false);
    }

    public static void main(String[] argv) throws Exception {
        TimeClient client = new TimeClient();
        client.sendRequest();
        client.getReply();
    }

    private void sendRequest() throws IOException {
        for (InetSocketAddress address : remoteHosts) {
            System.out.println("Requesting time from " + address.getHostName() + ":" + address.getPort());
            ByteBuffer buffer = ByteBuffer.allocate(1);
            buffer.clear().flip();
            System.out.println("");
            System.out.println("Waiting for replies...");
            channel.send(buffer, address);
        }
    }

    private void getReply() throws IOException, InterruptedException {
        ByteBuffer longBuffer = ByteBuffer.allocate(8);
        longBuffer.order(ByteOrder.BIG_ENDIAN);
        longBuffer.putLong(0, 0);
        longBuffer.position(4);

        ByteBuffer buffer = longBuffer.slice();

        System.out.println("Waiting for replies...");
        int replies = 0;
        int expect = remoteHosts.size();

        while (true) {
            buffer.clear();
            InetSocketAddress inetSocketAddress = (InetSocketAddress) channel.receive(buffer);

            if (inetSocketAddress != null) {
                replies++;
                buffer.flip();
                printTime(longBuffer.getLong(0), inetSocketAddress);
            }

            if (replies == expect) {
                System.out.println("All packets answered");
                break;
            }
            Thread.sleep(2000);
            System.out.println("Received " + replies + " of " + expect + " replies");
        }
    }

    private void printTime(long remote1900, InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress == null) {
            return;
        }
        // local time as seconds since Jan 1, 1970
        long local = System.currentTimeMillis() / 1000;
        // remote time as seconds since Jan 1, 1970
        long remote = remote1900 - DIFF_1900;
        Date remoteDate = new Date(remote * 1000);
        Date localDate = new Date(local * 1000);
        long skew = remote - local;
        System.out.println("Reply from " + inetSocketAddress.getHostName() + ":" + inetSocketAddress.getPort());
        System.out.println("there: " + remoteDate);
        System.out.println("here: " + localDate);
        System.out.print("skew: ");
        if (skew == 0) {
            System.out.println("none");
        } else if (skew > 0) {
            System.out.println(skew + " seconds ahead");
        } else {
            System.out.println((-skew) + " seconds behind");
        }
    }
}
