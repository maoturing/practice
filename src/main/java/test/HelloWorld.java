package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by User on 2018/11/20.
 */
public class HelloWorld {
    public static void main(String[] args) {

        System.out.println("hello world");
        List<String> list = new ArrayList<>();
        list.add("aaa");
        Predicate<String> p = s -> "aaa".equalsIgnoreCase(s);
        list.removeIf(p.and(p));
        list.forEach(s -> System.out.println(s));
        Function<String, Integer> f = s -> s.length();
        list.stream().map(f).collect(Collectors.toList());

    }
}