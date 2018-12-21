package keyword.finallys;

import org.junit.Test;

/**
 * 一句话总结:finally必会执行,且会在try catch代码块执行结束前一步执行
 *
 * 方法结束有3种方式: 
 * 1.代码执行完毕; 
 * 2.执行return语句;
 * 3.抛出异常(被catch住不会使方法结束)
 * 
 * 
 * 编译器会将finally代码块复制三份:
 * 1.放在try代码块正常执行的出口:如果try没有异常,且try块中有return,则在return前执行finally;
 * 2.放在catch代码块正常执行的出口:如果catch没有异常,且catch块中有return,则在return前执行finally;
 * 3.最后一份为全局的异常处理器,监控try和catch代码块,捕获try中触发且catch未捕获的异常和在catch中触发的异常;
 * 
 * 如果finally中出现异常,直接向外抛出
 * 
 * @link{https://mp.weixin.qq.com/s/hHO0ChXNynh4d6x3QF-keA}
 */
@SuppressWarnings("all")
public class FinallyDemo {
	/**
	 * 正常情况,try catch执行完后执行finally,若方法未结束,继续向下执行
	 */
	@Test
	public void test() {
		try {
			System.out.println("1-try");
		} catch (Exception e) {
			System.out.println("-1-catch");
		} finally {
			System.out.println("2-finally");
		}
		System.out.println("3-try catch outter"); // 结束点
	}

	/**
	 * try中发生异常,被catch住,代码执行结束(方法结束)前执行finally
	 */
	@Test
	public void test2() {
		try {
			System.out.println("1-try");
			int a = 1 / 0;
		} catch (Exception e) {
			System.out.println("2-catch");
		} finally {
			System.out.println("3-finally");
		}
		System.out.println("4-method end"); // 结束点
	}

	/**
	 * catch中发生异常(方法结束),执行finally
	 */
	@Test
	public void test3() {
		try {
			System.out.println("1-try");
			int a = 1 / 0;
		} catch (Exception e) {
			System.out.println("2-catch");
			int a = 1 / 0; // 结束点
			System.out.println("-1-catch");
		} finally {
			System.out.println("3-finally");
		}
		System.out.println("-1-method end");
	}

	/**
	 * catch中有异常(方法结束),执行finally
	 */
	@Test
	public void test4() {
		try {
			System.out.println("1-try");
			int a = 1 / 0;
		} catch (Exception e) {
			System.out.println("2-catch");
			int a = 1 / 0; // 结束点.这里如果抛出一个异常,执行路径一样
			return; // 不会执行,因为前面发生异常,方法结束
		} finally {
			System.out.println("3-finally");
		}
		System.out.println("-1");
		return; // 不会执行
	}

	/**
	 * finally发生异常(方法结束),直接抛出
	 */
	@Test
	public void test5() {
		try {
			System.out.println("1-try");
			int a = 1 / 0;
		} catch (Exception e) {
			System.out.println("2-catch");
		} finally {
			int a = 1 / 0;
			System.out.println("-1-finally");
		}
		System.out.println("-1");
	}

	/**
	 * try中有return(方法结束),会在return之前调用finally块
	 */
	@Test
	public void test6() {
		try {
			System.out.println("1-try");
			return; // 结束点
		} catch (Exception e) {
			System.out.println("-1-catch");
		} finally {
			System.out.println("2-finally");
		}
		return; // 不会执行
	}

	/**
	 * catch中有return(方法结束),会在return之前调用finally块
	 */
	@Test
	public void test7() {
		try {
			System.out.println("1-try");
			int a = 1 / 0;
			return; // 不会执行,因为上一步发生了异常
		} catch (Exception e) {
			System.out.println("2-catch");
			return; // 结束点
		} finally {
			System.out.println("3-finally");
		}
		// 这里编译报错,因为方法的两个执行路径中都有return语句:
		// 1.没有异常,try中return,2.有异常,catch块中已经有方法结束语句(return或throw)
		// return;
	}

	/**
	 * finally中有return(方法结束),方法结束,因为在其他return之前会调用finally
	 */
	@Test
	public void test8() {
		System.out.println(test9());
		System.out.println(test10());
	}

	/**
	 * finally有return,优先级高于try中return
	 */
	private int test10() {
		try {
			System.out.println("1-try");
			return 1;
		} catch (Exception e) {
			System.out.println("-1-catch");
		} finally {
			System.out.println("2-finally");
			return 3; // 结束点
		}
	}

	/**
	 * finally有return,优先级高于catch中return
	 */
	private int test9() {
		try {
			System.out.println("1-try");
			int a = 1 / 0;
		} catch (Exception e) {
			System.out.println("2-catch");
			return 2; // debug时会先到这一步,并不代表这行代码会被执行,代表会执行return 1+1中的1+1
		} finally {
			System.out.println("3-finally");
			return 3; // 结束点
		}
	}

}
