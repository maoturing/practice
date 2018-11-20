package collection.map;

/**
 * debug观察HashMap如何确定entry的索引，处理hash冲突，如何扩容和rehash
 * HashMap中有一个table数组，默认长度为16，存储Entry对象，Entry有三个属性，key,value,next
 * 根据HashMap的hash方法获得key的hash值，再和HashMap的容量进行与运算，获得key的索引，再保存到对应位置
 * hash冲突后，将其保存到table的对应位置，然后
 */
import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
	public static void main(String[] args) {
		TestHashMap test = new TestHashMap();
		
		test.strMap();
	}
	
    
    public void strMap(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	int length = 16-1;	//map中的table数组默认长度为16,负载因子为0.75
		String s1 = "java";
		String s2 = "mysql";
		String s3= "spring";
		String s4 = "android";
		String s5 = "scala";	
		
		//hash(s1)&length，与运算，得到entry存放的索引，我们将索引作为value方便观察
		map.put(s1, hash(s1)&length);
		map.put(s2, hash(s2)&length);
		map.put(s3, hash(s3)&length);	//spring的索引为13
		map.put(s4, hash(s4)&length);
		
		//scala的索引为13，hash冲突，debug时可以看到table中13位置的entry对象为scala，entry的next为spring
		map.put(s5, hash(s5)&length);	
    }	
    
	//这个方法是截取的jdk1.7中HashMap中hash()方法的实现
    public int hash(Object k) {
        int h = 0;
        if (0 != h && k instanceof String) {
            // return sun.misc.Hashing.stringHash32((String) k); //java8 不支持此方法
        }
        h ^= k.hashCode();
        
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        int result = h ^ (h >>> 7) ^ (h >>> 4);
        return result;
    }
}
