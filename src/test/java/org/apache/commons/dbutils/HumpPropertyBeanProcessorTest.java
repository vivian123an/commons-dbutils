package org.apache.commons.dbutils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.domain.ShipmentHeader;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

public class HumpPropertyBeanProcessorTest {

	@Test
    public void getByBeanProcessExt() throws SQLException{
    	QueryRunner qr = new QueryRunner();
        String sql = "select * from oub_shipment_header ";
        
        ResultSetHandler<List<ShipmentHeader>> rsh = 
        		new BeanListHandler<ShipmentHeader>(ShipmentHeader.class, 
        				new BasicRowProcessor(new HumpPropertyBeanProcessor()));
        
        List<ShipmentHeader> list = qr.query(JdbcUtils.getConnectionDirect(),sql, rsh);
    	for(ShipmentHeader shipment : list){
         	System.out.println(shipment.getOubShipmentHeaderId());
         	System.out.println(shipment.getLaunchNo());
         	System.out.println(shipment.getShipmentNo());
         	System.out.println(shipment.getCustomerName());
         	System.out.println(shipment.getUpdatedDtmLoc());
        }
    }
}
