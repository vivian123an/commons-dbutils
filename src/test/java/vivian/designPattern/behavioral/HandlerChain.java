package vivian.designPattern.behavioral;

import org.junit.Test;

/**
 * 定义：使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
 * 类型：行为类模式
 * @author pingan
 *
 */
class Level{
	private int priority = 0;
	public Level(int priority) {
		this.priority = priority;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean high(Level level){
		if(this.priority>level.getPriority())
			return true;
		else
			return false;
	}
	@Override
	public String toString() {
		return "Level priority : "+priority;
	}
}
class Request{}
class Response{}
abstract class Handler{
	private Handler nextHandler;
	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
	public final Response handlerRequest(Request request){
		Response response = null;
		if(nextHandler!=null && (nextHandler.getHandlerLevel().high(this.getHandlerLevel())))
			nextHandler.handlerRequest(request);
		else
			response = this.doHandler(request);
		return response;
	}
	public abstract Response doHandler(Request request);
	protected abstract Level getHandlerLevel(); 
}
class ConcreteHandler1 extends Handler{
	public Response doHandler(Request request){
		System.out.println("ConcreteHandler1 被调用"+getHandlerLevel());
		return null;
	}
	protected Level getHandlerLevel(){
		return new Level(1);
	}
}
class ConcreteHandler2 extends Handler{
	public Response doHandler(Request request){
		System.out.println("ConcreteHandler2    被调用"+getHandlerLevel());
		return null;
	}
	protected Level getHandlerLevel(){
		return new Level(5);
	}
}
public class HandlerChain{
	Handler handler1 = new ConcreteHandler1();
	Handler handler2 = new ConcreteHandler2(); 
	@Test
	public void test(){
		if(handler1.getHandlerLevel().high(handler2.getHandlerLevel()))
			handler1.setNextHandler(handler2);
		else
			handler2.setNextHandler(handler1);
		handler2.handlerRequest(new Request());
	}
}


