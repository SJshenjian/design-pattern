package concurrent.cancelAndShutdown;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/29
 */
public class LogService {
    private final ExecutorService service = Executors.newSingleThreadExecutor();
    private final PrintWriter writer = new PrintWriter("c://temp/log.log");

    public LogService() throws FileNotFoundException {
    }

    public void start() {

    }

    public void stop() {
        try {
            service.shutdown();
            service.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    public void log(String msg) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                writer.write(msg);
            }
        });
    }
}
