package concurrency.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 * bobi的代码
 *
 * @author mao
 * create by 2019-3-7 16:24
 */
public class ReOrderTest {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0; y = 0; a = 0; b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                // 下面两句会重排序???
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            latch.countDown();
            one.join();
            other.join();

            String result = "第" + i + "次 (a,b)=(" + a + "," + b + ")   ".concat("(x,y)=(" + x + "," + y + ")");
            if (x == 1 && y == 1) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}