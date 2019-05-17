package cloudDrive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/FileList")
public class FileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = (int) request.getSession().getAttribute("userid");
		String folderPath = (String) request.getSession().getAttribute("folderpath");
		Map<Integer, FileEntryBean> files = new LinkedHashMap<>();
		Map<Integer, FolderEntryBean> folders = new LinkedHashMap<>();
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu77";
			String dbUsername = "cs3220stu77";
			String dbPassword = "M**XK2EH";
			
			c = DriverManager.getConnection(url, dbUsername, dbPassword);
			String sql = "SELECT * FROM files WHERE userid=? AND folderpath=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, folderPath);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String query = request.getParameter("query");
				query = query == null ? "" : query.toLowerCase();
				
				if (rs.getString("filename").toLowerCase().indexOf(query) >= 0) {
					FileEntryBean file = new FileEntryBean(
							rs.getString("filename"),
							rs.getString("filepath"),
							rs.getInt("userid"),
							rs.getString("folderpath")
					);
					files.put(rs.getInt("id"), file);
				}
			}

			sql = "SELECT * FROM folders WHERE userid=? AND parentpath=?";
			ps = c.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, folderPath);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				FolderEntryBean folder = new FolderEntryBean(
						rs.getInt("userid"),
						rs.getString("foldername"),
						rs.getString("parentpath")
				);
				folders.put(rs.getInt("id"), folder);
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
		request.getSession().setAttribute("folders", folders);
		request.getRequestDispatcher("/WEB-INF/cloudDrive/FileListView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
