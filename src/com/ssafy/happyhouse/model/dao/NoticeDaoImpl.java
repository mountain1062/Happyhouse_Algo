package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.DBUtil;

public class NoticeDaoImpl {

	public List<NoticeDto> selectAll() throws SQLException{
		List<NoticeDto> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from notice where delYn = 'N' order by no desc limit 20");
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				NoticeDto ndt = new NoticeDto();
				ndt.setNo(rs.getInt("no"));
				ndt.setTitle(rs.getString("title"));
				ndt.setContent(rs.getString("content"));
				ndt.setWriter(rs.getString("writer"));
				ndt.setType(rs.getString("type"));
				list.add(ndt);
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}

	public void write(NoticeDto noticeDto) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into notice (title,content,writer) values (?,?,?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, noticeDto.getTitle());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setString(3, noticeDto.getWriter());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}

	public NoticeDto selectOne(String no) throws SQLException{
		NoticeDto ndt = new NoticeDto();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from notice where no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ndt.setNo(rs.getInt("no"));
				ndt.setTitle(rs.getString("title"));
				ndt.setContent(rs.getString("content"));
				ndt.setWriter(rs.getString("writer"));
				ndt.setType(rs.getString("type"));
			}
			return ndt;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}

	public void remove(String no) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update notice set delYn = 'Y' where no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, no);
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}

	public void update(NoticeDto noticeDto) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
//			update notice set delYn = 'Y' where no = ?
			sql.append("update notice set title = ?,content= ? where no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, noticeDto.getTitle());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setInt(3, noticeDto.getNo());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}

}
