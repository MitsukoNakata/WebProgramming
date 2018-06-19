package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログインセッションの有無のチェック
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String target = request.getRequestURI();

		HttpSession session = request.getSession(false);

		if (session.getAttribute("userInfo") == null){
			  /* まだ認証されていない */

			  response.sendRedirect("LoginServlet");
			  return;
		}


			// ユーザ一覧情報を取得全体の情報
			UserDao userDao = new UserDao();
			List<User> userList = userDao.findAll();

			// リクエストスコープにfilndAllで取得した全ユーザリストをセット。
			request.setAttribute("userList", userList);

			// ユーザ一覧のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        //フォームで入力された内容を取得
        String loginId = request.getParameter("loginId");
        String name = request.getParameter("name");
        String dateStart = request.getParameter("date-start");
        String dateEnd = request.getParameter("date-end");

		request.setAttribute("loginId", loginId);
		request.setAttribute("name", name);
		request.setAttribute("dateStart", dateStart);
		request.setAttribute("dateEnd", dateEnd);

		UserDao userDao = new UserDao();
		List<User> userList = userDao.searchUser(loginId,name,dateStart,dateEnd);

		if(userList == null) {
        request.setAttribute("errMsg", "該当のユーザーが見つかりません。");

        		//もう一度userList.jsp画面を出す
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
        		dispatcher.forward(request, response);
        		return;     //データが見つかった場合は次の処理に行く。
        	}

			request.setAttribute("userList", userList);

			// ユーザ一覧のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);
	}

}
