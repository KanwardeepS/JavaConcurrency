package com.blockingqueue.concurrency;


public class Message {

	private String msg;

	public Message( final String str ) {
		this.msg = str;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg( final String str ) {
		this.msg = str;
	}

}
