package object.refer;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
/**
 * 幽灵引用
 * 从PhantomReference类的源代码可以知道，它的get()方法无论何时返回的都只会是null。
 * 所以单独使用虚引用时，没有什么意义，需要和引用队列ReferenceQueue类联合使用。
 * 当执行Java GC时如果一个对象只有虚引用，就会把这个对象加入到与之关联的ReferenceQueue中。
 *
 */
public class PhantomReferenceTest {

	public static int M = 1024 * 1024;

	public static void printlnMemory(String tag) {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("\n" + tag + ":");
		System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
	}

	public static void main(String[] args) throws InterruptedException {

		printlnMemory("1.原可用内存和总内存");
		byte[] object = new byte[10 * M];
		printlnMemory("2.实例化10M的数组后");

		// 创建引用队列
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		
		// 创建虚引用,并与引用队列关联
		PhantomReference<Object> phantomReference = new PhantomReference<Object>(object, referenceQueue);

		printlnMemory("3.建立虚引用后");
		System.out.println("phantomReference : " + phantomReference);
		System.out.println("phantomReference.get() : " + phantomReference.get());
		System.out.println("referenceQueue.poll() : " + referenceQueue.poll());

		// 断开byte[10*PhantomReferenceTest.M]的强引用
		object = null;
		printlnMemory("4.执行object = null;强引用断开后");

		// Java GC时如果一个对象只有虚引用，就会把这个对象加入到与之关联的ReferenceQueue中。
		System.gc();
		printlnMemory("5.GC后");
		System.out.println("phantomReference : " + phantomReference);
		System.out.println("phantomReference.get() : " + phantomReference.get());
		System.out.println("referenceQueue.poll() : " + referenceQueue.poll());

		// 断开虚引用
		phantomReference = null;
		System.gc();
		printlnMemory("6.断开虚引用后GC");
		System.out.println("phantomReference : " + phantomReference);
		System.out.println("referenceQueue.poll() : " + referenceQueue.poll());
	}
}