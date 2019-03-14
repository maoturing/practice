package concurrency.volatiles;

/**
 * join的主要作用是同步，它可以使得线程之间的并行执行变为串行执行
 * 在A线程中调用了B线程的join()方法时，表示只有当B线程执行完毕时，A线程才能继续执行
 * 实例中main线程总是会等待子线程执行完毕后再执行,使用多次循环验证,也可以注释掉join对比
 *
 * join方法调用的wait方法,wait()方法属于java.lang.Object 类，调用线程必须获得对象锁才能调用wait()方法，
 * 而join方法又是synchronized方法，也就是说当前线程（main）在调用t1.join()方法时，获得到了t1的对象锁，这段逻辑类似如下代码
 * synchronized(t1){
 *     while(t1.isAlive())
 *           t1.wait(0);
 * }
 *
 * 当调用线程(main)执行t1.wait(0)时，调用线程(main)会释放t1的锁，一直等待t1线程执行结束(死亡)，调用线程(main)才会继续执行
 *
 * @author mao  2019-3-14 11:12
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        //循环五次
        for (int i = 0; i < 5; i++) {

            Runnable r = () -> System.out.println("子线程执行完毕");
            Thread thread =  new Thread(r);
            //启动线程
            thread.start();
                //调用join()方法,thread结束后,main线程才能执行
            thread.join();
            System.out.println("主线程执行完毕");
            System.out.println("===============");

        }
    }
}
