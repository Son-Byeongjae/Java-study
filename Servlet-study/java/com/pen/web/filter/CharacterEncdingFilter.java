package com.pen.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncdingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8"); // 서블릿 컨테이너 오르기 전
		filter.doFilter(request, response); // 다음 서블릿 실행.
		// 서블릿 컨테이너에서 실행 후
	}
}
