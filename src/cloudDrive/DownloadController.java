package cloudDrive;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadController")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		String path = request.getParameter("path");
		
		String fileDir = getServletContext().getRealPath(path);	
		
		Connection c = null;
		
//		response.setContentType( "image/jpg" );
//        response.setHeader( "Content-Disposition",
//            "attachment; filename=cark.jpg" );

        FileInputStream in = new FileInputStream( path );
        OutputStream out = response.getOutputStream();

        byte buffer[] = new byte[2048];
        
        int bytesRead;
        
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );

        in.close();
        
		response.sendRedirect("FileListController");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
