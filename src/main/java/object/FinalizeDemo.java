package object;

/**
 * finalize是Object类的默认方法, 是Object重要方法之一, 我是谁 getClass(), 我从哪里来 Objetc(), 我到哪里去 finalize()
 * jvm进行GC时会触发被回收对象的finalize()方法
 * 对finalize()方法进行debug,可以看到是Finalizer线程调用的finalize()方法,
 * 另外在IDEA的debug模式下,可以看到共有5个线程: main, Attach Listener, Finalizer, Reference Handler, Singnal Dispatcher
 *
 */
public class FinalizeDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 100 ; i++) {
            User user = new User("tracer", 18);
        }
        System.gc();
    }

    static class User {
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize...");
            super.finalize();
        }
    }
}
