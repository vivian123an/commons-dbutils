package vivian.designPattern.structural;
/**
 * 代理模式 （Proxy design pattern）
 * 结构型模式
 * 
 * GoF《设计模式》中说道：为其他对象提供一种代理以控制这个对象的访问。
 * Proxy代理模式是一种结构型设计模式，主要解决的问题是：在直接访问对象时带来的问题，比如说：要访问的对象在远程的机器上。
 * 在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），
 * 直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。
 * 
 * Provide a surrogate or placeholder for another object to control access to it.
 * 
 * Create a "wrapper" for a remote, or expensive, or sensitive target
 * Encapsulate the complexity/overhead of the target in the wrapper
 * The client deals with the wrapper
 * The wrapper delegates to the target
 * To support plug-compatibility of wrapper and target, create an interface
 * 
 * @author no1
 *
 */
interface Image{
	void display();
}
class RealImage implements Image{
	private String fileName;
	public RealImage(String name) {
		this.fileName = name;
		loadImage();
	} 
	@Override
	public void display() {
		System.out.println("image :"+fileName);
	}
	public void loadImage(){
		System.out.println("loading :"+fileName);
	}
}
class ProxyImage implements Image{
	private Image image;
	private String fileName;
	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public void display() {
		if(image==null)
			image = new RealImage(fileName);
		image.display();
	}
}
public class ProxyPattern {
	public static void main(String[] args) {
		Image proxy1 = new ProxyImage("family");
		Image proxy2 = new ProxyImage("wedding");
		proxy1.display(); // loading necessary
		proxy1.display(); // loading unnecessary
		proxy2.display(); // loading necessary
		proxy2.display(); // loading unnecessary
		proxy1.display(); // loading unnecessary
	}
}
