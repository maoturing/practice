package collection.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    private List<String> list = new LinkedList<>();

    @Before
    public void init() {
        list.add("mybatis");
        list.add("java");
        list.add("scala");
        list.add("hadoop");
    }

    /**
     * LinkedList可以实现队列操作: add() poll()
     * remove()与poll()的区别在于对于空集合的处理不同,前者抛出异常,后者返回null
     */
    @Test
    public void testQueue() {
        String ele = ((LinkedList<String>) list).poll();
        System.out.println(ele);
        list.add("spring");
        System.out.println(String.join(", ", list));
    }

    /**
     * 使用迭代器遍历
     */
    @Test
    public void testTraverseByIter() {
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    /**
     * 逆序遍历
     */
    @Test
    public void testTraverseByDescIter() {
        Iterator<String> itr = ((LinkedList<String>) list).descendingIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    /**
     * fori遍历
     * 效率最低的一种遍历方式,因为每次循环都要从头结点开始获取目标结点
     * get(i)并不是每次都从头结点开始,查看{@link java.util.LinkedList#node(int)}源码可知,
     * 会先判断i位于链表前半段还是后半段,然后从头结点或者尾结点开始遍历,充分利用了LinkedList双向链表的特性
     */
    @Test
    public void testTraverseByFori() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
