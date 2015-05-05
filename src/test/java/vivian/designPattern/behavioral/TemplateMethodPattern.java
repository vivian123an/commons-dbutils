package vivian.designPattern.behavioral;

/**
 * 行为模式：行为型模式涉及到算法和对象间职责的分配。将注意力从控制流转移到对象间的联系方式上来。
 * 行为模式分为：行为类模式和行为对象模式。行为类模式使用继承机制在类间分派行为；行为对象模式使用对象复合而不是继承。
 * Template Method模板方法模式
 * Template Method模板方法模式介绍：
 * Template Method模板方法模式是一种行为型模式，具体点说是行为类型模式。
 * 主要解决在软件构建过程中，对于某一项任务，常常有稳定的整体操作结构，但各个子步骤却有很多改变的需求，或者由于固有的原因而无法和任务的整体结构同时实现。
 * GoF《设计模式》中说道：定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。Template Method使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
 * 
 * 
 * the template method pattern is a behavioral design pattern 
 * that defines the program skeleton of an algorithm in a method, called template method, which defers some steps to subclasses.
 * It lets one redefine certain steps of an algorithm without changing the algorithm's structure
 * 
 * All non-abstract methods of java.io.InputStream, java.io.OutputStream, java.io.Reader and java.io.Writer.
 * All non-abstract methods of java.util.AbstractList, java.util.AbstractSet and java.util.AbstractMap.
 * javax.servlet.http.HttpServlet, all the doXXX() methods by default sends a HTTP 405 "Method Not Allowed" error to the response. 
 * You're free to implement none or any of them.

 * 
 * @author no1
 * @see java.io.InputStream
 * @see java.util.AbstractList
 * @see javax.servlet.http.HttpServlet
 * @see org.springframework.jdbc.core.JdbcTemplate#execute(org.springframework.jdbc.core.ConnectionCallback)
 */
abstract class AbstractCar{
	protected abstract void startUp();
	protected abstract void run();
	protected abstract void stop();
	public void drive(){
		this.startUp();
		this.run();
		this.stop();
	}
}
class BMW extends AbstractCar{
	@Override
	protected void run() {
		System.out.println("宝马加速");
	}
	@Override
	protected void startUp() {
		System.out.println("启动宝马");
	}
	@Override
	protected void stop() {
		System.out.println("宝马熄火");
	}
}
public class TemplateMethodPattern {
	public static void main(String[] args) {
		AbstractCar car = new BMW();
		car.drive();
	}
}
