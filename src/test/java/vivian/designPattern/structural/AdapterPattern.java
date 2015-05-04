package vivian.designPattern.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器模式(对象适配器模式)
 *  Adapter适配器模式是一种结构型模式，主要应对：由于应用环境的变化，常常需要将“一些现存的对象”放在新的环境中应用，但是，新环境要求的接口是现存对象所不满足的。
 *  《设计模式》中说道：将一个类的接口转换成客户希望的另一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的类可以一起工作。
 *  Gof23定义了两种Adapter模式的实现结构：对象适配器和类适配器。但类适配器采用“多继承”的实现方式，带来了不良的高耦合，所以一般不推荐使用。
 *  对象适配器采用“对象组合”的方式，更符合松耦合精神。Queue使用了对象适配器模式
 *  http://www.cnblogs.com/kid-li/archive/2006/05/29/412180.html
 * @author no1
 *
 * @param <T>
 */
interface IQueue<T>{
	void push(T item);
	T pop();
	T getLastItem();
	T getFirstItem();
}
class Queue<T> implements IQueue<T>{
	private List<T> adaptee;
	public Queue() {
		adaptee = new ArrayList<T>();
	}
	@Override
	public void push(T item) {
		adaptee.add(item);
	}
	@Override
	public T pop() {
		return adaptee.remove(0);
	}
	@Override
	public T getLastItem() {
		return adaptee.get(adaptee.size()-1);
	}
	@Override
	public T getFirstItem() {
		return adaptee.get(0);
	}
	@Override
	public String toString() {
		return adaptee.toString();
	}
}
public class AdapterPattern {
	public static void main(String[] args) {
		IQueue<Integer> queue = new Queue<Integer>();
		queue.push(1);
		System.out.println(queue);
		queue.push(2);
		System.out.println(queue);
		queue.push(3);
		System.out.println(queue);
		System.out.println(queue.getLastItem());
		System.out.println(queue.getFirstItem());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}
}
