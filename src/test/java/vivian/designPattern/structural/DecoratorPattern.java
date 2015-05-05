package vivian.designPattern.structural;

import java.util.List;

/**
 * 装饰模式     结构型设计模式
 * 
 * Decorator装饰模式是一种结构型模式，它主要是解决：“过度地使用了继承来扩展对象的功能”，由于继承为类型引入的静态特质，使得这种扩展方式缺乏灵活性；
 * 并且随着子类的增多（扩展功能的增多），各种子类的组合（扩展功能的组合）会导致更多子类的膨胀（多继承）。
 * 继承为类型引入的静态特质的意思是说以继承的方式使某一类型要获得功能是在编译时。所谓静态，是指在编译时；动态，是指在运行时。
 * 
 * GoF《设计模式》中说道：动态的给一个对象添加一些额外的职责。就增加功能而言，Decorator模式比生成子类更为灵活。
 * 
 * WikiPedia:
 * In object-oriented programming, the decorator pattern (also known as Wrapper, 
 * an alternative naming shared with the Adapter pattern) is a design pattern 
 * that allows behavior to be added to an individual object, either statically or dynamically, 
 * without affecting the behavior of other objects from the same class.
 * The decorator pattern is often useful for adhering to the Single Responsibility Principle, 
 * as it allows functionality to be divided between classes with unique areas of concern
 * 
 * @see javax.servlet.http.HttpServletRequestWrapper
 * @see javax.servlet.http.HttpServletResponseWrapper
 * @see java.util.Collections#checkedMap()			checkedXXX()
 * @see java.util.Collections#synchronizedSet()  	synchronizedXXX()
 * @see java.util.Collections#unmodifiableList()   	unmodifiableXXX()
 * 
 * All subclasses InputStream, OutputStream, Reader and Writer have a constructor taking an instance of same type.
 * @see java.io.InputStream 
 * @see java.io.OutputStream
 * @see java.io.Reader
 * @see java.io.FilterOutputStream
 * @author no1
 *
 */

interface Phone{
	double getPrice();
	String function();
}
class BasicPhone implements Phone{
	@Override
	public String function() {
		return " 打电话的手机 ";
	}
	@Override
	public double getPrice() {
		return 1000.00;
	}
}
class PhoneDecorator extends BasicPhone{
	private Phone decoratedPhone;
	public PhoneDecorator(Phone decoratedPhone) {
		this.decoratedPhone = decoratedPhone;
	}
	@Override
	public String function() {
		return decoratedPhone.function();
	}
	@Override
	public double getPrice() {
		return decoratedPhone.getPrice();
	}
} 
class XiaoMi extends PhoneDecorator{
	public XiaoMi(Phone decoratedPhone) {
		super(decoratedPhone);
	}
	@Override
	public String function() {
		return super.function()+" , 会发烧的手机  ";
	}
	@Override
	public double getPrice() {
		return super.getPrice() + 200;
	}
}
class Vivo extends PhoneDecorator{
	public Vivo(Phone decoratedPhone) {
		super(decoratedPhone);
	}
	@Override
	public String function() {
		return super.function()+" , 智能音乐手机 ";
	}
	@Override
	public double getPrice() {
		return super.getPrice() + 400;
	}
}
public class DecoratorPattern {
	public static void main(String[] args) {
		Phone phone = new PhoneDecorator(new BasicPhone());
		System.out.println(phone.getPrice());
		System.out.println(phone.function());
		System.out.println();
		
		phone = new PhoneDecorator(new Vivo(new BasicPhone()));
		System.out.println(phone.getPrice());
		System.out.println(phone.function());
		System.out.println();
		
		phone = new PhoneDecorator(new XiaoMi(new Vivo(new BasicPhone())));
		System.out.println(phone.getPrice());
		System.out.println(phone.function());
	}
}
