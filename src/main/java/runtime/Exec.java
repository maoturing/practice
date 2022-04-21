package runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 执行windows cmd命令
 *
 * 参考：https://www.cnblogs.com/BensonLaur/p/5234518.html
 *
 * @author mao  2022/4/21
 */
public class Exec {

    // 补充执行linux命令

    public static void main(String[] args) throws IOException {
        // windows系统命令：cmd /c dir
        String command = "cmd /c dir ";
        // 获取当前系统的环境。
        Runtime rt = Runtime.getRuntime();

        // 执行cmd命令
        Process p = rt.exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String msg = null;
        // 输入执行结果
        while ((msg = br.readLine()) != null) {
            System.out.println(msg);
        }
        br.close();
    }
}
