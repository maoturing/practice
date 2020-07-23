package clazz;

/**
 * 类加载顺序
 * why?
 * 输出: a = 0    b = 3
 *
 * @author  mao  2019-3-8 11:09:03
 */

/**
 * 从字节码可以看出, 4调用构造方法,7给静态变量sub赋值, 11给静态变量a赋值
 *   static {};
 *     descriptor: ()V
 *     flags: ACC_STATIC
 *     Code:
 *       stack=2, locals=0, args_size=0
 *          0: new           #13                 // class clazz/ClassLoadDemo
 *          3: dup
 *          4: invokespecial #14                 // Method "<init>":()V
 *          7: putstatic     #15                 // Field sub:Lclazz/ClassLoadDemo;
 *         10: iconst_2
 *         11: putstatic     #8                  // Field a:I
 *         14: return
 */
public class ClassLoadDemo {
    private static ClassLoadDemo sub = new ClassLoadDemo();

    public ClassLoadDemo() {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    private static int a = 2;
//    final private static int a = 2;
    private int b = 3;
    public static void main(String[] args) {
    }
}
