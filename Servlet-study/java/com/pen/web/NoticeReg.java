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
		
		//request.setCharacterEncoding("UTF-8"); // �������� ��û�� ���ڿ� ���ڵ� ��� ����.
		response.setCharacterEncoding("UTF-8"); // �� ������ ���ڿ� ���ڵ� ��� ����.
		response.setContentType("text/html; charset=UTF-8"); // �������� ���� ����Ʈ�� �� �������� �ؼ��ϴ� ��� ����.
		
		PrintWriter out = response.getWriter(); 
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
		
	}
}
