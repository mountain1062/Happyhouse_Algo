package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.UserServiceImpl;

@WebServlet("/user.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userservice = null;

	public void init() {
		userservice = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		String act = request.getParameter("act");

		if ("main".equals(act)) {
			redirect(response, root, path);
		} else if ("login".equals(act)) {
			login(request, response);
		} else if ("logout".equals(act)) {
			logout(request, response);
		} else if ("signup".equals(act)) {
			signup(request, response);
		} else if ("mvsignup".equals(act)) {
			path = "/WEB-INF/page/signup.jsp";
			forword(request, response, path);
		} else if ("findpw".equals(act)) {
			findpw(request, response);
		} else if ("mvfindpw".equals(act)) {
			path = "/WEB-INF/page/findUserpw.jsp";
			forword(request, response, path);
		} else if ("mvmodify".equals(act)) {
			path = "/WEB-INF/page/modify.jsp";
			forword(request, response, path);
		} else if ("modify".equals(act)) {
			modify(request, response);
		} else if ("userview".equals(act)) {
			path = "/WEB-INF/page/view.jsp";
			forword(request, response, path);
		} else {
			path = "/WEB-INF/error/error.jsp";
			request.setAttribute("msg", "올바르지 않은 접근입니다.");
			forword(request, response, path);
		}
	}

//	private void duplchk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String path = "/WEB-INF/page/signup.jsp";
//		String id = request.getParameter("id");
//		try {
//			String foundid = userservice.
//			if(foundid.equals(""))
//				request.setAttribute("duplres", null);
//			else
//				request.setAttribute("duplres", foundid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		forword(request, response, path);
//
//	}


	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/page/view.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		UserInfo dto = new UserInfo();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setAddress(address);
		dto.setPhone(phone);
		
		try {
			userservice.modify(dto);
			HttpSession ssesion = request.getSession();
			ssesion.removeAttribute("userinfo");
			ssesion.setAttribute("userinfo", dto);
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/error/error.jsp";
			request.setAttribute("msg", "회원 정보 수정 중 오류 발생");
		}

		forword(request, response, path);

	}

	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/page/resultpage.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		UserInfo dto = new UserInfo();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setAddress(address);
		dto.setPhone(phone);
		try {
			userservice.signup(dto);
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/error/error.jsp";
			request.setAttribute("msg", "회원 가입 중 오류 발생");
		}

		forword(request, response, path);

	}

	private void findpw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/page/foundRes.jsp";
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		try {
			String pw = userservice.findpw(id, name);
			if (pw.equals("")) {
				request.setAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요");

			} else {
				request.setAttribute("foundPW", pw);
			}

		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/error/error.jsp";
			request.setAttribute("msg", "비밀 번호 찾기 중 오류 발생");
		}

		forword(request, response, path);

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		try {
			UserInfo userDto = userservice.login(id, pw);
			if (userDto != null) {
				HttpSession ssesion = request.getSession();
				ssesion.setAttribute("userinfo", userDto);
			} else {
				request.setAttribute("msg", "아이디 / 비밀번호를 확인해 주세요");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		forword(request, response, path);

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		session.invalidate();
		redirect(response, root, path);
	}

	private void redirect(HttpServletResponse response, String root, String path) throws IOException {
		response.sendRedirect(root + path);
	}

	private void forword(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}
