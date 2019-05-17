package cloudDrive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/CloudDrive/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cloudDrive/LoginView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int userid = -1;
		String userRoot = null;
		boolean isValid = false;
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu77";
			String dbUsername = "cs3220stu77";
			String dbPassword = "M**XK2EH";
			
			c = DriverManager.getConnection(url, dbUsername, dbPassword);
			String sql = "SELECT * FROM users WHERE username=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
				isValid = true;
				userid = rs.getInt("id");
				userRoot = "/" + rs.getString("username");
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

		if (isValid) {
			request.getSession().setAttribute("userid", userid);
			request.getSession().setAttribute("userRoot", userRoot);
			request.getSession().setAttribute("folderpath", userRoot);
			request.getSession().setAttribute("currentFolder", userRoot);
			response.sendRedirect("FileList");
		} else {
			request.getSession().setAttribute("error", "login");
			doGet(request, response);
		}
	}
}
