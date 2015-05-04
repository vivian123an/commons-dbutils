package vivian.designPattern.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 类适配器模式
 * 类适配器模式是以继承的方式来实现，而对象适配器模式是以组合的方式实现。
 * 为了区别对象适配器模式#AdapterPattern
 * 
 * Adapter or Wrapper or Translator
 * Convert the interface of a class into another interface clients expect. 
 * An adapter lets classes work together that could not otherwise because of incompatible interfaces. 
 * The enterprise integration pattern equivalent is the translator.
 * 
 * eg:
 * translate ArrayList to IQueue
 * 
 * @author no1
 * @see    AdapterPattern
 */
class ClassAdapter extends ArrayList implements IQueue{
	@Override
	public void push(Object item) {
		this.add(item);
	}
	@Override
	public Object pop() {
		return super.remove(0);
	}
	@Override
	public Object getLastItem() {
		return super.get(super.size()-1);
	}
	@Override
	public Object getFirstItem() {
		return super.get(0);
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
public class ClassAdapterPattern {
	public static void main(String[] args) {
		IQueue queue = new ClassAdapter();
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
