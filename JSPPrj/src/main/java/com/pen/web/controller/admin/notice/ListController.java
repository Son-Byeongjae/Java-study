package com.pen.web.controller.admin.notice;

import java.io.IOException;
import java.net.http.HttpClient.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pen.web.entity.Notice;
import com.pen.web.entity.NoticeView;
import com.pen.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		String ids_ = request.getParameter("ids");
		String[] ids = ids_.split(" ");

		NoticeService service = new NoticeService();
		int[] ids;
		int result = 0;
		
		switch(cmd) {
		case "ÀÏ°ý°ø°³":
			ids = new int[openIds.length];
			for (int i = 0; i < openIds.length; i++)
				ids[i] = Integer.parseInt(openIds[i]);
			result = service.pubNoticeAll(ids);
			break;
			
		case "ÀÏ°ý»èÁ¦":
			ids = new int[delIds.length];
			for (int i = 0; i < delIds.length; i++)
				ids[i] = Integer.parseInt(delIds[i]);				
			
			result = service.removeNoticeAll(ids);
			break;
		}
		
		response.sendRedirect("list");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if (field_ != null && !field_.equals("") )
			field = field_;
		
		String query = "";
		if (query_ != null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if (page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		
		
		NoticeService service = new NoticeService();
		int count = service.getNoticeCount(field, query);
		List<NoticeView> list = service.getNoticeViewList(field, query, page);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);	
	}
}
