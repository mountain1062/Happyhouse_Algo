package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.dto.InterestRegion;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.util.DBUtil;

public class HouseDaoImpl implements HouseDao, HouseInfoDao, UserInfoDao {
	private Map<String, HouseInfo> houseInfo;
	private Map<String, List<HouseDeal>> deals;
	private int size;
	private List<HouseDeal>search;
	private String[] searchType= {HouseDeal.APT_DEAL, HouseDeal.APT_RENT, HouseDeal.HOUSE_DEAL, HouseDeal.HOUSE_RENT};
	public HouseDaoImpl() {
	}
	/**
	 * 아파트 정보와 아파트 거래 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() { }
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select * from housedeal where 1=1");
			boolean[] type = bean.getSearchType();
			sql.append(" and type in (");
			for(int i=0,size=type.length;i<size;i++) {
				if(type[i]) {
					sql.append(i+1);
					sql.append(",");
				}
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			if(dong !=null && !dong.trim().equals("")) {
				sql.append(" and dong like ?");
			}else if(aptName !=null && !aptName.trim().equals("")) {
				sql.append(" and aptname like ?");
			}
			//
			sql.append(" limit 100");
			pstmt = con.prepareStatement(sql.toString());
			if(dong !=null && !dong.trim().equals("")) {
				pstmt.setString(1, "%"+dong+"%");
			}else if(aptName !=null && !aptName.trim().equals("")) {
				pstmt.setString(1, "%"+aptName+"%");
			}
			
			rs=pstmt.executeQuery();
			search = new LinkedList<>();
			while(rs.next()) {
				HouseDeal deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong (rs.getString("dong"));
				deal.setAptName (rs.getString("AptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				search.add(deal);
			}
			return search;
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
	}
	
	/**
	 * 주택 거래 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * 법정동+아파트명을 이용하여 HouseInfo에서 건축연도, 법정코드, 지번, 이미지 정보를 찾아서 HouseDeal에 setting한 정보를 반환한다. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) throws SQLException {
		String sql ="select * from housedeal where no = "+ no;
		try (Connection con = DBUtil.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				HouseDeal deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong (rs.getString("dong"));
				deal.setAptName (rs.getString("AptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				return deal;
			}
			return null;
		}
	}
	
	public List<HouseInfo> searchAllHouseInfo() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from houseinfo limit 100";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<HouseInfo> list = new LinkedList<HouseInfo>();
			while(rs.next()) {
				HouseInfo info = new HouseInfo(rs.getInt("no"),
						rs.getString("dong"),
						rs.getString("aptname"),
						rs.getInt("code"),
						rs.getInt("buildYear"),
						rs.getString("jibun"),"","",
						rs.getString("img"));
				list.add(info);
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public List<UserInfo> searchAllUser() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from people";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<UserInfo> list = new LinkedList<UserInfo>();
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				//UserInfo info = new UserInfo(no, name, id, pw, address, phone);
				//list.add(info);
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public void insertUser(UserInfo p) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into people(name, id, pw, address, phone) values (?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getId());
			stmt.setString(3, p.getPw());
			stmt.setString(4, p.getAddress());
			stmt.setString(5, p.getPhone());
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public void updateUser(UserInfo p) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update people set name = ?, id = ?, pw = ?, address = ?, phone = ? where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getId());
			stmt.setString(3, p.getPw());
			stmt.setString(4, p.getAddress());
			stmt.setString(5, p.getPhone());
			stmt.setInt(6, p.getNo());
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public void deleteUser(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from people where no = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	
}





