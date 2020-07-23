package collection.compare.demo1;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 继承Comparable接口,实现compareTo,equals,hashcode方法,以后自定义类可以进行参考
 *
 * @author mao 2019-3-11 11:30:27
 * https://blog.csdn.net/u011240877/article/details/53399019
 */
@Setter
@Getter
public class BookBean implements Serializable, Comparable<BookBean> {
    private String name;
    private int count;

    public BookBean(String name, int count) {
        this.name = name;
        this.count = count;
    }

    /**
     * 重写 equals
     * 1. null或者类型不符, 返回false, 这里使用instance而不使用getClass, instance可以对子类也返回true
     * 2. 同一个对象返回true
     * 3. 先比较count, 再比较数量
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BookBean)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        BookBean bean = (BookBean) obj;
        if (this.getName().equals(bean.getName())) {
            return false;
        }
        return this.getCount() == (bean.getCount());
    }

    /**
     * 重写 hashCode 的计算方法
     * 根据所有属性进行 迭代计算，避免重复
     * 计算 hashCode 时 计算因子 31 见得很多，是一个质数，不能再被除
     *
     * @return
     */
    @Override
    public int hashCode() {
        //调用 String 的 hashCode(), 唯一表示一个字符串内容
        int result = this.getName().hashCode();
        //乘以 31, 再加上 count
        result = 31 * result + this.getCount();
        return result;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    /**
     * 当向 TreeSet 中添加 BookBean 时，会调用这个方法进行排序
     * 实现的Comparable接口添加了泛型,在编译器就检查传入参数,不需要在运行期进行类型检查和强制转换,更加安全简洁
     *
     * @param anotherBook
     * @return
     */
    @Override
    public int compareTo(BookBean anotherBook) {
        // 先比较书名, 再比较数量
        if (this.getName() != anotherBook.getName()) {
            return this.getName().compareTo(anotherBook.getName());
        }
        if (this.getCount() != anotherBook.getCount()) {
            return this.getCount() - anotherBook.getCount() == 0 ? 1 : -1;
        }
        return 0;
    }
}
