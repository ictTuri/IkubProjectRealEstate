package com.realestate.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorFormat {
	private String message;
	private Date timeStamp=new Date(); 
	private String desc;
	private String suggestion;
}
