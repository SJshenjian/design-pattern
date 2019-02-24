package concurrent.module.windowSearch;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Jian Shen
 * @version V1.0
 * @date 2019/2/24
 */
public class Window {

    public static void main(String[] args) {
        new Window().startIndexing();
    }

    public void startIndexing() {
        BlockingQueue<File> fileQueue = new ArrayBlockingQueue<>(8);
        FileFilter fileFilter = (pathname) -> { return true;};

        String path =  "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "concurrent";
        File root = new File(path);

        new Thread(new FileCrawler(fileQueue, fileFilter, root)).start();

        for (int i = 0; i < 3; i++) {
            new Thread(new Indexer(fileQueue)).start();
        }
    }
}
