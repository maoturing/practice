package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 向文件写入、读取内容
 *
 * FileWriter 类从 OutputStreamWriter 类继承而来。该类按字符向流中写入数据。
 * FileWriter.write() 方法是线程安全的, 不会出现多线程写入互相覆盖的情况
 *
 * @author mao  2022/6/17
 */
public class FileWriterDemo {
    public static void main(String args[]) throws IOException {
        // 1.创建文件
        File file = new File("Hello1.txt");
        file.createNewFile();

        // 2.使用FileWriter向文件写入内容
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("This\n is\n an\n example\n");
        fileWriter.flush();
        fileWriter.close();

        // 3.使用FileReader读取文件内容
        FileReader fileReader = new FileReader(file);
        char[] buffer = new char[50];
        fileReader.read(buffer);         // 将文件内容读取到数组中

        // 4.打印内容
        System.out.print(new String(buffer));
        fileReader.close();
    }
}
