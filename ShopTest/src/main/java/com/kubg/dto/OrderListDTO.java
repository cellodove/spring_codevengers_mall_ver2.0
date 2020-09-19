package com.kubg.dto;

import java.sql.Date;

public class OrderListDTO {
	private String orderid;
	private String mem_id;
	private String orderrec;
	private String useraddr1;
	private String useraddr2;
	private String useraddr3;
	private String orderphon;
	private int amount;
	private Date orderdate;
	
	private int orderdetailsnum;
	private int gdsnum;
	private int cartstock;
	
	private String gdsname;
	private String gdsthumbimg;
	private int gdsprice;
	
	private String delivery;
	
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getOrderrec() {
		return orderrec;
	}
	public void setOrderrec(String orderrec) {
		this.orderrec = orderrec;
	}
	public String getUseraddr1() {
		return useraddr1;
	}
	public void setUseraddr1(String useraddr1) {
		this.useraddr1 = useraddr1;
	}
	public String getUseraddr2() {
		return useraddr2;
	}
	public void setUseraddr2(String useraddr2) {
		this.useraddr2 = useraddr2;
	}
	public String getUseraddr3() {
		return useraddr3;
	}
	public void setUseraddr3(String useraddr3) {
		this.useraddr3 = useraddr3;
	}
	public String getOrderphon() {
		return orderphon;
	}
	public void setOrderphon(String orderphon) {
		this.orderphon = orderphon;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public int getOrderdetailsnum() {
		return orderdetailsnum;
	}
	public void setOrderdetailsnum(int orderdetailsnum) {
		this.orderdetailsnum = orderdetailsnum;
	}
	public int getGdsnum() {
		return gdsnum;
	}
	public void setGdsnum(int gdsnum) {
		this.gdsnum = gdsnum;
	}
	public int getCartstock() {
		return cartstock;
	}
	public void setCartstock(int cartstock) {
		this.cartstock = cartstock;
	}
	public String getGdsname() {
		return gdsname;
	}
	public void setGdsname(String gdsname) {
		this.gdsname = gdsname;
	}
	public String getGdsthumbimg() {
		return gdsthumbimg;
	}
	public void setGdsthumbimg(String gdsthumbimg) {
		this.gdsthumbimg = gdsthumbimg;
	}
	public int getGdsprice() {
		return gdsprice;
	}
	public void setGdsprice(int gdsprice) {
		this.gdsprice = gdsprice;
	}
	
}
