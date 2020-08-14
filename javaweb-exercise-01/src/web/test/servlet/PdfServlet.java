package web.test.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PdfServlet
 */
@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file = "/WEB-INF/pdf/test.pdf";
        try (InputStream is = getServletContext().getResourceAsStream(file)) {
        	response.setContentType("application/pdf");
//            response.setContentType("application/octet-stream");	// ←一定要在處理訊息本體前設定!!
//            String filename = URLEncoder.encode("中文.pdf", "UTF-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            try (ServletOutputStream sos = response.getOutputStream()) {
                byte[] buf = new byte[is.available()];
                while (is.read(buf) != -1) {
                    sos.write(buf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	

}
