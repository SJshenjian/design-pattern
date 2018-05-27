package concurrent.taskExecutor;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/27
 */
public class Renderer {
    private ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    public void renderPage(CharSequence source) {
        List<ImageInfo> info = scanImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
        for (final ImageInfo imageInfo : info) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        
        renderText(source);

        try {
            for (int i = 0; i < info.size(); i++) {
                Future<ImageData> future = completionService.take();
                ImageData imageData = future.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void renderImage(ImageData imageData) {
    }

    private void renderText(CharSequence source) {
        return ;
    }

    private List<ImageInfo> scanImageInfo(CharSequence source) {
        return null;
    }

    private class ImageInfo {
        public ImageData downloadImage() {
            // TODO 图片下载
            return null;
        }
    }

    private class ImageData {

    }
}
