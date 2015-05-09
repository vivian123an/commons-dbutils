package vivian.designPattern.behavioral;

import org.junit.Test;

/**
 * 定义：定义一组算法，将每个算法都封装起来，并且使他们之间可以互换 
 * 类型：行为类模式
 * 示列: Map map = new 
 * @author pingan
 * @since  03.05.2015
 */
interface IStrategy{
	public void doSomething();
}
class Context{
	private IStrategy strategy;
	public Context(IStrategy strategy) {
		this.strategy = strategy;
	}
	public void execute(){
		strategy.doSomething();
	}
}
class ConcreteStrategy1 implements IStrategy{
	@Override
	public void doSomething() {
		System.out.println("ConcreteStrategy1 doSomething");
	}
}
class ConcreteStrategy2 implements IStrategy{
	@Override
	public void doSomething() {
		System.out.println("ConcreteStrategy2 doSomething");
	}
}
public class StrategyPattern {
	@Test
	public void test(){
		System.out.println("----------- context first time -----------");
		Context context1 = new Context(new ConcreteStrategy1());
		context1.execute();
		System.out.println("----------- context second time ------------");
		Context context2 = new Context(new ConcreteStrategy2());
		context2.execute();
	}
}
