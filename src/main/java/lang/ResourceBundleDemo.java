package lang;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 根据不同语言环境,获取不同的资源文件
 * Web框架的国际化底层就是这样实现的
 *
 * 当在中文操作系统下，
 * 如果myres_zh_CN.properties、myres.properties两个文件都存在，则优先会使用myres_zh_CN.properties，
 * 当myres_zh_CN.properties不存在时候，会使用默认的myres.properties。
 *
 * Created by User on 2018/11/29.
 * 参考文档:http://blog.51cto.com/lavasoft/184605
 */
public class ResourceBundleDemo {

    // 资源文件名称
    private static final String RESOURCE_NAME = "myres";

    public static void main(String[] args) {

        Locale locale1 = new Locale("zh", "CN");
        ResourceBundle res1 = ResourceBundle.getBundle("myres", locale1);
        System.out.println(res1.getString("key1"));

        Locale locale3 = new Locale("en", "US");
        ResourceBundle res2 = ResourceBundle.getBundle("myres", locale3);
        System.out.println(res2.getString("key1"));

        // 根据操作系统语言环境,获取对应的资源文件
        ResourceBundle res3 = ResourceBundle.getBundle("myres", Locale.getDefault());
        System.out.println(Locale.getDefault());
        System.out.println(res3.getString("key1"));
    }
}
