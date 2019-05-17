package cloudDrive;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/ChangeFolder")
public class ChangeFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		if (id == 0) {
			String folderPath = (String) request.getSession().getAttribute("folderpath");
			String parentPath = folderPath.substring(0, folderPath.lastIndexOf("/"));

			request.getSession().setAttribute("folderpath", parentPath);
		} else {
			@SuppressWarnings("unchecked")
			Map<Integer, FolderEntryBean> folders = (Map<Integer, FolderEntryBean>) request.getSession().getAttribute("folders");
			FolderEntryBean folder = folders.get(id);

			request.getSession().setAttribute("folderpath", folder.getParentpath() + folder.getFoldername());
		}

		response.sendRedirect("FileList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
