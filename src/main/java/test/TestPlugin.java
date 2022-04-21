package test;

/**
 * @author mao  2021/12/5 19:12
 */
public class TestPlugin {


    public static void main(String[] args) {
        while (getFlag()) {
            System.out.println("111");
        }
        System.out.println(222);

    }

    static boolean getFlag() {
        return true;
    }

}
