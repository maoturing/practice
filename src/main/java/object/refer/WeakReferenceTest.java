package object.refer;

import java.lang.ref.WeakReference;

/**
 * 如果一个对象只具有弱引用，无论内存充足与否，Java GC后对象如果只有弱引用将会被自动回收。
 *
 * 数据保存在WeakReference的referent属性中
 *
 * -XX:PrintGCDetails  打印GC日志，大对象直接进入老年代ParOldGen，GC后大对象被回收 [ParOldGen: 1024008K->633K(1374208K)]
 * GC 日志分析查看 https://www.bilibili.com/video/BV1PJ411n7xZ?p=99
 * @author User
 *
 */
public class WeakReferenceTest {

	public static int M = 1024 * 1024 * 100;

	public static void printlnMemory(String tag) {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("\n" + tag + ":");
		System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
	}

	public static void main(String[] args) {
		printlnMemory("1.原可用内存和总内存");

		// 创建弱引用
		WeakReference<Object> weakRerference = new WeakReference<Object>(new byte[10 * M]);
		printlnMemory("2.实例化10M的数组,并建立弱引用");
		System.out.println("weakRerference.get() : " + weakRerference.get());

		System.gc();
		printlnMemory("3.GC后, 对象直接被回收");
		System.out.println("weakRerference.get() : " + weakRerference.get());
	}
	
}