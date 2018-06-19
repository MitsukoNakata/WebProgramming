package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		String target = request.getRequestURI();

		HttpSession session = request.getSession(false);

		if (session.getAttribute("userInfo") == null){
			  /* まだ認証されていない */

			  response.sendRedirect("LoginServlet");
			  return;
		}

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String birthDate= request.getParameter("birthDate");

        boolean log_b = loginId.isEmpty();
		boolean pas_b = password.isEmpty();
		boolean pas2_b = password2.isEmpty();
		boolean nm_b = name.isEmpty();
		boolean bB_b = birthDate.isEmpty();


		request.setAttribute("loginId", loginId);
		request.setAttribute("name", name);
		request.setAttribute("birthDate", birthDate);

		if(log_b || pas_b || pas2_b || nm_b || bB_b) { //名前、誕生日に空欄があった場合エラーを出す

			request.setAttribute("errMsg", "未入力の項目があります");
	   		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
	   		dispatcher.forward(request, response);
	   		return;

				}else if (password.equals(password2)){//入力されたパスワードが一致すれば、すべての値を更新する


				String source = password;

				Charset charset = StandardCharsets.UTF_8;

				String algorithm = "MD5";

				String password_s = null;
				try {
					byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
					password_s = DatatypeConverter.printHexBinary(bytes);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
					UserDao userDao = new UserDao();
			 		boolean result2 = userDao.regUser(loginId, password_s,name,birthDate);

			 		if(result2==false) {

			 			request.setAttribute("errMsg", "入力されたログインIDは既に使用されてます");
				   		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
				   		dispatcher.forward(request, response);
			 		}

			 		response.sendRedirect("UserListServlet");

				}else {//それ以外のものはエラーを出す。

					request.setAttribute("errMsg", "入力されたパスワードが一致しません");
			   		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			   		dispatcher.forward(request, response);
			   		return;

					}
		return;

	}

}