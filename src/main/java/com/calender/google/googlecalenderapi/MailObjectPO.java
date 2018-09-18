package com.calender.google.googlecalenderapi;

import java.util.List;

public class MailObjectPO {
	private String From;
	private String id;
	private String snippet;
	private boolean attachmentstatus;
	private String body;
	private String Date;
	private String Subject;
	private String To;
	private String cc;
	private List<String> attachmentname;
	private String Deliveredto;
	
	public String getDeliveredto() {
		return Deliveredto;
	}
	public void setDeliveredto(String deliveredto) {
		Deliveredto = deliveredto;
	}
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public boolean isAttachmentstatus() {
		return attachmentstatus;
	}
	public void setAttachmentstatus(boolean attachmentstatus) {
		this.attachmentstatus = attachmentstatus;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public List<String> getAttachmentname() {
		return attachmentname;
	}
	public void setAttachmentname(List<String> attachmentname) {
		this.attachmentname = attachmentname;
	}
	
}