package concurrent.module.windowSearch;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 索引建立
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2019/2/24
 */
public class Indexer implements Runnable{

    private final BlockingQueue<File> fileQueue;

    public Indexer(BlockingQueue<File> fileQueue) {
        this.fileQueue = fileQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                indexFile(fileQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void indexFile(File file) {
        System.out.println(file.getName() + "建立索引成功");
    }
}
