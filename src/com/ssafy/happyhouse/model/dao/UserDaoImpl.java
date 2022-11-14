package com.ssafy.happyhouse.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.util.DBUtil;

public class UserDaoImpl implements UserDao {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	@Override
	public UserInfo login(String id, String pw) throws Exception {
		UserInfo dto = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user where id = ? and pw = ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pw);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				dto = new UserInfo();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
			}
			if(dto == null)
				System.out.println("dto is null");
			else
				System.out.println("dto is not null");
		} catch (Exception e) {
			e.printStackTrace();
			dto = null;
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pst);
			DBUtil.close(conn);
		}
		return dto;
	}

	@Override
	public void modify(UserInfo userinfo) throws Exception {
	
		UserInfo dto = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set pw = ? , name = ?, address = ?, phone = ? where id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userinfo.getPw());
			pst.setString(2, userinfo.getName());
			pst.setString(3, userinfo.getAddress());
			pst.setString(4, userinfo.getPhone());
			pst.setString(5, userinfo.getId());
			pst.execute();
						
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pst);
			DBUtil.close(conn);
		}

	}

	@Override
	public List<UserInfo> search() throws Exception {
		List<UserInfo> list = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<>();
			if(rs.next()) {
				UserInfo dto = new UserInfo();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				list.add(dto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pst);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public void signup(UserInfo userinfo) throws Exception {
		try {
			conn = DBUtil.getConnection();
			String sql = "insert user value(?,?,?,?,?);";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, userinfo.getId());
			pst.setString(2, userinfo.getPw());
			pst.setString(3, userinfo.getName());
			pst.setString(4, userinfo.getAddress());
			pst.setString(5, userinfo.getPhone());
			
			pst.execute();
		
		}catch (Exception e) {
			e.printStackTrace();

		}finally {
			DBUtil.close(conn);
			DBUtil.close(pst);
		}
	}

	@Override
	public String findpw(String id, String name) throws Exception {
		String pw = "";
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT pw FROM user WHERE name = ? AND id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, id);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				pw = rs.getString("pw");
			}
		} catch (Exception e) {
			e.printStackTrace();
			pw ="";
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pst);
			DBUtil.close(conn);
		}
		return pw;
	}

}
