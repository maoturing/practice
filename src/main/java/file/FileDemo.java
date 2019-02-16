package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 *
 * 参考文档: https://blog.csdn.net/Lirx_Tech/article/details/51425364
 */
public class FileDemo {
    // 需要监听的文件目录（只能监听目录）
    private static final String path = "d:/test/";

    public static void main(String[] args) throws Exception {
        testResourceChanged();
        Thread.sleep(1000);

        // 新建和删除文件,测试能否监听的到文件变化
        File file = new File(path + "a1.txt");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
    }

    /**
     * 必须在main方法中执行,因为监听线程是非守护线程,在@Test中执行不起作用
     * @see thread.DaemonDemo.WorkerThread
     */
    public static void testResourceChanged() throws IOException, InterruptedException {
        /**
         *  获取当前OS平台下的文件系统监控器
         *  @see sun.nio.fs.WindowsWatchService
         */
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path p = Paths.get(path);
        // 注册监听器
        p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);

        Thread thread = new Thread(() -> {
            try {
                while(true){

                    /**
                     * 阻塞方式: 消费文件更改事件,不会一直死循环
                     * 使用debug可知,在这行代码会阻塞,一直等待文件变化才会继续向下执行
                     * watchService.take()底层是从阻塞队列 pendingKeys 中获取数据
                     *
                     * @see sun.nio.fs.AbstractWatchService#pendingKeys
                     */
                    WatchKey watchKey = watchService.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    for(WatchEvent<?> watchEvent : watchEvents){
                        //TODO 根据事件类型采取不同的操作
                        System.out.printf("[%s] 文件发生了 [%s] 事件。%n", path+"/"+watchEvent.context(), watchEvent.kind());
                    }
                    // 作用?
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 设置为用户线程,防止主线程结束后监听线程也随之退出
        thread.setDaemon(false);
        thread.start();
        Thread.sleep(1000);

        // ? 增加jvm关闭的钩子来关闭监听
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (Exception e) {
            }
        }));
    }
/*
    public static void testResourceChanged() throws IOException, InterruptedException, ExecutionException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path p = Paths.get(path);
        // 注册监听器
        p.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);

        // 文件监控线程
      Executors.newCachedThreadPool().submit(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        // 阻塞方式，消费文件更改事件
                        WatchKey watchKey = watchService.take();
                        List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                        for (WatchEvent<?> watchEvent : watchEvents) {
                            System.out.printf("[%s]文件发生了[%s]事件。%n", watchEvent.context(), watchEvent.kind());
                        }

                        watchKey.reset();
                    }
                } catch(Exception e) {}
            }
        });

        Thread.sleep(1000);
        File file = new File(path + "testDirChanged.xml");
        file.createNewFile();   // 新建文件
        file.delete();  // 删除文件
    }*/
}
