package concurrent.module.windowSearch;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 桌面搜索应用中的生产者任务和消费者任务
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2019/2/24
 */
public class FileCrawler implements Runnable{

    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void crawl(File root) throws InterruptedException {
        File[] files = root.listFiles(fileFilter);
        if (files != null) {
            for (File file: files) {
                if (file.isDirectory()) {
                    crawl(file);
                } else if (!alreadyIndexed(file)){
                    queue.put(file);
                }
            }
        }
    }

    private boolean alreadyIndexed(File file) {
        return false;
    }
}
