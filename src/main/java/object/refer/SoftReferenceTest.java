package object.refer;

import java.lang.ref.SoftReference;

/**
 * 为了用代码验证软引用只有在内存不足时才会被回收，需要加jvm参数 -Xmx15m，限制最大内存
 * 软引用和弱引用的特性基本一致， 主要的区别在于软引用在内存不足时才会被回收。
 * 如果一个对象只具有软引用，Java GC在内存充足的时候不会回收它，内存不足时才会被回收。
 *
 * 记忆点: 软的是有弹性的, 内存不足GC时才会回收
 */
public class SoftReferenceTest {

	public static int M = 1024 * 1024;

	public static void printlnMemory(String tag) {
		Runtime runtime = Runtime.getRuntime();
		int M = StrongReferenceTest.M;
		System.out.println("\n" + tag + ":");
		System.out.println(runtime.freeMemory() / M + "M(free)/" + runtime.totalMemory() / M + "M(total)");
	}

	public static void main(String[] args) {
		printlnMemory("1.原可用内存和总内存");

		// 建立软引用
		SoftReference<Object> softRerference = new SoftReference<Object>(new byte[10 * M]);
		printlnMemory("2.实例化10M的数组,并建立软引用");
		System.out.println("softRerference.get() : " + softRerference.get());

		System.gc();
		printlnMemory("3.内存可用容量充足，GC后");
		System.out.println("softRerference.get() : " + softRerference.get());

		// 实例化一个4M的数组,使内存不够用,并建立软引用
		// free=10M=4M+10M-4M,证明内存可用量不足时，GC后byte[10*m]被回收
		SoftReference<Object> softRerference2 = new SoftReference<Object>(new byte[4 * M]);
		printlnMemory("4.实例化一个4M的数组后");
		System.out.println("softRerference.get() : " + softRerference.get());
		System.out.println("softRerference2.get() : " + softRerference2.get());
	}
}
