package com.calculator.web;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String exp = "0";
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null)
			for(Cookie cookie : cookies)
				if (cookie.getName().equals("exp")) {
					exp = cookie.getValue();
					exp = exp.equals("") ? "0" : exp; 
					break;
				}
		
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<style>");
		out.write("input {");
		out.write("	width:50px;");
		out.write("	height:50px;");
		out.write("}");

		out.write(".output {");
		out.write("	height:50px;");
		out.write("	background: #e9e9e9;");
		out.write("	font-size:24px;");
		out.write("	font-weight: bold;");
		out.write("	text-align: right;");
		out.write("	padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("<body>");
		out.write("	<form method=\"post\">");
		out.write("		<table>");
		out.write("			<tr>");
		out.printf("				<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" value=\"CE\" name=\"operator\"></td>");
		out.write("				<td><input type=\"submit\" value=\"C\" name=\"operator\"></td>");
		out.write("				<td><input type=\"submit\" value=\"BS\" name=\"operator\"></td>");
		out.write("				<td><input type=\"submit\" value=\"/\" name=\"operator\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" value=\"7\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"8\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"9\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"*\" name=\"operator\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" value=\"4\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"5\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"6\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"-\" name=\"operator\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" value=\"1\" name=\"value\"></td>");
		out.write("				<td><input type=\"submit\" value=\"2\" name=\"value\"></td>");
		out.write("			<td><input type=\"submit\" value=\"3\" name=\"value\"></td>");
		out.write("			<td><input type=\"submit\" value=\"+\" name=\"operator\"></td>");
		out.write("		</tr>");
		out.write("		<tr>");
		out.write("			<td></td>");
		out.write("			<td><input type=\"submit\" value=\"0\" name=\"value\"></td>");
		out.write("			<td></td>");
		out.write("			<td><input type=\"submit\" value=\"=\" name=\"operator\"></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("	</form>");
		out.write("</body>");
		out.write("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		if (cookies != null)
			for(Cookie cookie : cookies)
				if (cookie.getName().equals("exp")) {
					exp = cookie.getValue();
					break;
				}
		
		if(operator != null && operator.equals("=")) {
			
			try {
				char op = exp.charAt(0) == '-'? '-' :'+';
				String operand = "0";
				int result = 0;
				for ( int i = 0; i < exp.length(); i++ )
				{
					if ( '0' <= exp.charAt(i) && exp.charAt(i) <= '9')
						operand += exp.charAt(i);
					else 
					{
						if ( op == '+')
							result += Integer.parseInt(operand);
						else if (op == '-')
							result -= Integer.parseInt(operand);
						else if (op == '*')
							result *= Integer.parseInt(operand);
						else if (op == '/') {
							result /= Integer.parseInt(operand);
						}
						operand = "";
						op = exp.charAt(i);
					}	
				}
				if ( op == '+')
					result += Integer.parseInt(operand);
				else if (op == '-')
					result -= Integer.parseInt(operand);
				else if (op == '*')
					result *= Integer.parseInt(operand);
				else if (op == '/') {
					result /= Integer.parseInt(operand);
				}
				exp = String.valueOf(result);
			}
			catch (ArithmeticException e) {
				exp = "0으로나눌수없음";
			}
			
		}
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		else if(operator != null && operator.equals("BS")) {
			
			int len = exp.length()-1;
			if (len < 0) len = 0;
			exp = (exp == null)? exp : exp.substring(0, len);
		}
		else if(operator != null && operator.equals("CE")) {
			int i;
			for (i = exp.length()-1; i >= 0; i--)
			{
				if (exp.charAt(i)=='+' || exp.charAt(i)=='-'|| exp.charAt(i)=='*' || exp.charAt(i)=='/')
					break;
			}
			exp = exp.substring(0, i+1);
			if (exp.equals("-")) exp="";
		}
		else {
			exp += (value==null)?"":value;
			
			if (operator != null)
			{
				if (exp.isEmpty()) 
					exp += "0" + operator;
				if ( !exp.isEmpty() ) {
					char lastChar = exp.charAt(exp.length()-1); 
					if ( lastChar == '-' || lastChar == '+' || lastChar == '*' || lastChar == '/')
						exp = exp.substring(0,exp.length()-1) + operator;
					else exp += operator;
				}	
			}
			else exp += "";
			
			exp += (dot==null)?"":dot;			
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.equals("C")) expCookie.setMaxAge(0);
		expCookie.setPath("/calculator");
		
		response.addCookie(expCookie);	
		response.sendRedirect("calculator");
	}
}