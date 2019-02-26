package concurrency;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 *
 * AtomicInteger使用CAS算法保证线程安全
 * 参考文档: https://blog.csdn.net/aesop_wubo/article/details/7537960
 *          https://www.cnblogs.com/Mainz/p/3546347.html
 * date: 2019-2-26 13:51:45
 */

public class AtomicIntegerDemo {
    private  AtomicInteger atomic = new AtomicInteger(0);
    private  Integer count = new Integer(0);

    //初始化数据，等到所有线程都执行完操作之后，在进行下一步
    private  CountDownLatch latch = new CountDownLatch(101);
    private  ExecutorService service = Executors.newFixedThreadPool(3);
//
//    public void increment() {
//        atomic.incrementAndGet();
//    }
//    //使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
//    public int getCount() {
//        return atomic.get();
//    }

    /**
     * 使用AtomicInteger,使用CAS算法保证线程安全
     */
    @Test
    public void test() {
        //通过多线程的方式来对数据进行操作
        for (int i = 0, len = 100; i <= len; i++) {
            final int scale = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    atomic.addAndGet(scale);
                    latch.countDown();
                }
            };
            service.submit(r);
        }
        try {
            //等到所有的线程都执行了countDown一次之后，在继续向下执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出最终的值
        System.out.println("0~100的和为:" + (atomic.get()));
        assertEquals(5050, atomic.get());
    }

    /**
     * 线程不安全.
     * 比如线程a读取count=1,scale=2到缓存中,
     * 然后线程b缓存中count=1,scale=3已经相加完成,并写回主存,
     * 最后线程a相加完毕,将结果count=3写回主内存.
     * 出现了错误,scale=3的情况就被覆盖了,最终结果也会出错
     */
    @Test
    public void test2() {
        //通过多线程的方式来对数据进行操作
        for (int i = 0, len = 100; i <= len; i++) {
            final int scale = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    count += scale;
                    latch.countDown();
                }
            };
            service.submit(r);
        }
        try {
            //等到所有的线程都执行了countDown一次之后，在继续向下执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出最终的值
        System.out.println("0~100的和为:" + (count));
        assertEquals(new Integer(5050), count);
    }


}
