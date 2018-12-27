package lambda;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 集合转字符串
     */
    @Test
    public void list2String(){
        List<String> list = new ArrayList<>();
        list.add("mwq");
        list.add("zj");
        list.add("mzc");
        String result = String.join(", ", list); // 类似的操作还有stream.collect(Collectors.joining(","))
        System.out.println(result);
    }
}
