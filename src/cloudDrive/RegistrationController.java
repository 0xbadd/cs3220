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

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/CloudDrive/Register")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/cloudDrive/RegistrationView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		int userid = -1;
		boolean isValid = true;
		Connection c = null;

		request.getSession().setAttribute("error", null);
		
		if (!password.equals(passwordRepeat)) {
			isValid = false;
			request.getSession().setAttribute("error", "password");
		}
		
		if (username == "" || email == "" || password == "") {
			isValid = false;
		}
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu77";
			String dbUsername = "cs3220stu77";
			String dbPassword = "M**XK2EH";
			
			c = DriverManager.getConnection(url, dbUsername, dbPassword);

			String sql = "SELECT * FROM users WHERE username=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() && isValid) {
				isValid = false;
				request.getSession().setAttribute("error", "username");
			}

			sql = "SELECT * FROM users WHERE email=?";
			ps = c.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if (rs.next() && isValid) {
				isValid = false;
				request.getSession().setAttribute("error", "email");
			}
			
			if (isValid) {
				String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
				sql = "INSERT INTO users (username, email, password) values (?, ?, ?)";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, email);
				ps.setString(3, passwordHash);
				ps.executeUpdate();
				
				sql = "SELECT * FROM users WHERE username=?";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				
				if (rs.next()) {
					userid = rs.getInt("id");
				}
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
			response.sendRedirect("FileList");
		} else {
			doGet(request, response);
		}
	}

}
