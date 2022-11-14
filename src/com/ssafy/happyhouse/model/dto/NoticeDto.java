package com.ssafy.happyhouse.model.dto;

public class NoticeDto {
	private int no;
	private String title;
	private String content;
	private String type;
	private String writer;
	public NoticeDto() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", title=" + title + ", content=" + content + ", type=" + type + "]";
	}
	
	public NoticeDto(int no,String title, String content,String writer) {
		this.no=no;
		this.title = title;
		this.content = content;
		this.writer=writer;
	}
	
	public NoticeDto(String title, String content,String writer) {
		this.title = title;
		this.content = content;
		this.writer=writer;
	}
	public NoticeDto(int no, String title, String content, String type, String writer) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.type = type;
		this.writer = writer;
	}
	public int getNo() {
		return no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
