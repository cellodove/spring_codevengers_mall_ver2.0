package com.kubg.dto;

import java.sql.Date;

public class CartListDTO {

	private int cartnum;
	private String mem_id;
	private int gdsnum;
	private int cartstock;
	private Date adddate;
	
	private int cartlistnum;
	private String gdsname;
	private int gdsprice;
	private String gdsthumbimg;
	

	public int getCartlistnum() {
		return cartlistnum;
	}
	public void setCartlistnum(int cartlistnum) {
		this.cartlistnum = cartlistnum;
	}
	public String getGdsname() {
		return gdsname;
	}
	public void setGdsname(String gdsname) {
		this.gdsname = gdsname;
	}
	public int getGdsprice() {
		return gdsprice;
	}
	public void setGdsprice(int gdsprice) {
		this.gdsprice = gdsprice;
	}
	public String getGdsthumbimg() {
		return gdsthumbimg;
	}
	public void setGdsthumbimg(String gdsthumbimg) {
		this.gdsthumbimg = gdsthumbimg;
	}
	public int getCartnum() {
		return cartnum;
	}
	public void setCartnum(int cartnum) {
		this.cartnum = cartnum;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	
}
