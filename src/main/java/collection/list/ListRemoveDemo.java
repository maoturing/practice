package collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * List集合的remove()方法,有两个重载方法,
 * 当参数是Object时,调用对象的equals()方法查找对象并删除
 * 当参数是int类型时,参数作为索引删除对应位置上的对象
 */
public class ListRemoveDemo {
    @Test
    public void testByIndex() {
        List<Integer> li = new ArrayList<Integer>();
        li.add(4);
        li.add(3);
        li.add(2);
        li.add(1);
        //1是int类型而不是Integer类型，所以调用的是remove(int index)方法，删除了位置1处的数字3
        li.remove(1);
        //参数是Integer类型而不是int类型，所以调用的是remove(Object o)方法，删除了数字1
        li.remove(new Integer(1));
    }

    @Test
    public void testByObject() {
        List<String> ls = new ArrayList();
        ls.add("aa");
        ls.add("bb");
        ls.add("cc");
        ls.add("dd");
        //remove调用的是equals方法比较,删除相应的元素,因为String重写了equals方法,所以这里能够正确删除.
        //这里提醒我以后删除自定义对象时需要重写equals方法.
        ls.remove("dd");
    }
}
