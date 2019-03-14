package finals;

public class ConstantDemo {
    public static void main(String[] args) {
        System.out.println(MyParent2.str);
    }

}
class MyParent2{
    public static final String  str =  "hello world ";
//    public static String  str =  "hello world ";
    static {
        System.out.println("MyParent2 static block");
    }
}