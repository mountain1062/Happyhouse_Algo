package com.ssafy.happyhouse.model.dto;

public class InterestRegion {
	private int no;
	private String code;
	private String city;
	private String gu;
	private String dong;
	private String ri;
	private String created;
	private String deleted;
	public InterestRegion() {
		// TODO Auto-generated constructor stub
	}
	public InterestRegion(int no, String code, String city, String gu, String dong, String ri, String created,
			String deleted) {
		this.no = no;
		this.code = code;
		this.city = city;
		this.gu = gu;
		this.dong = dong;
		this.ri = ri;
		this.created = created;
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "InterestRegion [no=" + no + ", code=" + code + ", city=" + city + ", gu=" + gu + ", dong=" + dong
				+ ", ri=" + ri + ", created=" + created + ", deleted=" + deleted + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
