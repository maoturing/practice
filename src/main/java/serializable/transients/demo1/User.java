package serializable.transients.demo1;

import java.io.Serializable;

/**
 * 序列化一般用于数据传输,比如网络传输中安全得不到保障,我们选择不对敏感信息序列化
 */
public class User implements Serializable {
    private static final long serialVersionUID = 3L;
    private String name;
    private transient String address;   // address属于敏感信息,序列化时忽略
    private int age;
    private transient int height;   // height属于敏感信息,序列化时忽略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", address=" + address + ", age=" + age + ", height=" + height + "]";
    }

}
