package cloudDrive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/FileList")
public class FileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FileEntryBean> files = new ArrayList<>();
		String userid = (String) request.getAttribute("userid");
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu83";
			String dbUsername = "cs3220stu83";
			String dbPassword = "ZsZ85.kr";
			
			c = DriverManager.getConnection(url, dbUsername, dbPassword);
			String sql = "SELECT * FROM files WHERE User_id=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				FileEntryBean file = new FileEntryBean(
						rs.getInt("id"),
						rs.getString("File_name"),
						rs.getString("File_Path"),
						rs.getInt("User_id")
				);
				files.add(file);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		request.getSession().setAttribute("files", files);
		request.getRequestDispatcher("/WEB-INF/cloudDrive/FileListView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
