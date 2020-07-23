package lang.string;

/**
 * @author mao  2020/3/28 2:00
 */
public class StringConcat {
    public static void main(String[] args) {
        // 下面代码在编译期就会优化为  String s1 = "aaabbb";
        // 原本会创建两个对象，然后执行拼接操作，既浪费内存，有耗费时间，编译器进行了优化
        String s1 = "aaa" + "bbb";

        // 这行代码并不会优化，因为只有运行期才能知道s1的内容
        String s2 = s1 + "ccc";

        String s3 = s1 + "ddd";

        String s4 = s1 + s2 + s3;
    }
}
