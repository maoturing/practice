package lang.string;

/**
 * 58同城面试题, 《深入理解JVM》代码清单2-8
 * <p>
 * String.intern() 的作用是 判断字符串常量池中是否存在该字符串, 如果不存在, 则将字符串加入常量池并返回引用,
 * 如果存在, 则返回常量池中该字符串的引用
 *
 * @author mao  2020/10/20 22:36
 */
public class StringPoolDemo {
    public static void main(String[] args) {
        // 测试 .intern() 的作用
        String s0 = "tracer";
        String s1 = new String("tracer");
        // false, s0 是在常量池中添加一个字符串, s1 是在堆中创建一个对象
        System.out.println(s0 == s1);
        // true, s1.intern() 是从常量池中返回"tracer"字符串的引用, 即s0的引用
        System.out.println(s0 == s1.intern());
        System.out.println("=======================");

        String str0 = new StringBuilder("58").append("tongcheng").toString();
        // true, str0.intern() 是将"58tongcheng"加入字符串常量池并返回引用
        System.out.println(str0 == str0.intern());
        String str1 = new StringBuilder("58").append("tongcheng").toString();
        // false, str1.intern()返回是字符串常量池中已存在且与"58tongcheng"相等的字符串引用 str0, 而str1是新创建的对象
        System.out.println(str1 == str1.intern());

        String str2 = new StringBuilder("ja").append("va").toString();
        // false, 因为"java"字符串在JVM启动时就将入了字符串常量池, str2.intern()返回了当时加入的字符串引用, 所以与新创建的str2不相等
        // 类似于前面的 str1 == str1.intern()
        // sun.misc.Version 中 launcher_name = "java"; , 在jvm启动时加入了常量池
        System.out.println(str2 == str2.intern());
    }
}
