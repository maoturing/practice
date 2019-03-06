package math;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Infinity: 无穷大
 */
public class InfinityDemo {
    /**
     * 1.0/0不会抛出异常,出现bug也不易发现
     */
    @Test
    public void test1() {
        double d = 1.0 / 0;
        Double value = d;
        Double result = Double.parseDouble(value.toString()) / 100.00;
        System.out.println(d);
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(d));

        Double a = Double.valueOf(df.format(d));
    }

    @Test
    public void test2() {
        System.out.println(1 / 0);
    }
}
