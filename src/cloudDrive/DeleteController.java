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

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu83";
			String username = "cs3220stu83";
			String password = "ZsZ85.kr";

			c = DriverManager.getConnection( url, username, password );
			
			String sql = "DELETE FROM files WHERE id=" + id;
			System.out.println("Query: " + sql);

			PreparedStatement pstmt = c.prepareStatement(sql);
			int numberOfRowsAffected = pstmt.executeUpdate();
		}
		catch( Exception e ) {
			throw new IOException( e );
		}
		
		response.sendRedirect("FileListController");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
