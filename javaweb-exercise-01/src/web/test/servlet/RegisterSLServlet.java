package web.test.servlet;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.annotation.WebInitParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterSLServlet
 */
//@WebServlet(
//			urlPatterns = {	 	// ←設定映射的網址
//	            "/registerSL",
//	            "/REGISTER_SL"
//	        },
//	        name = "registerSLServlet",	// ←設定Servlet的名稱
//	        loadOnStartup = 1,		// ←設定啟動順序
//	        initParams = {		// ←設定初始化參數(name、age)
//	            @WebInitParam(name = "name", value = "Mark"),
//	            @WebInitParam(name = "height", value = "100")
//	        }    
//)
public class RegisterSLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Writer writer = response.getWriter()) {
			Enumeration<String> names = getInitParameterNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				writer.write(name +": " + getInitParameter(name) + "\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
