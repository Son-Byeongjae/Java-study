package com.calculator.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			exp = (exp == null)? exp : exp.substring(0, exp.length()-1);
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
			exp += (operator==null)?"":operator;
			exp += (dot==null)?"":dot;			
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.equals("C"))
			expCookie.setMaxAge(0);
		response.addCookie(expCookie);	
		response.sendRedirect("calcpage");
	}
			
}
