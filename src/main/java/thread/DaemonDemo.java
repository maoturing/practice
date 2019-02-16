package thread;

import java.io.IOException;

/**
 *
 * 主线程(main)结束后:
 *       1. 用户线程(daemon为false)将会继续运行
 *       2. 如果没有用户线程，都是守护线程(daemon为true)的话，那么jvm结束
 * 守护线程与用户线程唯一的区别是：当用户线程退出时，JVM 会检查其他正在运行的线程，如果这些线程都是守护线程，那么 JVM 会正常退出操作，
 * 但是如果有用户线程还在运行，JVM 是不会执行退出操作的。

 * 当 JVM 退出时，所有仍然存在的守护线程都将被抛弃，既不会执行 finally 部分的代码，也不会执行 stack unwound 操作，JVM 会直接退出。
 * Thread.setDaemon()方法必须在线程启动前使用默认为false
 *
 * 垃圾回收线程就是一个经典的守护线程，当我们的程序中不再有任何运行的Thread,程序就不会再产生垃圾，垃圾回收器也就无事可做，所以当垃圾回收线程是JVM上仅剩的线程时，垃圾回收线程会自动离开。
 *
 * 参考文档: http://matt33.com/2018/07/07/java-daemon-thread/
 *          https://blog.csdn.net/xyls12345/article/details/26256693
 */
public class DaemonDemo {
    /**
     * 不能使用@Test方法测试
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        new WorkerThread().start();
        Thread.sleep(2000);
        System.out.println("Main Thread ending");
        System.in.read();
    }

    static class WorkerThread extends Thread {
        public WorkerThread() {
            // When false, (i.e. when it's a user thread), the Worker thread continues to run.
            // When true, (i.e. when it's a daemon thread), the Worker thread terminates when the main thread terminates.
            setDaemon(false);   // 默认为false
            System.out.println("isDaemon = " + isDaemon());
        }

        public void run() {
            int count = 0;
            while (true) {
                System.out.println("Hello from Worker " + count++);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }

    /**
     * 必须使用main方法测试,不能使用@Test测试
     */
    public static void testDamon() throws Exception {
        // 死循环线程,启动后永远不会结束
        Thread test = new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        });
        // 调试时可以设置为false，那么这个程序是个死循环，没有退出条件。设置为true，即可主线程结束，test线程也结束。
        test.setDaemon(false);
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
        Thread.sleep(2000);
        System.in.read();   // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
    }
}
