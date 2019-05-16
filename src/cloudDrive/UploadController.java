package cloudDrive;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@WebServlet("/CloudDrive/Upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("FileList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileDir = getServletContext().getRealPath("/WEB-INF/uploads");
		
		int userid = (int) request.getSession().getAttribute("userid");

		factory.setRepository(repository);
		
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu77";
			String dbUsername = "cs3220stu77";
			String dbPassword = "M**XK2EH";
			
			c = DriverManager.getConnection(url, dbUsername, dbPassword);
			try {
				List<FileItem> items = upload.parseRequest(request);

				for(FileItem item : items) {
					if(!item.isFormField()) {
						String fileName = FilenameUtils.getName(item.getName());
						File file = new File(fileDir, fileName);
						item.write(file);
						
						String sql = "INSERT INTO files (filename, filepath, userid) VALUES (?, ?, ?)";
						PreparedStatement pstmt = c.prepareStatement(sql);
						pstmt.setString(1, fileName);
						pstmt.setString(2, file.getAbsolutePath());
						pstmt.setInt(3, userid);
						
						pstmt.executeUpdate();
					}
				}
			} catch(Exception e) {
				throw new IOException(e);
			}            
		} catch(SQLException e){
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
		
		doGet(request, response);
	}
}
