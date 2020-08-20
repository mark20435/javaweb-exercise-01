package web.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class DeleteController
 */
@WebServlet("/member/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	private MemberService ms = new MemberService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteController() {
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
			List<Member> mList = ms.selectAll();

			// ↓設定回應的資料格式為JSON
			response.setContentType("application/json; charset=UTF-8");
			writeJson(response, mList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeJson(HttpServletResponse response, List<Member> mList) {
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(mList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
