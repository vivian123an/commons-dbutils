package vivian.designPattern.structural;

/**
 * Facade	
 * Provide a unified interface to a set of interfaces in a subsystem. 
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 * 
 * GoF《设计模式》中说道：为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 * 结构型模式
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
