package com.kubg.dto;

import java.sql.Date;

public class ReplyDTO {

	private int gdsnum;
	private String mem_id;
	private int repnum;
	private String repcon;
	private Date repdate;
	

	public int getGdsnum() {
		return gdsnum;
	}
	public void setGdsnum(int gdsnum) {
		this.gdsnum = gdsnum;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getRepnum() {
		return repnum;
	}
	public void setRepnum(int repnum) {
		this.repnum = repnum;
	}
	public String getRepcon() {
		return repcon;
	}
	public void setRepcon(String repcon) {
		this.repcon = repcon;
	}
	public Date getRepdate() {
		return repdate;
	}
	public void setRepdate(Date repdate) {
		this.repdate = repdate;
	}
	
}
