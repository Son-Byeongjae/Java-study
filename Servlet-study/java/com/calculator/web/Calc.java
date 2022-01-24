package com.calculator.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc")
public class Calc extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		if (v_ != null && !v_.equals("")) v = Integer.parseInt(v_);
		
		
		int result = 0;
		if (op.equals("=")){
			//int x = (Integer)application.getAttribute("value");
			//String operator = (String)application.getAttribute("op");
			
			//int x = (Integer)session.getAttribute("value");
			//String operator = (String)session.getAttribute("op");
			
			int x = 0;
			String operator = "";
			for(Cookie cookie : cookies)
				if (cookie.getName().equals("value")) {
					x = Integer.parseInt(cookie.getValue());
					break;
				}
			
			for(Cookie cookie : cookies)
				if (cookie.getName().equals("op")) {
					operator = cookie.getValue();
					break;
				}
			
			
			if (operator.equals("+"))
				result = x + v;
			else 
				result = x - v;	
					
			session.invalidate();
			out.println("연산 결과값은" + result + "입니다.");
		}
		else {
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
		
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
		
			valueCookie.setPath("/calc");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc");
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc.html");
		}
			
		
		
		
		/*
		 * String[] nums = request.getParameterValues("num"); String op =
		 * request.getParameter("operator");
		 * 
		 * int result = 0; if (op.equals("add")) { for(int i =0; i < nums.length; i++)
		 * result += Integer.parseInt(nums[i]); } else { for(int i =0; i < nums.length;
		 * i++) result -= Integer.parseInt(nums[i]); }
		 */
		
		
		/*
		 * String x_ = request.getParameter("x"); String y_ = request.getParameter("y");
		 * 
		 * int x = 0, y = 0; if ( x_ != null && !x_.equals("") ) x =
		 * Integer.parseInt(x_); if ( y_ != null && !y_.equals("") ) y =
		 * Integer.parseInt(y_);
		 * 
		 * int result = 0; String op = request.getParameter("operator"); if
		 * (op.equals("add")) result = x+y; else if (op.equals("sub")) result = x-y;
		 */
		
	}
}
