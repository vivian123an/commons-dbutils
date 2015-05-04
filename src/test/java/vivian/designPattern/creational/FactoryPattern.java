package vivian.designPattern.creational;

import org.junit.Test;
/**
 * Factory method
 * Define an interface for creating a single object, but let subclasses decide which class to instantiate. 
 * Factory Method lets a class defer instantiation to subclasses (dependency injection[15]).
 * 
 * 定义：定义一个用于创建对象的接口，让子类决定实例化哪一个类，工厂方法使一个类的实例化延迟到其子类。
 * 类型：创建类模式
 * @author pingan
 * @since  2015-05-03
 */

interface ICar{
	void use();
}
class Car implements ICar{
	private Engine engine;
	private Wheel wheel;
	public Car(Engine engine,Wheel wheel) {
		this.engine = engine;
		this.wheel = wheel;
	}
	@Override
	public void use() {
		engine.use();
		wheel.use();
	}
}
class Wheel{
	public void use(){
		System.out.println("使用轮胎");
	}
}
class Engine{
	public void use(){
		System.out.println("使用发动机");
	}
}
interface IFactory{
	public ICar createCar();
}
class Factory implements IFactory{
	@Override
	public ICar createCar() {
		return new Car(new Engine(),new Wheel());
	}
}
public class FactoryPattern {
	@Test
	public void test(){
		IFactory factory = new Factory();
		factory.createCar().use();
	}
}
