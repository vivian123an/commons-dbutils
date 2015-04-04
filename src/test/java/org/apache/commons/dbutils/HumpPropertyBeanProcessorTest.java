package org.apache.commons.dbutils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.domain.ShipmentHeader;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

/**
 * create table `oub_shipment_header` (
	`oub_shipment_header_id` int (11),
	`shipment_no` varchar (96),
	`launch_no` varchar (96),
	`order_type` varchar (96),
	`customer_name` varchar (96),
	`updated_dtm_loc` date 
); 
insert into `oub_shipment_header` (`oub_shipment_header_id`, `shipment_no`, `launch_no`, `order_type`, `customer_name`, `updated_dtm_loc`) values('1','123456','10006','1','陈先生','2015-04-03');
insert into `oub_shipment_header` (`oub_shipment_header_id`, `shipment_no`, `launch_no`, `order_type`, `customer_name`, `updated_dtm_loc`) values('2','100002','10001','2','谢女士','2015-04-02');

 * @author pingan
 *
 */

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
