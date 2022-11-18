package math;

/**
 * @author mao  2022/6/21
 */
public class Circle {
    public static void main(String[] args) {
        double r = 1;
        int n = 200000000 ;
        double x = r * 2 / n;
        double mianji = 0;
        for (int i = 1; i <= n/2; i++) {
            double y = Math.sqrt(r * r - Math.pow(x * i, 2));
            double result = x * y;
            mianji += result;
        } 

        System.out.println(mianji * 4);
    }


}
