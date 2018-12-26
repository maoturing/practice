package lambda.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate 顾名思义,谓词.比如"集合中大于3的数"这句话中大于3就是谓词,一般用于过滤
 * 函数式接口有且仅有一个抽象方法,可以通过这个抽象方法确定函数的参数与返回值
 * 查看{@link Predicate#test(java.lang.Object)}方法, 可以知道Predicate函数的参数为T,返回值为boolean
 *
 * 函数式变成是将行为(代码)作为参数传递,debug时,在48行进入test方法,
 * 可以跳到传递的函数( n -> n % 2 == 0).
 * 这其实就相当于是实现了Predicate接口,重写了test方法,而方法内容就是传递的函数,与匿名类非常类似
 *
 * eval方法可以看做是Stream.filter的简单实现,只不过我们使用filter时忽略的
 */
public class PredicateDemo {
    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");

        // 传递参数 n
        eval(list, n -> true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true

        System.out.println("输出所有偶数:");
        eval(list, n -> n % 2 == 0);

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n -> n > 3);

    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {

            if (predicate.test(n)) {
                System.out.print(n + "  ");
            }
        }
        System.out.println();
    }
}
