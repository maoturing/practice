package clazz;

/**
 * 如果类还没有被加载：
 * 1、先执行父类的静态代码块和静态变量初始化，并且静态代码块和静态变量的执行顺序只跟代码中出现的顺序有关。
 * 2、执行子类的静态代码块和静态变量初始化。
 * 3、执行父类的实例变量初始化
 * 4、执行父类的构造函数
 * 5、执行子类的实例变量初始化
 * 6、执行子类的构造函数
 *
 * 如果类已经被加载：
 * 则静态代码块和静态变量就不用重复执行，再创建类对象时，只执行与实例相关的变量初始化和构造方法。
 *
 * https://www.cnblogs.com/leiqiannian/p/7922824.html
 * @author mao   2019-3-8 11:45:07
 */
public class ClassLoadOrderDemo {
    public static void main(String[] args) {
        new ClassLoadOrderDemo();                         //4.第四步，new一个类，但在new之前要处理匿名代码块
    }

    static int num = 4;                    //2.第二步，静态变量和静态代码块的加载顺序由编写先后决定

    {
        num += 3;
        System.out.println("b.匿名代码块");           //5.第五步，按照顺序加载匿名代码块，代码块中有打印
    }

    int a = 5;                             //6.第六步，按照顺序加载变量

    { // 成员变量第三个
        System.out.println("c");           //7.第七步，按照顺序打印c
    }

    ClassLoadOrderDemo() { // 类的构造函数，第四个加载
        System.out.println("d.构造函数");           //8.第八步，最后加载构造函数，完成对象的建立
    }

    static {                              // 3.第三步，静态块，然后执行静态代码块，因为有输出，故打印a
        System.out.println("a.静态代码块");
    }

    static void run()                    // 静态方法，调用的时候才加载// 注意看，e没有加载
    {
        System.out.println("e.静态方法");
    }
}