package web.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReceiveParameterServlet
 */
@WebServlet("/ReceiveParameterServlet")
public class ReceiveParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveParameterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()){
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			Map<String, String[]> paramMap = request.getParameterMap();
            paramMap.forEach((key, value) -> {
            	System.out.println(key + ": " + Arrays.toString(value));
                //pw.println(key + ": " + Arrays.toString(value) + "<br>");
            });
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
