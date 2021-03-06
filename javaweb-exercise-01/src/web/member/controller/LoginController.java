package web.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.bean.Member;
import web.member.service.MemberService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/member/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	private MemberService ms = new MemberService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			Member member = json2Member(request);
			member = ms.login(member);

			
			//轉發一直出錯，放棄使用，改用js跳轉網頁
//			if (member != null) {
//				// 使用Session範圍物件
//				HttpSession session = request.getSession();
//				// 加入一個共享資料:
//				session.setAttribute("account", member.getAccount());
//				session.setAttribute("nickname", member.getNickname());
//				// 使用重新導向的方式轉發
//	            response.sendRedirect("html/update.html");
//			}
			

			// ↓設定回應的資料格式為JSON
			response.setContentType("application/json; charset=UTF-8");
			writeJson(response, member);
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

	private Member json2Member(HttpServletRequest request) {
		StringBuilder json = new StringBuilder();
		try (BufferedReader br = request.getReader()) {
			String line;
			while ((line = br.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GSON.fromJson(json.toString(), Member.class);
	}
}
