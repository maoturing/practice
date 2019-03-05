package jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * jvm输出一个helloworld需要启动几个线程?
 * [1]main  主线程, 除了main线程其他都是守护线程
 * [2]Reference Handler   用于标记对象是否为垃圾对象,放到回收对象的队列中，以便于Finalizer线程来进行释放内存操作
 * [3]Finalizer   垃圾回收线程, 销毁对象时触发finalize()方法, {@link object.FinalizeDemo}有更加详细的说明
 * [4]Signal Dispatcher
 * [5]Attach Listener    提供一种jvm进程间通信的能力，能让一个进程传命令给另外一个进程. 比如jstack [pid]命令就是启动了jstack进程,然后对pid进程进行线程dump.
 *      既然是两个进程，那肯定涉及到进程间通信，以及传输协议的定义，比如要执行什么操作，传了什么参数等
 * [6]Monitor Ctrl-Break   IDEA下独有,windows下的监听Ctrl+break的守护进程
 * [7]JDWP Transport Listener: dt_socket   debug模式下才会出现的线程
 *
 * 参考文档: http://lovestblog.cn/blog/2014/06/18/jvm-attach/
 * http://www.cnblogs.com/mymelody/p/5611691.html
 */
public class DefaultThreads {

    public static void main(String[] args) {
        System.out.println("hello world");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo info : threadInfos) {
            System.out.println("[" + info.getThreadId() + "]" + info.getThreadName());
        }
        System.out.println("activeCount:" + Thread.activeCount());
    }
}
