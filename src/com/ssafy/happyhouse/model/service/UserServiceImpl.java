package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dao.UserDao;
import com.ssafy.happyhouse.model.dao.UserDaoImpl;
import com.ssafy.happyhouse.model.dto.UserInfo;

public class UserServiceImpl implements UserService {
	UserDao dao = null;
	
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}

	@Override
	public UserInfo login(String id, String pw) throws Exception {
		if(id == null || pw == null)
			throw new Exception();
		return dao.login(id, pw);
	}

	@Override
	public void modify(UserInfo userinfo) throws Exception {
		dao.modify(userinfo);		
	}

	@Override
	public List<UserInfo> search() throws Exception {
		return dao.search();
	}

	@Override
	public void signup(UserInfo userinfo) throws Exception {
		dao.signup(userinfo);
	}

	@Override
	public String findpw(String id, String name) throws Exception {
		return dao.findpw(id, name);
	}

}
