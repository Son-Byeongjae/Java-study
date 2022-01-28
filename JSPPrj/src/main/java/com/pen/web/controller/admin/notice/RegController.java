package com.pen.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.pen.web.entity.Notice;
import com.pen.web.service.NoticeService;

@MultipartConfig(
	fileSizeThreshold=1024*1024,
	maxFileSize=1024*1024*50,
	maxRequestSize=1024*1024*50*5
)

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		// ���ڵ� ����� multipart
		Collection<Part> parts = request.getParts(); // part ������ ��������. 
		StringBuilder builder = new StringBuilder(); // DB���� ������ ��ǥ�� �����ϱ�� ��.
		for (Part p : parts) { 
			
			if (!p.getName().equals("file")) continue; // file�� �ƴ� part�� �ѱ��.
			if (p.getSize() == 0) continue; //���� ÷�ΰ� �ȵ� ��� �ѱ��.  
			
			
			Part filePart = p;
			
			String fileName = filePart.getSubmittedFileName(); // ���� �̸� ���
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream(); // �Է� ��Ʈ�� ����
			
			String realPath = request.getServletContext().getRealPath("/upload"); // ���� ��� ���			
			File path = new File(realPath); //Creating a File object for path.
			if (!path.exists()) path.mkdirs(); // ��ΰ� �������� �ʴ´ٸ� ������ֱ�.
			
			
			String filePath = realPath + File.separator + fileName; // ���� ��� ����. File.sepatrator : �ü������ �ٸ� ������ ����.

	
			FileOutputStream fos = new FileOutputStream(filePath); // ��� ��Ʈ�� ����
			
			byte[] buf = new byte[1024]; // 1024����Ʈ¥�� ���� �غ�
			int size = 0;
			while ((size=fis.read(buf)) != -1) // �ѹ��� �ִ� buf�� ũ�⸸ŭ ����. ���⼱ �ִ� 1024����Ʈ.
				fos.write(buf,0,size); // ��½�Ʈ���� ���� ������ ���ϰ�ο� read�� �����͸� offset 0���� size��ŭ write.
			
			fos.close(); // ��� ��Ʈ�� �ݱ�
			fis.close(); // �Է� ��Ʈ�� �ݱ�
		}
		builder.delete(builder.length()-1, builder.length()); // �� ������ ������ ���ֱ�.
		
	
		boolean pub = (isOpen != null)? true : false;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("Pen."); // ������ ����. ������ ���� ������ �����!�� �˾ƾ��Ѵ�.
		notice.setFiles(builder.toString());
		
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		// ���� ��θ� �������� ������ ���� url�� /admin/board/notice/ ���� ������ path�� ����. 
		response.sendRedirect("list"); 

	}
}
