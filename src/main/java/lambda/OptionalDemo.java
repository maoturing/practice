package lambda;

import org.junit.Test;

import java.util.Optional;

/**
 * 使用Optional,避免null检查,简化代码
 */
public class OptionalDemo {

    /**
     * 创建Optional的3放方法
     */
    @Test
    public void testCreate() {
        Optional<Integer> opt = Optional.empty();  // 返回单例对象
        Optional<Integer> opt0 = Optional.empty();  // 返回单例对象
        System.out.println("Optional.empty()是否创建单例对象:" + (opt == opt0));  // true

        Optional<Integer> opt1 = Optional.of(5);

        // 最常的构造Optional方法
        Optional<Integer> opt2 = Optional.ofNullable(null);
        Optional<Integer> opt3 = Optional.ofNullable(5);
    }

    /**
     * 简化为空才执行的场景;
     * 简化为空返回指定值的场景
     */
    @Test
    public void testExec() {
        Integer a = new Integer(5);
        Optional<Integer> integerOpt = Optional.of(a);
        // 1. 不为null执行相应操作
        // 等价于 if(a!=null) sout(a); 目的是为了简化校验操作
        integerOpt.ifPresent(System.out::println);

        // 2.1 不为null返回自身,为null返回指定值
        // 可用于替换三元表达式和判断返回特定值等.需要注意的是指定返回值的类型必须与Optional的泛型一致
        Integer result = integerOpt.orElse(666);    // 等价于a != null ? a : 666
        System.out.println("result:" + result);

        // 2.2 不为null返回自身,为null返回指定值
        Optional<Integer> integerOpt2 = Optional.ofNullable(null);
        Integer result2 = integerOpt2.orElse(666);
        System.out.println(result2);

        // 3. 不为null返回,为null抛出异常
        Integer orElseThrow = integerOpt2.orElseThrow(
                () -> new IllegalArgumentException("cannot be null or blank."));

        // 4. 为null时执行更加复杂的操作,生成返回值
        Integer result3 = integerOpt2.orElseGet(() -> new Integer(777) );
        System.out.println(result3);


    }

    /**
     * 使用Optional简化代码,提高可读性
     */
    @Test
    public void testLambda() {
        User user = new User("mwq");
//        user.setName(null);
//        user = null;
        if (user != null) {
            String userName = user.getName();
            if (userName != null) {
                System.out.println(userName.toUpperCase());
            } else {
                System.out.println("null");     // 这里用sout代替return
            }
        } else {
            System.out.println("null");
        }

        // 完全等价的代码
        Optional<User> userOpt = Optional.ofNullable(user);
        String result = userOpt.map(User::getName)
                .map(String::toUpperCase)
                .orElse(null);
        System.out.println(result);
    }

    class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
