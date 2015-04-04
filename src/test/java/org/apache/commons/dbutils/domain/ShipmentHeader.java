package org.apache.commons.dbutils.domain;

import java.util.Date;

public class ShipmentHeader {
	private int oubShipmentHeaderId;
	private String shipmentNo;
	private String launchNo;
	private String orderType;
	private String customerName;
	private Date updatedDtmLoc;
	
	public int getOubShipmentHeaderId() {
		return oubShipmentHeaderId;
	}
	public void setOubShipmentHeaderId(int oubShipmentHeaderId) {
		this.oubShipmentHeaderId = oubShipmentHeaderId;
	}
	public String getShipmentNo() {
		return shipmentNo;
	}
	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}
	public String getLaunchNo() {
		return launchNo;
	}
	public void setLaunchNo(String launchNo) {
		this.launchNo = launchNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getUpdatedDtmLoc() {
		return updatedDtmLoc;
	}
	public void setUpdatedDtmLoc(Date updatedDtmLoc) {
		this.updatedDtmLoc = updatedDtmLoc;
	}
	
	
}
