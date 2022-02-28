package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/search")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String url = "jdbc:mysql://localhost:3306/groupCreate?characterEncoding=UTF-8&serverTimezone=JST&autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "root";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		int section = Integer.parseInt(request.getParameter("section"));
		int sort = Integer.parseInt(request.getParameter("sort"));
		List<user> userList = new ArrayList<user>();

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		int flag = 0;

		try {
			conn = DriverManager.getConnection(url, user, password);

			// 接続したデータベースに、javaサーブレットから命令(DML)を送るための準備
			// ※DML : データ操作言語。データベースのデータを指定して拾ってきたり、データを追加・更新したりできる。
			Statement stmt = (Statement) conn.createStatement();
			// Statementオブジェクト ： SQL文を実行し、作成された結果を返す。
			sql = "SELECT * FROM users";
			// 送りたいSQL文。
			// 今回は従業員の一覧情報を取得したいので、
			// SELECTという命令を使って、employeeというテーブルからすべての情報をとってくるよう指定している
			ResultSet rs = stmt.executeQuery(sql);
			// ↑ Statementクラスの持つ、executeQueryメソッドにsql文を入れる
			// executeQueryメソッド : SELECT文を扱う（INSERTなど他のSQL文は×）。ResultSet型で戻り値を返す。
			// このとき、データベースへの接続の失敗や、SELECT文以外のSQL文を使う、などの異常があると、
			// SQLexceptionというエラーを出し、catch文にあとの処理を任せる（throwする）
			// 要はここでデータベース系の異常が出ると処理がcatchに飛ぶよ！

			// Statementをつかって、データベースの中身を得ることができたので、
			// 次はその結果を表示していく。

			// 繰り返し文をつかって、表の行を表示していく

			while (rs.next()) {

				// rs.nextについて
				// ResultSetオブジェクトはデータの表に加えて、現在、表の中のどの行を見ているか、という情報（カーソル）を持っている。
				// カーソルは初期は一番上にあり、nextメソッドは、カーソルを次の行に進める。
				// 繰り返し文を使うことによって、表のデータを一行ごと、上から下まで順番に見ていく感じ。

				// 書き出し用の変数を定義
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int resultA = rs.getInt("resultA");
				int resultB = rs.getInt("resultB");
				int resultC = rs.getInt("resultC");
				String employment = rs.getString("employment");
				

				user userInstance = new user(id, name, resultA, resultB, resultC,employment);
				userList.add(userInstance);
				// それぞれ、ResultSetオブジェクトのget～～メソッドを使ってデータを取り出している。
				// 現在カーソルが一行目を示している場合、
				// 一行目のid、name、sectionのデータをそれぞれ取り出す。

				// 行を書き出し
				// HTMLのテーブルのタグを使って、↑で拾ってきたデータを一行分記述する。

			} // 従業員一覧表の書き出しおわり

			// try文からここまで実行の際にSQLに関するエラーが発生したら ↓ が実行される
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			// コンソールにSQLのエラー内容を出力
		}

		// 並べ替え
		Collections.sort(userList, new Comparator<user>() {
			@Override
			public int compare(user num1, user num2) {
				return Integer.compare(num1.getUser()[section], num2.getUser()[section]);
			}
		});

		pw.println("<table><th>ID</th><th>名前</th><th>職Aの点数</th><th>職Bの点数</th><th>職Cの点数</th><th>就職適正</th>");

		if (sort == 1 || sort == 0) {
			for (int i = 0; i < userList.size(); i++) {
				pw.println("<tr><td>" + userList.get(i).id + "</td>");
				pw.println("<td>" + userList.get(i).name + "</td>");
				pw.println("<td>" + userList.get(i).resultA + "</td>");
				pw.println("<td>" + userList.get(i).resultB + "</td>");
				pw.println("<td>" + userList.get(i).resultC + "</td>");
				pw.println("<td>" + userList.get(i).employment + "</td></tr>");
			}
		} else {
			for (int i = userList.size()-1; i >= 0; i--) {
				pw.println("<tr><td>" + userList.get(i).id + "</td>");
				pw.println("<td>" + userList.get(i).name + "</td>");
				pw.println("<td>" + userList.get(i).resultA + "</td>");
				pw.println("<td>" + userList.get(i).resultB + "</td>");
				pw.println("<td>" + userList.get(i).resultC + "</td>");
				pw.println("<td>" + userList.get(i).employment + "</td></tr>");
			}
		}

		pw.println("</table>");

	}

}
