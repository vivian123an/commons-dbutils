package vivian.designPattern.structural;

/**
 * 外观设计模式      结构型模式
 * GoF《设计模式》中说道：为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 * 
 * Façade模式的几个要点：
 *  1、从客户程序的角度看，Facade模式不仅简化了整个组件系统的接口，同时对于组件内部与外部客户程序来说，
 *  	从某种程度上也达到了一种“解耦”的效果——内部子系统的任何变化不会影响到Facade接口的变化。
 *  2、Facade设计模式更注重从架构的层次去看整个系统，而不是单个类的层次。Facade很多时候更是一种架构设计模式。
 * 
 * Facade	
 * Provide a unified interface to a set of interfaces in a subsystem. 
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 * 
 * @author no1
 *
 */
class Engine{
	public void startEngine(){
		System.out.println("发动机启动");
	}
	public void stopEngine(){
		System.out.println("发动机停止");
	}
}
class Wheel{
	public void startRoll(){
		System.out.println("轮胎转动");
	}
	public void stopRoll(){
		System.out.println("轮胎停止");
	}
}
class Body{
	private Engine engine;
	private Wheel[] wheels = new Wheel[4];
	public Body() {
		engine = new Engine();
		for(int i=0;i<4;i++){
			wheels[i] = new Wheel();
		}
	}
	public Engine getEngine() {
		return engine;
	}
	public Wheel[] getWheels() {
		return wheels;
	}
}
class CarFacade{
	private Body body;
	public CarFacade(Body body) {
		this.body = body;
	}
	public void start(){
		body.getEngine().startEngine();
		for(int i=0;i<body.getWheels().length;i++)
			body.getWheels()[i].startRoll();
	}
	public void stop(){
		body.getEngine().stopEngine();
		for(int i=0;i<body.getWheels().length;i++)
			body.getWheels()[i].stopRoll();
	}
}
public class FacadePattern {
	public static void main(String[] args) {
		CarFacade car = new CarFacade(new Body());
		car.start();
		car.stop();
	}
}
