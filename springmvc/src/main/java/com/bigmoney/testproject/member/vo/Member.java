package com.bigmoney.testproject.member.vo;

public class Member {
	private String userName; //유저명
	private String userPsw;	//유저비밀번호
	private String email;	//유저이메일
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPsw() {
		return userPsw;
	}
	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
