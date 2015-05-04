package vivian.designPattern.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite组合模式主要是应对这样的问题：
 * 一类具有“容器特征”的对象——即他们在充当对象的同时，又是其他对象的容器的情况。
 * 在编写时我们常常会造成：客户代码过多地依赖于对象容器复杂的内部实现，对象容器内部实现结构（而非抽象接口）的变化将引起客户代码的频繁变化，带来了代码的维护性、扩展性的弊端。
 * 
 * GoF《设计模式》中说到：将对象组合成树形结构以表示“部分-整体”的层次结构。Composite模式使得客户对单个对象和组合对象的使用具有一致性。
 * 
 * wikiPedia：
 * In software engineering, the composite pattern is a partitioning design pattern. 
 * The composite pattern describes that a group of objects is to be treated in the same way as a single instance of an object.
 * The intent of a composite is to "compose" objects into tree structures to represent part-whole hierarchies. 
 * Implementing the composite pattern lets clients treat individual objects and compositions uniformly
 * 
 * 1.Component
 * is the abstraction for all components, including composite ones
 * declares the interface for objects in the composition
 * (optional) defines an interface for accessing a component's parent in the recursive structure, and implements it if that's appropriate
 * 
 * 2.Leaf
 * represents leaf objects in the composition 
 * implements all Component methods
 * 
 * 3.Composite
 * represents a composite Component (component having children)
 * implements methods to manipulate children
 * implements all Component methods, generally by delegating them to its children
 * 
 * @author no1
 *
 */
/** "Component" */
interface Graphic{
	void print();
}
/** "Leaf" */
class Ellipse implements Graphic{
	private int id;
	public Ellipse(int id) {
		this.id = id;
	}
	@Override
	public void print() {
		System.out.println("this is Ellipse:"+id);
	}
}
/** "Composite" */
class CompositeGraphic implements Graphic{
	private List<Graphic> list;
	public CompositeGraphic() {
		list = new ArrayList<Graphic>();
	}
	@Override
	public void print() {
		for(Graphic graphic:list)
			graphic.print();
	}
	public void add(Graphic e){
		list.add(e);
	}
	public void remove(Graphic e){
		list.remove(e);
	}
}
/** Client */
public class CompositePattern {
	public static void main(String[] args) {
		//Initialize four ellipses
        Ellipse ellipse1 = new Ellipse(1);
        Ellipse ellipse2 = new Ellipse(2);
        Ellipse ellipse3 = new Ellipse(3);
        Ellipse ellipse4 = new Ellipse(4);
 
        //Initialize three composite graphics
        CompositeGraphic graphic = new CompositeGraphic();
        CompositeGraphic graphic1 = new CompositeGraphic();
        CompositeGraphic graphic2 = new CompositeGraphic();
 
        //Composes the graphics
        graphic1.add(ellipse1);
        graphic1.add(ellipse2);
        graphic1.add(ellipse3);
 
        graphic2.add(ellipse4);
 
        graphic.add(graphic1);
        graphic.add(graphic2);
 
        //Prints the complete graphic (four times the string "Ellipse").
        //注意最后打印了四条数据
        graphic.print();
	}
}
