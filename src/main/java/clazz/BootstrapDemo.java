package clazz;

import org.junit.Test;

import java.net.URL;

public class BootstrapDemo {
    /**
     * 打印Bootstrap ClassLoader所有已经加载的类库
     */
    @Test
    public void listLoadClasses(){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toString());
        }
    }
}
