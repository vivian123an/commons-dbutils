package vivian.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;
/**
 * 这里需要注意的地方时，很多人认为监听 SelectionKey.OP_ACCEPT 事件就已经是非阻塞方式了，
 * 其实 Jetty 仍然是用一个线程来监听客户端的连接请求，当接受到请求后，把这个请求再注册到 Selector 上，然后才是非阻塞方式执行。
 * 这个地方还有一个容易引起误解的地方是：认为 Jetty 以 NIO 方式工作只会有一个线程来处理所有的请求，
 * 甚至会认为不同用户会在服务端共享一个线程从而会导致基于 ThreadLocal 的程序会出现问题，
 * 其实从 Jetty 的源码中能够发现，真正共享一个线程的处理只是在监听不同连接的数据传送事件上，
 * 比如有多个连接已经建立，传统方式是当没有数据传输时，线程是阻塞的也就是一直在等待下一个数据的到来，
 * 而 NIO 的处理方式是只有一个线程在等待所有连接的数据的到来，而当某个连接数据到来时 Jetty 会把它分配给这个连接对应的处理线程去处理，所以不同连接的处理线程仍然是独立的。
 * 
 * @author no1
 *
 */
public class JettyTest {

	public static void main(String[] args) {
		SelectChannelConnector module = new SelectChannelConnector();
		SocketConnector connector = new SocketConnector();
	}
	
	@Test
	public void test() {
		 Server server = new Server(); 
		 Connector connector = new SelectChannelConnector(); 
		 connector.setPort(8080); 
		 server.setConnectors(new Connector[]{ connector }); 
		 ServletContextHandler root = new ServletContextHandler(null,"/",ServletContextHandler.SESSIONS); 
		 server.setHandler(root); 
		 root.addServlet(new ServletHolder(new HttpServletRequestDemo()),"/"); 
		 try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
