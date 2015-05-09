package vivian.designPattern.behavioral;

import java.util.Vector;
/**
 * 观察者模式（有时又被称为发布/订阅模式）是软件设计模式的一种。
 * 观察者模式又叫做发布-订阅（Publish/Subscribe）模式、模型-视图（Model/View）模式、源-监听器（Source/Listener）模式或从属者（Dependents）模式。
 * 
 * 在此种模式中，一个目标对象管理所有相依于它的观察者对象，并且在它本身的状态改变时主动发出通知。
 * 这通常透过呼叫各观察者所提供的方法来实现。此种模式通常被用来实作事件处理系统。
 * 
 * 可以看出，在这个观察者模式的实现里有下面这些角色：
 * 抽象主题（Subject）角色：		主题角色把所有对观察考对象的引用保存在一个聚集里，每个主题都可以有任何数量的观察者。
 *					     	抽象主题提供一个接口，可以增加和删除观察者对象，主题角色又叫做抽象被观察者（Observable）角色，一般用一个抽象类或者一个接口实现。
 * 抽象观察者（Observer）角色：	为所有的具体观察者定义一个接口，在得到主题的通知时更新自己。这个接口叫做更新接口。抽象观察者角色一般用一个抽象类或者一个接口实现。
 * 					                        在这个示意性的实现中，更新接口只包含一个方法（即Update()方法），这个方法叫做更新方法。
 * 具体主题（ConcreteSubject）角色：将有关状态存入具体现察者对象；在具体主题的内部状态改变时，给所有登记过的观察者发出通知。具体主题角色又叫做具体被观察者角色（Concrete Observable）。
 * 							     具体主题角色通常用一个具体子类实现。
 * 具体观察者（ConcreteObserver）角色：存储与主题的状态自恰的状态。具体现察者角色实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题的状态相协调。
 * 								如果需要，具体现察者角色可以保存一个指向具体主题对象的引用。具体观察者角色通常用一个具体子类实现。
 * 
 * 从具体主题角色指向抽象观察者角色的合成关系，代表具体主题对象可以有任意多个对抽象观察者对象的引用。
 * 之所以使用抽象观察者而不是具体观察者，意味着主题对象不需要知道引用了哪些ConcreteObserver类型，而只知道抽象Observer类型。
 * 这就使得具体主题对象可以动态地维护一系列的对观察者对象的引用，并在需要的时候调用每一个观察者共有的Update()方法。这种做法叫做"针对抽象编程"。
 * 
 * 
 * WikiPedia:
 * 
 * The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents,
 * called observers, and notifies them automatically of any state changes, usually by calling one of their methods. 
 * It is mainly used to implement distributed event handling systems. 
 * The Observer pattern is also a key part in the familiar model–view–controller (MVC) architectural pattern.[1] 
 * The observer pattern is implemented in numerous programming libraries and systems, including almost all GUI toolkits.
 * The observer pattern can cause memory leaks, known as the lapsed listener problem, 
 * because in basic implementation it requires both explicit registration and explicit deregistration, 
 * as in the dispose pattern,because the subject holds strong references to the observers, keeping them alive. 
 * This can be prevented by the subject holding weak references to the observers.
 * 
 * Related patterns: Publish–subscribe pattern, mediator, singleton.
 * 
 * 
 * 
 * @author no1
 *
 */
abstract class Subject {
	private Vector<Observer> obs = new Vector<Observer>();
	public void addObserver(Observer obs){
		this.obs.add(obs);
	}
	public void delObserver(Observer obs){
		this.obs.remove(obs);
	}
	protected void notifyObserver(){
		for(Observer o: obs){
			o.update();
		}
	}
	public abstract void doSomething();
}

class ConcreteSubject extends Subject {
	public void doSomething(){
		System.out.println("被观察者事件反生");
		this.notifyObserver();
	}
}
public interface Observer {
	public void update();
}
class ConcreteObserver1 implements Observer {
	public void update() {
		System.out.println("观察者1收到信息，并进行处理。");
	}
}
class ConcreteObserver2 implements Observer {
	public void update() {
		System.out.println("观察者2收到信息，并进行处理。");
	}
}

class ObserverClient {
	public static void main(String[] args){
		Subject sub = new ConcreteSubject();
		sub.addObserver(new ConcreteObserver1()); //添加观察者1
		sub.addObserver(new ConcreteObserver2()); //添加观察者2
		sub.doSomething();
	}
}

