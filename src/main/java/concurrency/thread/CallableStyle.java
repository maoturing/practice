package concurrency.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 利用CallableStyle创建线程，本质还是实现Runnable接口
 * FutureTask相当于是Runnable的实现类，启动线程后会调用FutureTask.run()方法，然后调用c1.call()，执行完毕后将返回值保存到FutureTask.outcome属性中
 * @author mao  2019/12/27 5:30
 */
public class CallableStyle implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //用Callable的方式创建线程, 相比较于Runnable，可以获取返回的值
        CallableStyle c1 = new CallableStyle();
        FutureTask<Integer> futureTask = new FutureTask<>(c1);

        // FutureTask是Runnable的实现类，启动线程后会调用FutureTask.run()方法，然后调用c1.call()，执行完毕后将返回值保存到FutureTask.outcome属性中
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();

        // 获取Callable的返回值，即FutureTask.outcome属性值
        System.out.println("Callable返回值: " + futureTask.get());
    }
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 调用call()方法");
        return 1024;
    }
}
