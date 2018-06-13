package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String target = request.getRequestURI();

		HttpSession session = request.getSession(false);

		if (session.getAttribute("userInfo") == null){
			  /* まだ認証されていない */

			  response.sendRedirect("LoginServlet");
			  return;
		}
		request.setCharacterEncoding("UTF-8");

        String str= request.getParameter("id");
        int id = Integer.parseInt(str);

        UserDao userDao = new UserDao();
 		User user = userDao.userDetail(id);

 		HttpSession session2 = request.getSession();
 		session2.setAttribute("user3", user);



		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId");
        UserDao userDao = new UserDao();
 		boolean result = userDao.delUser(loginId);


 		response.sendRedirect("UserListServlet");
		return;
	}

}
