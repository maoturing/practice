package concurrency.volatiles;

import java.util.concurrent.TimeUnit;

/**
 *
 * volatile变量被修改时,能使cpu缓存中该变量的值失效
 * updater线程定时修改init_value的值,reader线程读取init_value值并缓存,由于一直使用的缓存值,所以始终不变,Reader线程陷入死循环
 * init_value添加volatile修饰,updater线程定时修改init_value的值后就能使其他线程的缓存失效,reader线程从主存重新读取init_value的值并输出,然后结束
 */
public class VolatileDemo {
    /**
     * init_value的最大值
     */
    final static int MAX = 5;
//    static int init_value = 0;
    volatile static int init_value = 0;

    public static void main(String[] args) {
        /**
         * 启动一个Reader线程，当发现local_value和init_value不同时，则输出init_value被修改的信息
         */
        new Thread(() ->  {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {     // 虽然updater线程修改了init_value的值,但是当前线程一直使用的是缓存中的init_value值, 会一直陷入死循环
                    System.out.printf("Current thread is [%s] and the init_value is updated to [%d]\n", Thread.currentThread().getName(), init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        /**
         * 启动一个Updater线程，主要用于对init_value的修改，当localValue >= 5 时，则退出生命周期
         */
        new Thread(() ->
        {
            int localValue = init_value;
            while (localValue < MAX) {
                System.out.printf("Current thread is [%s] and the init_value will be changed to [%d]\n", Thread.currentThread().getName(), ++localValue);
                init_value = localValue;
                try {
                    // 短暂休眠，目的是为了使Reader线程能够来得及输出变化内容
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}