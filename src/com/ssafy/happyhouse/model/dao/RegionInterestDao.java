package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.InterestRegion;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.util.DBUtil;

public class RegionInterestDao {
	public List<InterestRegion> search(UserInfo ui) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DBUtil.getConnection();
			String sql = "select * from regioninterest where gu like ?";
			pstmt = con.prepareStatement(sql);
			String gu = ui.getAddress().trim().split(" ")[1];
			pstmt.setString(1, "%"+gu+"%");
			rs=pstmt.executeQuery();
			List<InterestRegion> list = new LinkedList<InterestRegion>();
			while(rs.next()) {
				InterestRegion ir = new InterestRegion();
				ir.setCity(rs.getString("city"));
				ir.setGu(rs.getString("gu"));
				ir.setDong(rs.getString("dong"));
				ir.setNo(rs.getInt("no"));
				ir.setCreated(rs.getString("created"));
				list.add(ir);
			}
			list.remove(0);
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}
}
