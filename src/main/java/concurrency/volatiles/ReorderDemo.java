package concurrency.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 重排序demo
 *
 * 参考文档: java并发编程的艺术3.2.4
 * @author mao  2019-3-8 10:39
 */
public class ReorderDemo {
    int a = 0;
    boolean flag = false;
    static int result = 0;

    public void write(){
        a = 1;
        flag = true;
    }
    public void reader(){
        if(flag){
            System.out.println(result = a + a);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        ReorderDemo demo = new ReorderDemo();
        for (; ; ) {
            i++;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                demo.write();
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                demo.reader();
            });
            one.start();
            other.start();
            latch.countDown();
            one.join();
            other.join();
            if(result==0){
                System.err.println("======");
                break;
            }
        }
    }
}
