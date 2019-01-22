package serializable.transients.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NativeSerializeTools {

	/**
	 * 序列化
	 * 
	 * @param filePath
	 *            序列化的路径
	 * @param s
	 *            序列化的对象
	 */
	public static void write(String filePath, Serializable s) throws FileNotFoundException, IOException {
		if (filePath == null || filePath.length() == 0) {
			throw new RuntimeException("请传入序列化路径");
		}

		if (s == null) {
			throw new RuntimeException("请传入序列化对象");
		}

		File f = new File(filePath);

		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(s);
			System.out.println("finish.");
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (fos != null) {
				fos.close();
			}
			System.out.println("close the resource.");
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param filePath
	 *            反序列化的路径
	 * @return 反序列化的对象
	 */
	public static Object read(String filePath) throws ClassNotFoundException, FileNotFoundException, IOException {
		if (filePath == null || filePath.length() == 0) {
			throw new RuntimeException("请传入反序列化路径");
		}

		File f = new File(filePath);

		ObjectInputStream ois = null;
		FileInputStream fis = null;
		Object o = null;
		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			o = ois.readObject();
			System.out.println("finish.");
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (fis != null) {
				fis.close();
			}
			System.out.println("close the resource.");
		}

		return o;
	}

}