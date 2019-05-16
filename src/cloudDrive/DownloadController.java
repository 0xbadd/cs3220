package cloudDrive;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloudDrive/Download")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		@SuppressWarnings("unchecked")
		Map<Integer, FileEntryBean> files = (Map<Integer, FileEntryBean>) request.getSession().getAttribute("files");
		String filepath = files.get(Integer.parseInt(id)).getFilepath();
		String filename = files.get(Integer.parseInt(id)).getFilename();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        FileInputStream in = new FileInputStream(filepath);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        
        while((read = in.read(buffer, 0, 4096)) > 0) {
            out.write(buffer, 0, read);
        }

        in.close();
        out.flush();
        out.close();
        
		response.sendRedirect("FileList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
