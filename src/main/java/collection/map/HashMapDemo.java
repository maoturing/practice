package collection.map;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapDemo {
    private Map<String, String> maps = new HashMap<String, String>();
    @Before
    public void generateMaps(){
        maps.put("1", "apple");
        maps.put("2", "banana");
        maps.put("3", "watermelon ");
    }

    /**
     * 遍历hashmap的三种方式
     */
    @Test
    public void traverse(){
        for (String key : maps.keySet()) {
            System.out.println("key= " + key + " and value= " + maps.get(key));
        }

        // 方法2:keySet其实是遍历了2次，一次是转为Iterator对象，另一次是从hashMap中取出key所对应的value。
        Iterator it1 = maps.keySet().iterator();
        Object key;
        Object value;
        while(it1.hasNext()){
            key=it1.next();
            value=maps.get(key);
            System.out.println(key+":"+value);
        }

        //方法3: 而entrySet只是遍历了一次就把key和value都放到了entry中，效率更高。如果是JDK8，使用Map.foreach方法。
        Iterator<Map.Entry<String, String>> it = maps.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        for (Map.Entry<String, String> entry : maps.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        for (String v : maps.values()) {
            System.out.println("value= " + v);
        }
    }

    /**
     * Map key或value转数组
     */
    @Test
    public void test(){
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("1", "apple");
        maps.put("2", "banana");
        maps.put("3", "watermelon ");

        String[] strArr = new String[maps.size()];
        // maps.keySet().toArray(strArr);
        maps.values().toArray(strArr);
        for(String str:strArr){
            System.out.println(str);
        }
    }
}
