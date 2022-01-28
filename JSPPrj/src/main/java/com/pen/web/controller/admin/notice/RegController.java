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
		
		// 인코딩 방식이 multipart
		Collection<Part> parts = request.getParts(); // part 여러개 가져오기. 
		StringBuilder builder = new StringBuilder(); // DB에서 파일을 쉼표로 구분하기로 함.
		for (Part p : parts) { 
			
			if (!p.getName().equals("file")) continue; // file이 아닌 part면 넘기기.
			if (p.getSize() == 0) continue; //파일 첨부가 안된 경우 넘기기.  
			
			
			Part filePart = p;
			
			String fileName = filePart.getSubmittedFileName(); // 파일 이름 얻기
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream(); // 입력 스트림 개설
			
			String realPath = request.getServletContext().getRealPath("/upload"); // 절대 경로 얻기			
			File path = new File(realPath); //Creating a File object for path.
			if (!path.exists()) path.mkdirs(); // 경로가 존재하지 않는다면 만들어주기.
			
			
			String filePath = realPath + File.separator + fileName; // 파일 경로 설정. File.sepatrator : 운영체제별로 다른 구분자 설정.

	
			FileOutputStream fos = new FileOutputStream(filePath); // 출력 스트림 개설
			
			byte[] buf = new byte[1024]; // 1024바이트짜리 버퍼 준비
			int size = 0;
			while ((size=fis.read(buf)) != -1) // 한번에 최대 buf의 크기만큼 읽음. 여기선 최대 1024바이트.
				fos.write(buf,0,size); // 출력스트림을 통해 설정한 파일경로에 read한 데이터를 offset 0부터 size만큼 write.
			
			fos.close(); // 출력 스트림 닫기
			fis.close(); // 입력 스트림 닫기
		}
		builder.delete(builder.length()-1, builder.length()); // 맨 마지막 구분자 빼주기.
		
	
		boolean pub = (isOpen != null)? true : false;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("Pen."); // 인증과 권한. 서버는 현재 접속한 사용자!를 알아야한다.
		notice.setFiles(builder.toString());
		
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		// 따로 경로를 지정하지 않으면 현재 url인 /admin/board/notice/ 에서 지정한 path가 붙음. 
		response.sendRedirect("list"); 

	}
}
