package com.example.exception;

import java.time.LocalDateTime;
import java.util.Date;




public class ExInfo {
	private String exCode;
	
	private String exMsg;
	
	private LocalDateTime date;

	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}

	
	
	

}
