package vivian.designPattern.structural;

/**
 * 
 * Bridge桥接模式是一种结构型模式，它主要应对的是：由于类型的固有罗辑，使得类型具有两个或两个以上的纬度变化。
 * 也就是要求抽象不应依赖于实现细节，实现细节应依赖于抽象。
 * 《设计模式》中说到将抽象部分与实现部分分离，使他们可以独立的变化。
 * 
 * Bridge模式的要点：
 * 1、Bridge模式使用“对象间的组合关系”解耦了抽象和实现之间固有的绑定关系，使得抽象和实现可以沿着各自的纬度来变化。
 * 2、所谓抽象和实现沿着各自纬度的变化，即“子类化”它们，得到各个子类之后，便可以任意组合它们。
 * 3、Bridge模式有时候类似于多继承方案，但是多继承方案往往违背单一职责原则（即一个类只有一个变化的原因），复用性比较差。Bridge模式是比多继承方案更好的解决方法。
 * 4、Bridge模式的应用一般在“两个非常强的变化纬度”，有时候即使有两个变化的纬度，但是某个方向的变化纬度并不剧烈——换言之两个变化不会导致纵横交错的结果，并不一定要使用Bridge模式。
 * 
 * 
 * 
 * WikiPedia:
 * The bridge pattern is a design pattern used in software engineering 
 * which is meant to "decouple an abstraction from its implementation so that the two can vary independently".
 * The bridge uses encapsulation, aggregation, and can use inheritance to separate responsibilities into different classes.
 * When a class varies often, the features of object-oriented programming become very useful 
 * because changes to a program's code can be made easily with minimal prior knowledge about the program. 
 * The bridge pattern is useful when both the class and what it does vary often. 
 * The class itself can be thought of as the implementation and what the class can do as the abstraction. 
 * The bridge pattern can also be thought of as two layers of abstraction.When there is only one fixed implementation, 
 * this pattern is known as the Pimpl idiom in the C++ world.The bridge pattern is often confused with the adapter pattern. 
 * In fact, the bridge pattern is often implemented using the class adapter pattern, e.g. in the Java code below.
 * 
 * 
 * 
 * @author no1
 *
 */
interface DrawingApi{
	void drawCircle();
}
class Drawing1 implements DrawingApi{
	@Override
	public void drawCircle() {
		System.out.println("Drawing1 drawCircle");
	}
}
class Drawing2 implements DrawingApi{
	@Override
	public void drawCircle() {
		System.out.println("Drawing2 drawCircle");
	}
}
abstract class Shape{
	protected DrawingApi api;
	public Shape(DrawingApi api) {
		this.api = api;
	}
	protected abstract void draw();					// low-level
	protected abstract void resizeBy(double pct);	// high-level
}
class CircleShape extends Shape{
	private double x, y, radius;
	public CircleShape(double x,double y,double radius,DrawingApi api) {
		super(api);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	// low-level i.e. Implementation specific
	@Override
	protected void draw() {
		api.drawCircle();
	}
	
	// high-level i.e. Abstraction specific
	@Override
	protected void resizeBy(double pct) {
		radius = radius*pct;
	}
	@Override
	public String toString() {
		return "X:"+x+", Y:"+y+", radius:"+radius;
	}
}
public class BridgePattern {
	public static void main(String[] args) {
		CircleShape circle = new CircleShape(1.0 , 2.0, 1.0, new Drawing1());
		circle.draw();
		System.out.println(circle);
		
		System.out.println("----------------------------------------------");
		
		CircleShape circle2 = new CircleShape(4.0 , 4.0, 5.0, new Drawing2());
		circle2.draw();
		System.out.println(circle2);
	}
}
