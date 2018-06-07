package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBManager {
	final private static String URL = "jdbc:mysql://localhost/";
	final private static String DB_NAME = "usermanagement?characterEncoding=UTF-8&serverTimezone=JST";
	final private static String USER = "root";
	final private static String PASS = "password";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			//接続の際の決まり文句のようなやつ。DBドライバーをロードし、ドライバーを利用してDBに接続する

			con = DriverManager.getConnection(URL+DB_NAME, USER, PASS);

			//データベースに対する処理
		}catch(SQLException | ClassNotFoundException e) {
			//接続やSQL処理の失敗時の処理とJDBCドライバが見つからないときの処理
			e.printStackTrace();
		//	例外処理　エラーを起こした経緯をだしてくれる。
		}
		return con;//URLへの接続をする。
	}
}
