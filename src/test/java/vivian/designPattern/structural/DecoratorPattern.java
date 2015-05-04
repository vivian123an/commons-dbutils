package vivian.designPattern.structural;

/**
 * 装饰模式     结构型设计模式
 * 
 * 
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
