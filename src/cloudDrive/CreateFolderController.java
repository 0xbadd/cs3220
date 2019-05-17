package cloudDrive;

import java.io.IOException;
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
		String newFolderName = request.getParameter("folderName");
		String oldFolderPath = (String) request.getSession().getAttribute("folderpath");
		
		if (newFolderName.matches(".*[^\\w-.].*") || newFolderName == ""){
			request.getSession().setAttribute("error", "folder");
		} else {
			newFolderName = "/" + newFolderName;
			request.getSession().setAttribute("currentFolder", newFolderName);
			request.getSession().setAttribute("folderpath", oldFolderPath + newFolderName);
		}
		
		doGet(request, response);
	}

}
