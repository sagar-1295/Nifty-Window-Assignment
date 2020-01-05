package com.niftyWindow.assignment.dto;

public class PrepareData implements Comparable<PrepareData>{

	private String userName;
	private Integer msgCount;
	private String messsage;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	@Override
	public int compareTo(PrepareData o) {
		return this.getMsgCount().compareTo(o.getMsgCount());
	}

}
