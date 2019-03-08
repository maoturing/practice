package clazz;

/**
 * 类加载顺序
 * why?
 * 输出: a = 0    b = 3
 *
 * @author  mao  2019-3-8 11:09:03
 */
public class ClassLoadDemo {
    private static ClassLoadDemo sub = new ClassLoadDemo();

    public ClassLoadDemo() {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    private static int a = 2;
    private int b = 3;
    public static void main(String[] args) {
    }
}
