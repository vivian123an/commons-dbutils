package vivian.threadLocal;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;

/**
 *  Servlet体系结构是建立在Java多线程机制之上的，它的生命周期是由Web容器负责的。
 *  当客户端第一次请求某个Servlet时，Servlet容器将会根据web.xml配置文件（或者servlet3.0扫描WebServlet注解）实例化这个Servlet类，（JSP本质也是servlet，当第一次请求时，编译成一个servlet类）。
 *  当有新的客户端再请求该Servlet时，一般不会再实例化该Servlet类，也就是有多个线程在使用这个实例。
 *  Servlet容器会自动使用线程池等技术来支持系统的运行。
 *  这样，当两个或多个线程同时访问同一个Servlet时，可能会发生多个线程同时访问同一资源的情况，数据可能会变得不一致。
 * 
 * http://my.oschina.net/lujianing/blog/276815
 * @author no1
 *
 */


@WebServlet("/mutilRequestServlet")
public class MutilRequestThreadServlet extends HttpServlet{
	private static final long serialVersionUID = 7101129711073466297L;
	
	public String username;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//synchronized (this) {
        	username =request.getParameter("username");
            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            response.getWriter().print("hello:"+username);
		//}
    }
    
	public void test() {
		 Server server = new Server(); 
		 Connector connector = new SelectChannelConnector(); 
		 connector.setPort(8080); 
		 server.setConnectors(new Connector[]{ connector }); 
		 ServletContextHandler root = new ServletContextHandler(null,"/",ServletContextHandler.SESSIONS); 
		 server.setHandler(root); 
		 root.addServlet(new ServletHolder(new MutilRequestThreadServlet()),"/"); 
		 try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
    
	
	@Test
	public void test2() throws Exception{
		Set<String> set = new HashSet<String>();
        CompletionService<String> cs = new ExecutorCompletionService<String>(Executors.newCachedThreadPool());  
        for(int i = 1; i < 1000; i++) {  
            final int taskID = i;  
            cs.submit(new Callable<String>() {  
                public String call() throws Exception {  
                	CloseableHttpClient httpclient = HttpClients.createDefault();
        			HttpGet httpget = new HttpGet("http://localhost:8080/threadLocalServlet?username=vivian"+taskID);  
                	CloseableHttpResponse response = httpclient.execute(httpget);
                    HttpEntity entity = response.getEntity();  // 获取响应实体    
                    return EntityUtils.toString(entity);
                }  
            });  
        }  
        for(int i = 1; i < 1000; i++) {  
        	String result = cs.take().get();
            System.out.println(result);
            set.add(result);
            System.out.println("-------------------");
        }
        System.out.println("++++++++++++++++++++");
        System.out.println(set.size());//每个请求线程收到的反馈String都是唯一的
	}
                                    
}
