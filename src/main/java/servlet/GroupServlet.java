package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GroupServlet
 */
@WebServlet("/Group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
	private String url = "jdbc:mysql://localhost:3306/groupCreate?characterEncoding=UTF-8&serverTimezone=JST&autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "root";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		
		String A1 = request.getParameter("A1");
		String A2 = request.getParameter("A2");
		String A3 = request.getParameter("A3");
		String A4 = request.getParameter("A4");
		String A5 = request.getParameter("A5");
		String A6 = request.getParameter("A6");
		int scoreA = Integer.parseInt(A1) + Integer.parseInt(A2) + Integer.parseInt(A3) + Integer.parseInt(A4) + Integer.parseInt(A5) + Integer.parseInt(A6);
		
		String B1 = request.getParameter("B1");
		String B2 = request.getParameter("B2");
		String B3 = request.getParameter("B3");
		String B4 = request.getParameter("B4");
		String B5 = request.getParameter("B5");
		String B6 = request.getParameter("B6");
		int scoreB = Integer.parseInt(B1) + Integer.parseInt(B2) + Integer.parseInt(B3) + Integer.parseInt(B4) + Integer.parseInt(B5) + Integer.parseInt(B6);
		
		String C1 = request.getParameter("C1");
		String C2 = request.getParameter("C2");
		String C3 = request.getParameter("C3");
		String C4 = request.getParameter("C4");
		String C5 = request.getParameter("C5");
		String C6 = request.getParameter("C6");
		int scoreC = Integer.parseInt(C1) + Integer.parseInt(C2) + Integer.parseInt(C3) + Integer.parseInt(C4) + Integer.parseInt(C5) + Integer.parseInt(C6);
		
		PrintWriter out = response.getWriter();//[7]
             
        
        
        
        //sqlに保存
        HttpSession session = request.getSession();
        Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE groupCreate.users SET resultA=?, resultB=?, resultC=?,employment=? WHERE name=? AND password=?";
		String check;
		int scoreAns;
		if (scoreA<=scoreB){
		       check="B";
		       scoreAns=scoreB;}
		else{
		check="A";
		scoreAns=scoreA;
		}
		if(scoreAns<=scoreC){
		scoreAns=scoreC;
		check="C";
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			ps.setString(5, (String)session.getAttribute("name"));
			ps.setString(6, (String)session.getAttribute("password"));
			ps.setInt(1, scoreA);
			ps.setInt(2, scoreB);
			ps.setInt(3, scoreC);
			
			ps.setString(4, check);
			
			
			
			session.setAttribute("resultA", scoreA);
			session.setAttribute("resultB", scoreB);
			session.setAttribute("resultC", scoreC);
			session.setAttribute("employment", check);
			int i = ps.executeUpdate();
			out.println(i + "結果が更新されました。");
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		}
		
		
		if(check == "B") {
			String dispB = "resultB.html";
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher(dispB);

		    dispatch.forward(request, response);
		} else if(check =="A") {
			String dispA = "resultA.html";
			RequestDispatcher dispatch = request.getRequestDispatcher(dispA);

		    dispatch.forward(request, response);
		} else if(check=="C"){
			String dispC = "resultC.html";
			RequestDispatcher dispatch = request.getRequestDispatcher(dispC);

		    dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();//[7]
        out.println("<html><head></head><body>");//[8]
        out.println("<p>あなたのAの得点はです。</p>");//[9]
        out.println("</body></html>");
	}

}
