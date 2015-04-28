package vivian.jdk.classloader;

public class NetClassLoaderTest {
	public static void main(String[] args) {
		try {  
            //测试加载网络中的class文件  
            String rootUrl = "D://Users/pingan/git/commons-dbutils/target/test-classes";  
            String className = "vivian.jdk.classloader.NetClassLoaderSimple";  
            NetworkClassLoader ncl1 = new NetworkClassLoader(rootUrl);  
            NetworkClassLoader ncl2 = new NetworkClassLoader(rootUrl);  
            Class<?> clazz1 = ncl1.loadClass(className);  
            Class<?> clazz2 = ncl2.loadClass(className);  
            Object obj1 = clazz1.newInstance();  
            Object obj2 = clazz2.newInstance();  
            System.out.println(obj1.getClass().equals(obj2.getClass()));
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
}
