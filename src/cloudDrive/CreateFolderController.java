package cloudDrive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/CreateFolder")
public class CreateFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("FileList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderName = request.getParameter("folderName");
		String parentpath = (String) request.getSession().getAttribute("folderpath");
		int userid = (int) request.getSession().getAttribute("userid");
		
		if (folderName.matches(".*[^\\w-.].*") || folderName == ""){
			request.getSession().setAttribute("error", "folder");
		} else {
			folderName = "/" + folderName;
			String folderpath = parentpath + folderName;

			Connection c = null;
			try {
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu77";
				String dbUsername = "cs3220stu77";
				String dbPassword = "M**XK2EH";
				
				c = DriverManager.getConnection(url, dbUsername, dbPassword);

				String sql = "SELECT * FROM folders WHERE userid=? AND parentpath=? AND foldername=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, userid);
				ps.setString(2, parentpath);
				ps.setString(3, folderName);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					request.getSession().setAttribute("error", "duplicatefolder");
				} else {
					sql = "INSERT INTO folders (userid, foldername, parentpath) values (?, ?, ?)";
					ps = c.prepareStatement(sql);
					ps.setInt(1, userid);
					ps.setString(2, folderName);
					ps.setString(3, parentpath);
					ps.executeUpdate();

					request.getSession().setAttribute("folderpath", folderpath);
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
		}
		
		doGet(request, response);
	}

}
