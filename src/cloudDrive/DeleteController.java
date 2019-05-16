package cloudDrive;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/Delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu77";
			String dbUsername = "cs3220stu77";
			String dbPassword = "M**XK2EH";

			c = DriverManager.getConnection(url, dbUsername, dbPassword);
			
			String sql = "DELETE FROM files WHERE id=?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, id);

			pstmt.executeUpdate();

			@SuppressWarnings("unchecked")
			Map<Integer, FileEntryBean> files = (Map<Integer, FileEntryBean>) request.getSession().getAttribute("files");
			String filepath = files.get(Integer.parseInt(id)).getFilepath();
			File file = new File(filepath);
			file.delete();
		} catch(SQLException e) {
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
		
		response.sendRedirect("FileList");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

