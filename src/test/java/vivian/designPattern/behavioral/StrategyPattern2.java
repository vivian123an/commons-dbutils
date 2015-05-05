package vivian.designPattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy策略模式是一种对象行为模式。
 * 主要是应对：在软件构建过程中，某些对象使用的算法可能多种多样，经常发生变化。如果在对象内部实现这些算法，将会使对象变得异常复杂，甚至会造成性能上的负担。
 * 
 * GoF《设计模式》中说道：定义一系列算法，把它们一个个封装起来，并且使它们可以相互替换。该模式使得算法可独立于它们的客户变化。
 * 
 * 典型的策略设计模式：
 * java.util.Collections#sort() 结合java.util.Comparator#compare() 排序
 * 
 * WikiPedia:
 * Strategy pattern
 * the strategy pattern (also known as the policy pattern) is a software design pattern that enables an algorithm's behavior to be selected at runtime. 
 * The strategy pattern defines a family of algorithms,encapsulates each algorithm, and makes the algorithms interchangeable within that family.
 * Strategy lets the algorithm vary independently from clients that use it.
 * 
 * java.util.Comparator#compare(), executed by among others Collections#sort().
 * javax.servlet.http.HttpServlet, the service() and all doXXX() methods take HttpServletRequest and HttpServletResponse 
 * and the implementor has to process them (and not to get hold of them as instance variables!).
 * javax.servlet.Filter#doFilter()
 * 
 * @author no1
 * @see java.util.Collections#sort()
 */
interface BillStrategy{
	double sum(List<Double> list);
}
class NormalStrategy implements BillStrategy{
	@Override
	public double sum(List<Double> list) {
		double result = 0;
		for(double money:list)
			result += money;
		return result;
	}
}
class DiscountStrategy implements BillStrategy{
	@Override
	public double sum(List<Double> list) {
		double result = 0;
		for(double money:list)
			result += money;
		return result*0.8;
	}
}
class StrategyContext{
	private List<Double> drinks;
	private BillStrategy strategy;
	public StrategyContext(BillStrategy strategy) {
		this.strategy = strategy;
	}
	public void add(double price,int number){
		if(drinks == null)
			drinks = new ArrayList<Double>();
		drinks.add(price*number);
	}
	public void printHowMuch(){
		System.out.println(strategy.sum(drinks));
	}
}
public class StrategyPattern2 {
	public static void main(String[] args) {
		StrategyContext context = new StrategyContext(new NormalStrategy());
		context.add(188.88, 10);
		context.add(388.88, 2);
		context.printHowMuch();
		System.out.println();
		
		StrategyContext context2 = new StrategyContext(new DiscountStrategy());
		context2.add(188.88, 10);
		context2.add(388.88, 2);
		context2.printHowMuch();
	}
}
