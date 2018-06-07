package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//自動生成するとget.Writerがついてくるのでけす。

		//ログイン画面のJSPファイルをフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);      自動生成でついてくる戻り値は消す！

		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        //リクエストパラメータの取得
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        	// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
     		UserDao userDao = new UserDao();
     		User user = userDao.findByLoginInfo(loginId, password);


        	//該当データなし（入力間違いなども含む）
        	if(user == null) {
        		request.setAttribute("errMsg", "ユーザーが見つかりません。登録がないかIDやパスワードに入力誤りがあります。");

        		//もう一度login.jsp画面を出す
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        		dispatcher.forward(request, response);
        		return;     //データが見つかった場合は次の処理に行く。
        	}

        	//該当データあり　リクエストをまたいてでインスタンスを利用する為、セッションスコープを利用する
        	//HttpSEssionインスタンスの取得
        	HttpSession session = request.getSession();
        	//属性名を指定してスコープにインスタンスを保存　すでに同じ属性名でインスタンスが保存されている場合は上書きされる　　
        	session.setAttribute("userInfo", user);     //保存するデータを名前と値のペアで登録。名前はString型、値はObject型

        	//同じサイトだけど新しくページを表示する時なのでフォワードではなく、リダイレクトを使う。？？
        	//リクエスト情報を引き継げないという特徴をもっていいる。
        	//リダイレクトの方がトラフィックは多い。
        	//なぜ使う？？
        	response.sendRedirect("userList.jsp");




	}

}
