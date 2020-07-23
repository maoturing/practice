package lang.integer;

/**
 * 测试自动拆箱 auto-unboxing
 * Java中对于 ==, !=, +, > 等运算符会自动拆箱
 *
 * @author mao  2020/7/23 16:12
 */
public class AutoUnboxingTest {
    public static void main(String[] args) {
        int a = 200;
        Integer b = 200;
        Integer c = 200;
        Integer dd = b + c;
        System.out.println(b == a);         // true 自动拆箱
        System.out.println(b.equals(a));    // true 自动装箱
        System.out.println(b == c);         // false 不拆箱

        // 上面的代码可以翻译如下
        System.out.println("=========================");
        System.out.println(b.intValue() == a);
        System.out.println(b.equals(Integer.valueOf(a)));
        System.out.println(b == c);
    }
}
