package concurrency.thread;


import org.junit.Test;

/**
 * 中断线程,当线程处于wait,sleep,join时,那么线程将立即退出被阻塞状态，调用interrupt会抛出InterruptedException
 *
 * Thread.interrupt 的作用不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己写代码处理。
 * 一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。
 *
 * 具体来说，当对一个线程，调用 interrupt() 时，
 * ① 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。仅此而已。
 * ② 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。
 * 被设置中断标志的线程将继续正常运行，不受影响。
 *
 * interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。也就是说，一个线程如果有被中断的需求，那么就可以这样做。
 * ① 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
 * ② 在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）
 * Thread thread = new Thread(() -> {
 *     while (!Thread.interrupted()) {
 *         // do more work.
 *     }
 * });
 * thread.start();
 *
 * // 一段时间以后
 * thread.interrupt();
 *
 *
 * 参考文档: 汪文君多线程
 * @author mao  2019/4/25 10:51
 */
public class InterruptDemo {
    private static final Object MONITOR = new Object();

    /**
     * 不会中断线程, 会修改标志位
     */
    @Test
    public void testInterrupt() {
        Runnable r = () -> {
            while (true) {
                System.out.println("线程正在运行..");
            }
        };
        test(r);
    }

    /**
     * 不会中断线程,但是会退出sleep造成的阻塞状态, 并抛出InterruptedException
     */
    @Test
    public void testSleep() {
        Runnable r = () -> {
            while (true) {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    System.out.println("收到中断信号Interrupt, 抛出异常, 结束线程");
                    e.printStackTrace();
                }
            }
        };
        test(r);
    }

    @Test
    public void testWait() {
        Runnable r = () -> {
            while (true) {
                synchronized (MONITOR) {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        System.out.println("收到中断信号Interrupt, 抛出异常, 结束线程");
                        System.out.println("Interrupted: " + Thread.interrupted()); // Thread.interrupted()与t.isInterrupted()作用相同,应用于这种线程内部查看线程状态的情况, 但是Thread.interrupted()会修改标志位
                        e.printStackTrace();
                    }
                }
            }
        };
        test(r);
    }

    public void test(Runnable r) {
        Thread t = new Thread(r);
        t.start();
        try {
            Thread.sleep(100L);    // 等待线程启动完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.isInterrupted());
        t.interrupt();  // 中断线程
        System.out.println(t.isInterrupted());
        try {
            // 必须加这一行,因为Junit中主线程结束后会直接退出, 具体参考https://www.jianshu.com/p/6f2db348e44d
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
