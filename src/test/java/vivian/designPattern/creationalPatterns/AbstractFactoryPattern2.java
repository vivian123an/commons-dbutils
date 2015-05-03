package vivian.designPattern.creationalPatterns;

import org.junit.Test;
/**
 * Abstract factory	
 * Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
 * 定义：为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
 * 类型：创建类模式
 * 
 * @author pingan
 *
 */
interface IFactory1{
	IProduct1 createProduct1();
	IProduct2 createProduct2();
}
interface IProduct1{
	void show();
}
interface IProduct2{
	void show();
}
class Product1 implements IProduct1{
	@Override
	public void show() {
		System.out.println("Product1 show");
	}
}
class Product2 implements IProduct2{
	@Override
	public void show() {
		System.out.println("Product2 show");
	}
} 
class Factory1 implements IFactory1{
	@Override
	public IProduct1 createProduct1() {
		return new Product1();
	}
	@Override
	public IProduct2 createProduct2() {
		return new Product2();
	}
}
public class AbstractFactoryPattern2 {
	@Test
	public void test(){
		IFactory1 factory = new Factory1();
		factory.createProduct1().show();
		factory.createProduct2().show();
	}
}
