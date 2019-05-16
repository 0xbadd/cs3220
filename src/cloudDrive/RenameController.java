package cloudDrive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/Rename")
public class RenameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cloudDrive/RenameView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
//		int id = Integer.parseInt(request.getParameter("id"));
		String newName = request.getParameter("newName");

		if(newName != null) {
			Connection c = null;

			try {
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu83";
				String username = "cs3220stu83";
				String password = "ZsZ85.kr";

				c = DriverManager.getConnection(url, username, password);

				String sql = "UPDATE files SET File_Name = \'" + newName + "\' WHERE id=";

				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch(Exception e) {
				throw new IOException(e);
			}

			response.sendRedirect("FileList");
		}

		doGet(request, response);
	}

}
