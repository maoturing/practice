package concurrency.thread;

import object.refer.WeakReferenceTest;
import org.junit.Test;

/**
 *
 * Java中的ThreadLocal类允许我们创建只能被同一个线程读写的变量。ThreadLocal中填充的变量属于当前线程，该变量对其他线程而言是隔离的。
 * ThreadLocal将数据保存到ThreadLocalMap中,key为线程,value为set值,
 *
 * 使用场景: SpringMVC中Controller和Service是单例的,全局变量设置为ThreadLocal可以达到线程隔离的作用
 *
 * 参考文档: https://droidyue.com/blog/2016/03/13/learning-threadlocal-in-java/
 * Created by User on 2019/1/7.
 */
public class ThreadLocalDemo {
    /**
     * api的使用: set, get
     */
    @Test
    public void test() throws InterruptedException {
        ThreadLocal<String> local = new ThreadLocal<>();
        System.out.println(local.get());
        local.set("mwq");
        local.set("123");
        System.gc();
        Thread.sleep(5000);
        System.out.println(local.get());
    }

    /**
     * 多个线程使用全局变量local,互不影响
     */
    @Test
    public void testThreadLocal() {
        ThreadLocal<String> local = new ThreadLocal<>();    // 全局变量
        local.set(Thread.currentThread().getName() + " haha");


        new Thread(
                () -> {
                    System.out.println(local.get());
                    local.set(Thread.currentThread().getName() + " world");
                    System.out.println(local.get());    //Thread-1 world
                }
        ).start();
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println(local.get());
                local.set(Thread.currentThread().getName() + " hello");
                System.out.println(local.get());       // Thread-0 hello
            }
        };
        t.start();

        System.out.println(local.get());    //main haha
    }

    /**
     * 重写initialValue()方法为ThreadLocal设置初始值
     * 默认为null
     */
    @Test
    public void testInit() {
        ThreadLocal<String> local = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return Thread.currentThread().getName();
            }
        };
        System.out.println(local.get());
    }

    /**
     * ThreadLocalMap.Entry用于保存数据,是弱引用
     * 测试ThreadLocalMap.Entry是否会被回收
     */
    @Test
    public void testWeakRefer() throws InterruptedException {
        ThreadLocal<byte[]> local = new ThreadLocal<>();
        WeakReferenceTest.printlnMemory("1.原可用内存和总内存");
        System.out.println(local.get());
        local.set(new byte[10 * WeakReferenceTest.M]);
        WeakReferenceTest.printlnMemory("2.实例化10M的数组");

        System.gc();
        WeakReferenceTest.printlnMemory("3.GC后");
        System.out.println(local.get());
    }
}
