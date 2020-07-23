package test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 2018/11/20.
 */
public class HelloWorld {
    public static void main(String[] args) {

        Integer integer = new Integer(1);
        System.out.println(integer.compareTo(99));

        double a = 1.0-0.9;
        System.out.println(0.1);


        ArrayList<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        String[] strarr = new String[6];
        String[] res = list.toArray(strarr);
        Arrays.stream(res).forEach(x-> System.out.println(x));
        Arrays.stream(strarr).forEach(x-> System.out.println(x));

//String[] strarr = list.toArray(new String[list.size()]);
    }
}