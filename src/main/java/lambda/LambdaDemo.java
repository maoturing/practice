package lambda;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * lambda的一些实践
 */
public class LambdaDemo {

    /**
     * 生成10个随机数
     */
    @Test
    public void randomDemo() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**
     * 集合转字符串
     */
    @Test
    public void list2String() {
        List<String> list = new ArrayList<>();
        list.add("mwq");
        list.add("zj");
        list.add("mzc");
        String result = String.join(", ", list); // 类似的操作还有stream.collect(Collectors.joining(","))
        System.out.println(result);
    }

    /**
     * 启动一个线程
     * 每个 Lambda 表达式都能隐式地赋值给函数式接口
     */
    @Test
    public void startThread() {
        // 方法1,将代码传递给函数式接口Runnable.简化了以前重写
        Runnable r = () -> System.out.println("hello world...");
        new Thread(r).start();

        // 方法2,编译器会自动推断：根据线程类的构造函数签名 public Thread(Runnable r) { },将该 Lambda 表达式赋给 Runnable 接口
        new Thread(() -> System.out.println("hello world"))
                .start();
    }
}
