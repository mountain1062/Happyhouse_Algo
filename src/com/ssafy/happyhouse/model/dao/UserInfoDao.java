package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.UserInfo;

public interface UserInfoDao {
	public List<UserInfo> searchAllUser() throws SQLException;
	public void updateUser(UserInfo p) throws SQLException;
	public void insertUser(UserInfo p) throws SQLException;
	public void deleteUser(int no) throws SQLException;
}
