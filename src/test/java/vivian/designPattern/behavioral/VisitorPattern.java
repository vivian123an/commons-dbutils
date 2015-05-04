package vivian.designPattern.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
/**
 * 访问者模式  Visitor Behavioral patterns
 * 定义：封装某些作用于某种数据结构中各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
 * 类型：行为类模式
 * @author pingan
 * @since  03.05.2015
 */
abstract class Element {
	public abstract void accept(IVisitor visitor);
	public abstract void doSomething();
}
interface IVisitor {
	public void visit(Element element);
}
class ConcreteElement1 extends Element {
	@Override
	public void doSomething(){
		System.out.println("这是元素1");
	}
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
class ConcreteElement2 extends Element {
	@Override
	public void doSomething(){
		System.out.println("这是元素2");
	}
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
class Visitor implements IVisitor {
	@Override
	public void visit(Element element) {
		element.doSomething();
	}
}
class ObjectStruture {
	public static List<Element> getList(){
		List<Element> list = new ArrayList<Element>();
		Random ran = new Random();
		for(int i=0; i<10; i++){
			int a = ran.nextInt(100);
			if(a>50){
				list.add(new ConcreteElement1());
			}else{
				list.add(new ConcreteElement2());
			}
		}
		return list;
	}
}
public class VisitorPattern {
	@Test
	public void test(){
		List<Element> list = ObjectStruture.getList();
		for(Element e: list){
			e.accept(new Visitor());
		}
	}
}
