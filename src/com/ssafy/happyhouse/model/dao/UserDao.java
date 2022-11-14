package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.dto.UserInfo;

public interface UserDao {
	public UserInfo login(String id, String pw) throws Exception;

	public void modify(UserInfo userinfo) throws Exception;

	public List<UserInfo> search() throws Exception;

	public void signup(UserInfo userinfo) throws Exception;

	public String findpw(String id, String name) throws Exception;
}
