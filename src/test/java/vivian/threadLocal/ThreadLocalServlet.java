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

@WebServlet("/threadLocalServlet")
public class ThreadLocalServlet extends HttpServlet {
	private static final long serialVersionUID = -5128235455874846875L;
	
	public ThreadLocal<String> username =new ThreadLocal<String>(); 
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username.set(request.getParameter("username"));
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        response.getWriter().print("hello:"+username.get());
    }
    
    @Test
	public void test() {
		 Server server = new Server(); 
		 Connector connector = new SelectChannelConnector(); 
		 connector.setPort(8080); 
		 server.setConnectors(new Connector[]{ connector }); 
		 ServletContextHandler root = new ServletContextHandler(null,"/",ServletContextHandler.SESSIONS); 
		 server.setHandler(root); 
		 root.addServlet(new ServletHolder(new ThreadLocalServlet()),"/"); 
		 try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void test2() throws Exception{
		Set<String> set = new HashSet<String>();
        CompletionService<String> cs = new ExecutorCompletionService<String>(Executors.newCachedThreadPool());  
        for(int i = 1; i < 1000; i++) {  
            final int taskID = i;  
            cs.submit(new Callable<String>() {  
                public String call() throws Exception {  
                	CloseableHttpClient httpclient = HttpClients.createDefault();
        			HttpGet httpget = new HttpGet("http://localhost:8080/mutilRequestServlet?username=vivian"+taskID);  
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
        System.out.println(set.size());
	}
	
}
