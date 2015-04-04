package org.apache.commons.dbutils;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
 
 
 
public class JdbcUtils {
    
    private static DataSource ds = null;
    private static Connection conn = null;
    //在静态代码块中创建数据库连接池
    static{
        try{
            //通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
        	//ds = new ComboPooledDataSource("MySQL");//使用C3P0的命名配置来创建数据源
        	
        	String url =   "jdbc:mysql://localhost:3306/dbutils" ;
           	String jdbcDriver =   "com.mysql.jdbc.Driver" ;
           	String user =  "root" ;
           	String password =   "1234" ;

           	DbUtils.loadDriver(jdbcDriver);
           	try {
     			conn =  DriverManager.getConnection(url, user, password);
     			System.out.println(conn.getMetaData());
     		} catch (SQLException e) {
     			e.printStackTrace();
     		}
             
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Connection getConnection() throws SQLException{
        //从数据源中获取数据库连接
        return ds.getConnection();
    }
    public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try{
                //关闭存储查询结果的ResultSet对象
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //关闭负责执行SQL命令的Statement对象
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
         
        if(conn!=null){
            try{
                //将Connection连接对象还给数据库连接池
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 
    public static DataSource getDataSource() {
        // TODO Auto-generated method stub
        return ds;
    }
    
    public static Connection getConnectionDirect(){
    	return conn;
    }
}