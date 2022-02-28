package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompanyUsersServlet
 */
@WebServlet("/CompanyUsers")
public class CompanyUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String pass = request.getParameter("password");
		String passCorrect  = "company123";

		PrintWriter pw = response.getWriter();
		
		if (pass == "") {
			pw.println("パスワードを入力してください。");
		} else {
			if (pass.equals(passCorrect)) {
				String disp = "loginCompanySuccess.html";
				RequestDispatcher dispatch = request.getRequestDispatcher(disp);

			    dispatch.forward(request, response);
			} else {
				pw.println("ブラウザバックしてもう一度入力してください");
			}
		}		
	}

}
