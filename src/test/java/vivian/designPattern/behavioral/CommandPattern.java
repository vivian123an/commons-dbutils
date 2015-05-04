package vivian.designPattern.behavioral;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 命令模式 
 * 行为类模式
 * Command命令模式是一种对象行为型模式，它主要解决的问题是：在软件构建过程中，“行为请求者”与“行为实现者”通常呈现一种“紧耦合”的问题。
 * 有时我们必须向某对象提交请求，但并不知道关于被请求的操作或请求的接受者的任何信息，此时无法抵御变化的紧耦合是不合适的。
 * 如：需要对行为进行“记录、撤销/重做、事务”等处理。我们所要做的是将依赖关系转化，将紧耦合变为松耦合。
 * 
 * eg:
 * ActionRequest --> ActionImplementor
 * 
 * above transfer to follow
 * 
 * ActionRequest --> Command --> Receiver
 * 
 * @author no1
 *
 */
public class CommandPattern {
	private int state;
	public CommandPattern(Integer state) {
		this.state = state;
	}
	public int addOne(Integer a){
		return state = state + a.intValue();
	}
	public int addTwo(Integer one,Integer two){
		return state = state + one.intValue() + two.intValue();
	}
	
	static class Command{
		private Object receiver;
		private Method action;
		private Object[] args;
		
		public Command(Object object,String methodName,Object[] args) {
			this.receiver = object;
			this.args = args;
			Class[] classTypes = new Class[args.length];
			for(int i=0;i<args.length;i++){
				classTypes[i] = args[i].getClass();
			}
			try {
				action = object.getClass().getMethod(methodName, classTypes);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		public Object execute(){
			try {
				return action.invoke(receiver, args);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new CommandPattern(0).addOne(3));
		System.out.println(new CommandPattern(0).addTwo(4,5));
		System.out.println("------------------------------------");
		
		Command command1 = new Command(new CommandPattern(0),"addOne",new Object[]{3});
		Command command2 = new Command(new CommandPattern(0),"addTwo",new Object[]{4,5});
		System.out.println(command1.execute());
		System.out.println(command2.execute());
	}
}
