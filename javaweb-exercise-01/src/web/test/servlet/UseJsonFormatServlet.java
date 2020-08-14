package web.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.bean.Member;

/**
 * Servlet implementation class UseJsonFormatServlet
 */
@WebServlet("/UseJsonFormatServlet")
public class UseJsonFormatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UseJsonFormatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
            Member member = json2Person(request);
            				// ↓設定回應的資料格式為JSON
            //response.setContentType("application/json; charset=UTF-8");
            member.setPass(true);
            //writeJson(response, member);
            
            //使用範圍物件"Request"設定共享資料
            //識別字串：name
            //屬性物件：[會員的暱稱]
            //直接跳轉至result.jsp
            // 使用Session範圍物件
            HttpSession session = request.getSession();
            // 加入一個共享資料: 
            session.setAttribute("name", member.getNickname());
//          // 使用重新導向的方式轉發，導向result.jsp
//            response.sendRedirect("result.jsp");
            
            //直接跳轉
            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private void writeJson(HttpServletResponse response, Member member) {
		try (PrintWriter pw = response.getWriter()) {
            pw.print(GSON.toJson(member));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private Member json2Person(HttpServletRequest request) {
		StringBuilder json = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String line;
            while ((line = br.readLine()) != null) { json.append(line); }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json.toString(), Member.class);
	}

}
