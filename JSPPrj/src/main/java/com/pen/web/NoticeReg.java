package com.pen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet{
	 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8"); // 웹서버의 요청시 문자열 인코딩 방식 설정.
		response.setCharacterEncoding("UTF-8"); // 웹 서버의 문자열 인코딩 방식 설정.
		response.setContentType("text/html; charset=UTF-8"); // 웹서버가 보낸 컨텐트를 웹 브라우저가 해석하는 방식 설정.
		
		PrintWriter out = response.getWriter(); 
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
		
	}
}
