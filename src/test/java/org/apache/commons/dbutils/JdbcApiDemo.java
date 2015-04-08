package org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JdbcApiDemo {
	
	@Test
	public void batchTest() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int[] i = null;
		try {    
			conn = JdbcUtils.getConnectionDirect();
			String sql = "insert into oub_shipment_header(shipment_no,launch_no,order_type,customer_name,updated_dtm_loc) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for (int n = 0; n < 1000; n++) {
				ps.setString(1, "shipment_no" + n);
				ps.setString(2, "launch_no" + n);
				ps.setInt(3, n);
				ps.setString(4, "customer_name" + n);
				ps.setDate(5,new Date(System.currentTimeMillis()));
				ps.addBatch();
			}
			i = ps.executeBatch();
		} finally {
			JdbcUtils.release(conn, ps, rs);
		}
		System.out.println(i);
	}
	
	public void read() throws SQLException{
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);//两个参数设置结果集为可滚动的、只读的
			rs=st.executeQuery("select id ,name,birthday,money from T_Users where id<20");
			while(rs.next()){
				System.out.println(rs.getInt("id")+"    "+rs.getObject("name")+"   "+rs.getObject("money"));
			}
            //rs.beforeFirst()定位到第一行前面; rs.afterLast()定位到最后一行后面;rs.first()定位到第一行;rs.isFirst()判断是否是第一行;rs.last();定位到最后一行rs.isLast()判断是否是最后一行 
			rs.absolute(5);//具体定位到结果集的第五行
			System.out.println("----------------------");
			while(rs.previous()){//previous()向前读取记录
				System.out.println(rs.getInt("id")+"    "+rs.getObject("name")+"   "+rs.getObject("money"));
			}
		}
		finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}
