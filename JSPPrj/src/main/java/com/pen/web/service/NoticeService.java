package com.pen.web.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pen.web.entity.Notice;
import com.pen.web.entity.NoticeView;

public class NoticeService {
	
	public int removeNoticeAll(int[] ids) {
		
		int result = 0;
		String params = "";
		
		for (int i = 0; i < ids.length; i++) {
			params += ids[i];
			if (i < ids.length-1)
				params += ",";
		}
		
		String sql = "DELETE NOTICE WHERE ID IN (" +params+ ")";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int pubNoticeAll(int[] ids) {
		
		int result = 0;
		String params = "";
		
		for (int i = 0; i < ids.length; i++) {
			params += ids[i];
			if (i < ids.length-1)
				params += ",";
		}
		
		String sql = "UPDATE NOTICE SET PUB=1 WHERE ID IN (" +params+ ")";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int insertNotice(Notice notice) {
		
		int result = 0;
		
		String sql = "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID, FILES, PUB) VALUES(?, ?, ?, ?, ?)";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getWriterId());
			st.setString(4, notice.getFiles());
			st.setBoolean(5, notice.getPub());
			result = st.executeUpdate();
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteNotice(int id) {
		
		int result = 0;
		String sql = "DELETE NOTICE WHERE ID = ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			result = st.executeUpdate();
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int updateNotice(Notice notice) {
		
		int result = 0;
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=?, REDATE=SYSDATE, FILES=? WHERE ID=?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getFiles());
			st.setInt(4, notice.getId());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	List<Notice> getNoticeNewestList(){
		
		List<Notice> list = new ArrayList<>();
		
		String sql = "SELECT "
				+ "FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE_VIEW WHERE TITLE LIKE '%%' ORDER BY REGDATE DESC) N) "
				+ "WHERE NUM BETWEEN 1 AND 5";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) { 
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				Notice notice = new Notice(id, title, regDate, writerId, hit, files, content, pub);
				list.add(notice);		
			}
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return list;
	}
	
	
	
	
	public List<NoticeView> getNoticeViewList(){
		
		return  getNoticeViewList("TITLE", "", 1);
	}
	
	public List<NoticeView> getNoticeViewList(int page){
		
		return getNoticeViewList("TITLE", "", page);
	}
	
	public List<NoticeView> getNoticeViewList(String field, String query, int page) {
		
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE_VIEW WHERE " +field+ " LIKE ? ORDER BY REGDATE DESC) N) "
				+ "WHERE NUM BETWEEN ? AND ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
	
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) { 
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(id, title, regDate, writerId, hit, files, pub, cmtCount);
				list.add(notice);		
			}
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return list;
	}
	
	public List<NoticeView> getPubNoticeViewList(String field, String query, int page) {
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE_VIEW WHERE " +field+ " LIKE ? AND PUB = 1 ORDER BY REGDATE DESC) N) "
				+ "WHERE NUM BETWEEN ? AND ?";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
	
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) { 
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				//String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("CMT_COUNT");
				boolean pub = rs.getBoolean("PUB");
				
				NoticeView notice = new NoticeView(id, title, regDate, writerId, hit, files, pub, cmtCount);
				list.add(notice);		
			}
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return list;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE WHERE " +field+ " LIKE ?"; 
				/*"SELECT COUNT(ID) COUNT "
				+ "FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE " +field+ " LIKE ? ORDER BY REGDATE DESC) N)";*/
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				count = rs.getInt("count");

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	public int getPubNoticeCount() {
		
		return getPubNoticeCount("title", "");
	}
	
	public int getPubNoticeCount(String field, String query) {
		
		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE WHERE " +field+ " LIKE ? AND PUB=1" ; 
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				count = rs.getInt("count");

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	public Notice getNotice(int id) {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			
			if (rs.next()) {
				int nid = rs.getInt("ID"); 
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regDate, writerId, hit, files, content, pub);
				 
				rs.close();
				st.close();
				con.close();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		
		String sql = "SELECT * FROM NOTICE "
				+ "WHERE ID = ("
				+ "    SELECT ID FROM NOTICE"
				+ "    WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = ?) AND ROWNUM = 1"
				+ ")";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			
			if (rs.next()) {
				int nid = rs.getInt("ID"); 
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regDate, writerId, hit, files, content, pub);
				
				rs.close();
				st.close();
				con.close();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	public Notice getPrevNotice(int id) {
		
		String sql = "SELECT * FROM NOTICE "
				+ "WHERE ID = (SELECT ID FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID = ?) AND ROWNUM=1)";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			
			if (rs.next()) {
				int nid = rs.getInt("ID"); 
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regDate, writerId, hit, files, content, pub);

				
				rs.close();
				st.close();
				con.close();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return notice;
	}


	public Notice getPubNextNotice(int id) {
		String sql = "SELECT * FROM NOTICE "
				+ "WHERE ID = (SELECT ID FROM (SELECT ID FROM NOTICE WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = ?) AND PUB=1 ORDER BY REGDATE) WHERE ROWNUM=1)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			
			if (rs.next()) {
				int nid = rs.getInt("ID"); 
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regDate, writerId, hit, files, content, pub);
				
				rs.close();
				st.close();
				con.close();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	public Notice getPubPrevNotice(int id) {
		
		String sql = "SELECT * FROM NOTICE "
				+ "WHERE ID = (SELECT ID FROM (SELECT * FROM NOTICE WHERE PUB=1 ORDER BY REGDATE DESC) WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID = ?) AND ROWNUM=1)";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		Notice notice = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "021212");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			
			if (rs.next()) {
				int nid = rs.getInt("ID"); 
				String title = rs.getString("TITLE"); 
				Date regDate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("PUB");
				
				notice = new Notice(nid, title, regDate, writerId, hit, files, content, pub);

				
				rs.close();
				st.close();
				con.close();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return notice;
	}

	
}
