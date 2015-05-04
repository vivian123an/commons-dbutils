package vivian.designPattern.creational;

/**
 * Creational patterns
 * Builder	Separate the construction of a complex object from its representation, 
 * allowing the same construction process to create various representations.
 * 
 * 定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 类型：创建类模式
 * @author pingan
 *
 */
class Product3{
	private String name;
	private String type;
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void showInfo(){
		System.out.println( "名称 :"+name+",型号："+type);
	}
}
abstract class Builder{
	protected abstract Product3 getProduct();
	protected abstract void setPart(String name,String type);
}
class ConcreteBuilder extends Builder{
	private Product3 product = new Product3();
	@Override
	protected Product3 getProduct() {
		return product;
	}
	@Override
	protected void setPart(String name, String type) {
		product.setName(name);
		product.setType(type);
	}
}
class Director{
	private Builder builder = new ConcreteBuilder();;
	public Product3 getAProduct(){
		builder.setPart("奔驰", "250 Li");
		return builder.getProduct();
	}
	public Product3 getBProduct(){
		builder.setPart("宝马", "X5");
		return builder.getProduct();
	}
}
public class BuilderPattern {
	public static void main(String[] args) {
		Director director = new Director();
		director.getAProduct().showInfo();
		director.getBProduct().showInfo();
	}
}
