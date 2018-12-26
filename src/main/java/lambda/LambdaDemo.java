package lambda;


import org.junit.Test;

import java.util.Random;

/**
 *  lambda的一些实践
 */
public class LambdaDemo {

    /**
     * 生成10个随机数
     */
    @Test
    public void randomDemo(){
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}
