package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;

@WebServlet("/house.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HouseService houseService;
	
	
	public Controller() {
		houseService=new HouseServiceImpl();
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = "/WEB-INF";
		String act=request.getParameter("action");
		
		if("listview".equals(act)) {
			listview(request,response,root,path);
		}else if("listsearch".equals(act)) {
			listSearch(request,response,root,path);
		}else {
			request.getRequestDispatcher("/").forward(request, response);
		}
	}
	
	private void listSearch(HttpServletRequest request, HttpServletResponse response, String root, String path) {
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		String[] types=request.getParameterValues("types");
		boolean[] tmp = new boolean[4];
		for(String s : types) {
			tmp[Integer.parseInt(s)]=true;
		}
		HousePageBean pageBean= new HousePageBean();
		if("name".equals(key)) {
			pageBean.setAptname(word);
		}else if("dong".equals(key)) {
			pageBean.setDong(word);
		}
		pageBean.setSearchType(tmp);
		try {
			//HousePageBean 처리하기
			List<HouseDeal> list = houseService.searchAll(pageBean);
			request.setAttribute("apartments", list);
			request.getRequestDispatcher("/").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listview(HttpServletRequest request, HttpServletResponse response, String root, String path) {
		try {
			List<HouseInfo> list = houseService.searchAllHouseInfo();
			request.setAttribute("apartments", list);
			request.getRequestDispatcher("/").forward(request, response);
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

