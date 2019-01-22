package serializable.transients.demo1;

import org.junit.Test;

import java.util.ArrayList;

public class TestSerialize {

	/**
	 * 测试自定义类中transient修饰的属性序列化与反序列化
	 * 测试transient关键字
	 */
	@Test
	public void TestObject() throws Exception {
		User obj = new User();
		obj.setName("mwq");
		obj.setAddress("sx");
		obj.setAge(15);
		obj.setHeight(180);
		// 序列化保存到本地
		NativeSerializeTools.write("D://testObj.txt", obj);

		// 反序列化到内存
		User newObj = (User) NativeSerializeTools.read("D://testObj.txt");

		// 由于address和height属性被transient修饰,没有被序列化,故反序列化后得不到这两个值
		System.out.println(newObj);
	}

	/**
	 * 测试Arraylist的序列化于反序列化
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void TestArrayList() throws Exception {
		/**
		 * ArrayList中的modCount属性表示该集合被操作(增删改)了多少次,被transient修饰,表示不被序列化
		 * debug中可以看到序列化之前modCount是3,反序列化之后modeCount变为1,而size属性会被序列化,故保持不变
		 * 也可以自己写一个对象进行测试,自定义对象必须继承Serializable接口
		 * 
		 */
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		// 序列化保存到本地
		NativeSerializeTools.write("D://test.txt", list);

		// 反序列化到内存
		ArrayList<String> newList = (ArrayList<String>) NativeSerializeTools.read("D://test.txt");
		System.out.println(newList);
	}

	/**
	 * 修改SerializeObject对象的serialVersionUID,使其与序列化之前的ID不一致,
	 * 反序列化会报异常InvalidClassException,提示class的serialVersionUID不一致
	 */
	@Test
	public void TestSerializeID() {
		// 反序列化到内存
		User user = null;
		try {
			user = (User) NativeSerializeTools.read("D://testObj.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 由于address和height被transient修饰,没有被序列化,故反序列化后得不到这两个值
		System.out.println(user);
	}

}
