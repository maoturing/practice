package keyword.statics;

/**
 * final 强调不可更改,创建对象时初始化
 * static final 强调唯一且不可更改,类静态常量:强调所有对象共享这个唯一的常量, 类装载时被初始化
 * 重点:static与类有关, final与引用不可更改有关
 *
 * 参考文档:https://www.cnblogs.com/dingruihfut/p/9096226.html
 * Created by mk on 2018-12-15.
 */
public class StaticAndFinalDemo {
    final long FINAL_CURRENT_TIME = System.nanoTime();
    static final long STATIC_FINAL_CURRENT_TIME = System.nanoTime();

    /**
     * 三次输出CURRENT_TIME_SF值都一致,因为static final强调唯一且不可更改
     * 因为static变量时类变量, 在类装载时初始化, 故STATIC_FINAL_CURRENT_TIME值小于FINAL_CURRENT_TIME
     */
    public static void main(String[] args) {
        System.out.println("STATIC_FINAL_CURRENT_TIME: " + StaticAndFinalDemo.STATIC_FINAL_CURRENT_TIME);
        StaticAndFinalDemo sf = new StaticAndFinalDemo();
        System.out.println("FINAL_CURRENT_TIME: " + sf.FINAL_CURRENT_TIME);
        System.out.println("STATIC_FINAL_CURRENT_TIME: " + sf.STATIC_FINAL_CURRENT_TIME);
        System.out.println("-------------------------------------");

        StaticAndFinalDemo sf1 = new StaticAndFinalDemo();
        System.out.println("FINAL_CURRENT_TIME: " + sf1.FINAL_CURRENT_TIME);
        System.out.println("STATIC_FINAL_CURRENT_TIME: " + sf1.STATIC_FINAL_CURRENT_TIME);

    }
}
