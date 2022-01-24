package com.pen.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Nana extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); // 웹 서버의 응답시 문자열 인코딩 방식 설정.
		response.setContentType("text/html; charset=UTF-8"); // 웹서버가 보낸 컨텐트를 웹 브라우저가 해석하는 방식 설정.
		
		PrintWriter out = response.getWriter(); 
		
		int cnt = 1; // 기본값
		
		String cnt_ = request.getParameter("cnt");
		if (cnt_ != null && !cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		
		for (int i = 0; i < cnt; i++)
			out.println("안녕하세요! <br>");
	}
}
