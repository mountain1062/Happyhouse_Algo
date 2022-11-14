package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.service.NoticeServiceImpl;
@WebServlet("/notice.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeServiceImpl noticeServiceImpl=new NoticeServiceImpl();
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = "/WEB-INF";
		String act=request.getParameter("action");
		//세션값 처리하기
		if("list".equals(act)) {
			listView(request,response,root,path);
		}else if("writeform".equals(act)) {
			HttpSession session = request.getSession();
			
			if(session.getAttribute("userinfo")==null) {
				response.sendRedirect(root+"/");
			}else {
				request.getRequestDispatcher(path+"/notice/writeform.jsp").forward(request, response);				
			}
		}else if("write".equals(act)) {
			System.out.println("no = "+request.getParameter("no"));
			if("".equals(request.getParameter("no")))
				doWrite(request,response,root,path);
			else
				doUpdate(request,response,root,path);
		}else if("remove".equals(act)) {
			doRemove(request,response,root,path);
		}else if("updatepage".equals(act)) {
			updateDetail(request,response,root,path);
		}else if("update".equals(act)) {
			doUpdate(request,response,root,path);
		}else if("detail".equals(act)) {
			showDetail(request,response,root,path);
		}
	}
	//상세보기
	private void updateDetail(HttpServletRequest request, HttpServletResponse response, String root, String path) {
		String no =request.getParameter("no");
		try {
			NoticeDto ndt = noticeServiceImpl.selectOne(no);
			HttpSession session = request.getSession();
			if(((UserInfo)session.getAttribute("userinfo")).getId().equals(ndt.getWriter())) {
				request.setAttribute("article", ndt);
				request.getRequestDispatcher(path+"/notice/writeform.jsp").forward(request, response);
			}else {
				response.sendRedirect(root+path+"/notice/noticelist.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//상세보기
	private void showDetail(HttpServletRequest request, HttpServletResponse response, String root, String path) {
		String no =request.getParameter("no");
		try {
			NoticeDto ndt = noticeServiceImpl.selectOne(no);
			request.setAttribute("article", ndt);
			request.getRequestDispatcher(path+"/notice/noticedetail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//글 업데이트
	private void doUpdate(HttpServletRequest request, HttpServletResponse response, String root, String path) {
		HttpSession session = request.getSession();
		//세션에서 값 가져오기
		int no = Integer.parseInt(request.getParameter("no"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String ses = ((UserInfo)session.getAttribute("userinfo")).getId();
		try {
			noticeServiceImpl.update(new NoticeDto(no,title,content,ses));
			listView(request,response,root,path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//글 삭제
	private void doRemove(HttpServletRequest request, HttpServletResponse response, String root, String path) throws ServletException, IOException{
		String no = request.getParameter("no");
		try {
			noticeServiceImpl.remove(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listView(request,response,root,path);
	}
	//글 쓰기
	private void doWrite(HttpServletRequest request, HttpServletResponse response, String root, String path) throws ServletException, IOException{
		HttpSession session = request.getSession();
		//세션에서 값 가져오기
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String ses = ((UserInfo)session.getAttribute("userinfo")).getId();
		try {
			noticeServiceImpl.write(new NoticeDto(title,content,ses));
			listView(request,response,root,path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//공지사항 리스트 보기
	private void listView(HttpServletRequest request, HttpServletResponse response, String root, String path) throws ServletException, IOException{
		try {
			List<NoticeDto> list = noticeServiceImpl.selectAll();
			request.setAttribute("noticelist", list);
			request.getRequestDispatcher(path+"/notice/noticelist.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request,response);
	}
}
