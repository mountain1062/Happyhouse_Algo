package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.happyhouse.model.dto.NoticeDto;

public class NoticeServiceImpl {
	private NoticeDaoImpl noticeDaoImpl = new NoticeDaoImpl();
	public List<NoticeDto> selectAll() throws SQLException{
		return noticeDaoImpl.selectAll();
	}

	public void write(NoticeDto noticeDto) throws Exception{
		if(noticeDto.getTitle() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		noticeDaoImpl.write(noticeDto);
	}

	public NoticeDto selectOne(String no) throws SQLException{
		return noticeDaoImpl.selectOne(no);
	}

	public void remove(String no) throws SQLException{
		noticeDaoImpl.remove(no);
	}

	public void update(NoticeDto noticeDto) throws Exception{
		if(noticeDto.getNo() < 0 ||noticeDto.getTitle() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		noticeDaoImpl.update(noticeDto);
	}
}
