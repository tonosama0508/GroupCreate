package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/Users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String url = "jdbc:mysql://localhost:3306/groupCreate?characterEncoding=UTF-8&serverTimezone=JST&autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "root";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try (PrintWriter pw = response.getWriter()) {
			pw.println("<table><th>ID</th><th>名前</th><th>職Aの点数</th><th>職Bの点数</th><th>職Cの点数</th><th>就職適正</th>");
			Connection conn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);

				Statement stmt = (Statement) conn.createStatement();
				String sql = "SELECT * FROM users";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String pass = rs.getString("password");
					int resultA = rs.getInt("resultA");
					int resultB = rs.getInt("resultB");
					int resultC = rs.getInt("resultC");
					String employment = rs.getString("employment");
					
					pw.println("<tr><td>" + id + "</td>");
					pw.println("<td>" + name + "</td>");
					//pw.println("<td>" + pass + "</td>");
					pw.println("<td>" + resultA + "</td>");
					pw.println("<td>" + resultB + "</td>");
					pw.println("<td>" + resultC + "</td>");
					pw.println("<td>" + employment + "</td></tr>");
				}
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("SQLException:" + e.getMessage());
			}
			pw.println("</table>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String name = request.getParameter("name");
		String pass = request.getParameter("password");

		try (PrintWriter pw = response.getWriter()) {
			if (name == "") {
				pw.println("名前を入力してください。");
			} else if (pass == "") {
				pw.println("パスワードを入力してください。");
			} else {
				Connection conn = null;
				PreparedStatement ps = null;
				String sql = "INSERT INTO users (name, password, resultA, resultB, resultC, employment) values(?, ?, ?, ?, ?, ?)";

				try {
					conn = DriverManager.getConnection(url, user, password);
					ps = conn.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, pass);
					ps.setInt(3, 0);
					ps.setInt(4, 0);
					ps.setInt(5, 0);
					ps.setString(6, "");
					int i = ps.executeUpdate();
					pw.println(i + "ユーザー登録されました。");
				} catch (SQLException e) {
					System.out.println("SQLException:" + e.getMessage());
				}
			}
		}
	}

}
