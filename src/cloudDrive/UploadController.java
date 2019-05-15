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

@WebServlet("/CloudDrive/Upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("FileList");
	}
	
	public void addBackslash(String path) {
		String[] arrOfStrings = path.split("\\");
		String newString = "";
		int count = 0;
		for(String str: arrOfStrings) {
			newString.concat(str);
			if(count < arrOfStrings.length - 1) {
				newString.concat("\\\\");
			}
		}
		
		path = newString;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletContext servletContext = this.getServletConfig().getServletContext();

		File repository = (File) servletContext.getAttribute( "javax.servlet.context.tempdir" );

		factory.setRepository( repository );

		ServletFileUpload upload = new ServletFileUpload( factory );

		String fileDir = getServletContext().getRealPath( "/WEB-INF/uploads" );
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu83";
			String username = "cs3220stu83";
			String password = "ZsZ85.kr";

			c = DriverManager.getConnection( url, username, password );
			try {
				List<FileItem> items = upload.parseRequest( request );

				for( FileItem item : items ) {
					if( !item.isFormField() ) {
						String fileName = (new File( item.getName() )).getName();
						File file = new File( fileDir, fileName );
						String path = "WEB-INF\\\\uploads\\\\" + fileName;
						item.write( file );
						
						String sql = "INSERT INTO files (File_Name, File_Path, User_id) VALUES (\"" + fileName + "\", \""  + path + "\", "+ 1 + ")";
						System.out.println("Query: " + sql);

						PreparedStatement pstmt = c.prepareStatement(sql);
						int numberOfRowsAffected = pstmt.executeUpdate();

					}

				}

			}
			catch( Exception e ) {
				throw new IOException( e );
			}            

		}
		catch( SQLException e ){
			throw new ServletException( e );
		}
		
		doGet(request, response);
	}

}
