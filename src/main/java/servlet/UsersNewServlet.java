package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UsersNewServlet
 */
@WebServlet("/UsersNew")
public class UsersNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String url = "jdbc:mysql://localhost:3306/groupCreate?characterEncoding=UTF-8&serverTimezone=JST&autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "root";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String check=(String)session.getAttribute("employment");
		//PrintWriter pw = response.getWriter();
	   // int resultA=session.getAttribute("resultA");
	    
		
		
		if(check.equals("B")) {
			String dispB = "resultB.html";
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher(dispB);

		    dispatch.forward(request, response);
		} else if(check.equals("A")) {
			String dispA = "resultA.html";
			RequestDispatcher dispatch = request.getRequestDispatcher(dispA);

		    dispatch.forward(request, response);
		} else if(check.equals("C")){
			String dispC = "resultC.html";
			RequestDispatcher dispatch = request.getRequestDispatcher(dispC);

		    dispatch.forward(request, response);
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
		
		int resultA = 1;
		int resultB = 1;
		int resultC = 1;
		String employment = "";
		

		try (PrintWriter pw = response.getWriter()) {
			if (name == "") {
				pw.println("名前を入力してください。");
			} else if (pass == "") {
				pw.println("パスワードを入力してください。");
			} else {
				Connection conn = null;
				PreparedStatement ps = null;
				String sql = null;
				int flag = 0;
				

				try {
				    conn = DriverManager.getConnection(url, user, password);

					//接続したデータベースに、javaサーブレットから命令(DML)を送るための準備
					//※DML : データ操作言語。データベースのデータを指定して拾ってきたり、データを追加・更新したりできる。
					Statement stmt = (Statement) conn.createStatement();
						//Statementオブジェクト ： SQL文を実行し、作成された結果を返す。
					sql = "SELECT * FROM users";
						//送りたいSQL文。
						//今回は従業員の一覧情報を取得したいので、
						//SELECTという命令を使って、employeeというテーブルからすべての情報をとってくるよう指定している
					ResultSet rs = stmt.executeQuery(sql);
						//			↑ Statementクラスの持つ、executeQueryメソッドにsql文を入れる
						//executeQueryメソッド : SELECT文を扱う（INSERTなど他のSQL文は×）。ResultSet型で戻り値を返す。
						//このとき、データベースへの接続の失敗や、SELECT文以外のSQL文を使う、などの異常があると、
						//SQLexceptionというエラーを出し、catch文にあとの処理を任せる（throwする）
						//要はここでデータベース系の異常が出ると処理がcatchに飛ぶよ！


					//Statementをつかって、データベースの中身を得ることができたので、
					//次はその結果を表示していく。

					//繰り返し文をつかって、表の行を表示していく
					
					while (rs.next()) {
						//rs.nextについて
						//ResultSetオブジェクトはデータの表に加えて、現在、表の中のどの行を見ているか、という情報（カーソル）を持っている。
						//カーソルは初期は一番上にあり、nextメソッドは、カーソルを次の行に進める。
						//繰り返し文を使うことによって、表のデータを一行ごと、上から下まで順番に見ていく感じ。


						//書き出し用の変数を定義
						String nameRead = rs.getString("name");
						String passRead = rs.getString("password");
							//それぞれ、ResultSetオブジェクトのget～～メソッドを使ってデータを取り出している。
							//現在カーソルが一行目を示している場合、
							//一行目のid、name、sectionのデータをそれぞれ取り出す。

						//行を書き出し
						//HTMLのテーブルのタグを使って、↑で拾ってきたデータを一行分記述する。
						
						if (nameRead.equals(name) && passRead.equals(pass)) {
							flag = 1;
							resultA = rs.getInt("resultA");
							resultB = rs.getInt("resultB");
							resultC = rs.getInt("resultC");
							employment = rs.getString("employment");
							break;
						} 

					}//従業員一覧表の書き出しおわり


				//try文からここまで実行の際にSQLに関するエラーが発生したら ↓ が実行される
				} catch (SQLException e) {
					System.out.println("SQLException:" + e.getMessage());
						//コンソールにSQLのエラー内容を出力
				}
				
				if (flag == 0) {
					String disp = "loginFailed.html";
					//pw.println("ユーザーネームかパスワードが違います");
					RequestDispatcher dispatch = request.getRequestDispatcher(disp);

				    dispatch.forward(request, response);
					
				} else {
					HttpSession session = request.getSession();
					
					session.setAttribute("name", name);
					session.setAttribute("password", pass);		
					session.setAttribute("resultA", resultA);
					session.setAttribute("resultB", resultB);
					session.setAttribute("resultC", resultC);
					session.setAttribute("employment", employment);
					
					String disp = "loginSuccess.jsp";
					RequestDispatcher dispatch = request.getRequestDispatcher(disp);

				    dispatch.forward(request, response);
				}
			}
		}
	}

}
