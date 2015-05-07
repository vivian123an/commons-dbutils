package vivian.designPattern.creational;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * Singleton 	
 * Ensure a class has only one instance, and provide a global point of access to it.
 * Creational patterns
 * 单列模式
 * 定义：确保一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
 * 类型：创建类模式
 * @see java.lang.System
 * @author pingan
 * @since  2015-05-03
 */
class Singleton{
	private static Singleton singleton;
	private Singleton() {
	}
	public static Singleton getInstance(){
		synchronized (Singleton.class) {
			if(singleton==null)
				return singleton = new Singleton();
			else
				return singleton;
		}
	}
}

public class SingletonPattern {

	@Test
	public void test(){
		
		//本地计算机是4核心CPU
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
		
		List<Future<Singleton>> futureList = new ArrayList<Future<Singleton>>();
		for(int i=0;i<100;i++){
			Future<Singleton> future = fixedThreadPool.submit(
					new Callable<Singleton>() {
						public Singleton call() {
							return Singleton.getInstance();
						}
					});
			futureList.add(future);
		}
		for(Future<Singleton> future:futureList){
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
