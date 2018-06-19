package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;
//DAOで分けて開発する理由。ソースコードの見通しがよくなり、利用するデータベースに変更があっても修正箇所は
//最低限に抑えられる。すなわち、データベースに関する仕様の変更に対応しやすくなる。


public class UserDao {

	public User findByLoginInfo(String loginId, String password) {

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

		Connection conn = null;
	        try {
	            // データベースへ接続
	            conn = DBManager.getConnection();

	            // SELECT文を準備
	            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

	             // SELECTを実行し、結果表を取得
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            pStmt.setString(1, loginId);
	            pStmt.setString(2, password_s);
	            ResultSet rs = pStmt.executeQuery();

	             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
	            if (!rs.next()) {
	                return null;
	            }

	            String loginIdData = rs.getString("login_id");
	            String nameData = rs.getString("name");
	            int idData = rs.getInt("id");
	            return new User(loginIdData, nameData,idData);

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        }
	    }

	    public List<User> findAll() {
	        Connection conn = null;
	        List<User> userList = new ArrayList<User>();

	        try {
	            // データベースへ接続
	            conn = DBManager.getConnection();

	            // SELECT文を準備

	            String sql = "SELECT * FROM user WHERE id not in (1)";

	             // SELECTを実行し、結果表を取得
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);

	            // 結果表に格納されたレコードの内容を
	            // Userインスタンスに設定し、ArrayListインスタンスに追加
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String loginId = rs.getString("login_id");
	                String name = rs.getString("name");
	                Date birthDate = rs.getDate("birth_date");
	                String password = rs.getString("password");
	                String createDate = rs.getString("create_date");
	                String updateDate = rs.getString("update_date");
	                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

	                userList.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        }
	        return userList;
	    }

	    public boolean regUser(String loginId, String password,String name,String birthDate) {
	    	Connection conn = null;
	    	 try {
	             // データベースへ接続
	             conn = DBManager.getConnection();

	             //INSERT文の準備
	             String sql = "INSERT INTO user (login_id,password,name,birth_date,create_date,update_date) VALUES (?,?,?,?,now(),now())";

	             PreparedStatement pStmt = conn.prepareStatement(sql);
	             pStmt.setString(1, loginId);
	             pStmt.setString(2, password);
	             pStmt.setString(3, name);
	             pStmt.setString(4, birthDate);
	             pStmt.executeUpdate();


	    	 } catch(SQLException e){
	    		 e.printStackTrace();
	    		 return false;
	         } finally {
	             //終了処理
	             //Connection終了
	             if(conn != null) {
	                 try {
	                     conn.close();
	                 } catch (SQLException e) {
	                 }
	                 conn = null;
	             }
	         }

	    	 return true;
	    }

		public User userDetail(int id) {
	        Connection conn = null;
		        try {
		            // データベースへ接続
		            conn = DBManager.getConnection();

		            // SELECT文を準備
		            String sql = "SELECT * FROM user WHERE id = ?";

		             // SELECTを実行し、結果表を取得
		            PreparedStatement pStmt = conn.prepareStatement(sql);

		            pStmt.setInt(1, id);
		            ResultSet rs = pStmt.executeQuery();


		            if (!rs.next()) {
		                return null;
		            }
		                String loginId = rs.getString("login_id");
			            String name = rs.getString("name");
		                Date birthDate = rs.getDate("birth_date");
		                String password = rs.getString("password");
		                String createDate = rs.getString("create_date");
		                String updateDate = rs.getString("update_date");
			            return new User(id,loginId,name,birthDate,password,createDate,updateDate);

		        } catch (SQLException e) {
		            e.printStackTrace();
		            return null;
		        } finally {
		            // データベース切断
		            if (conn != null) {
		                try {
		                    conn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    return null;
		                }
		            }
		        }
		    }


		public boolean updateUserWithP(String loginId,String password,String name,String birthDate) {
	    	Connection conn = null;
	    	 try {
	             // データベースへ接続
	             conn = DBManager.getConnection();

	             //INSERT文の準備
	             String sql = "UPDATE user SET password = ?,name =?,birth_date = ?,update_date=now() WHERE login_id = ?";

	             PreparedStatement pStmt = conn.prepareStatement(sql);
	             pStmt.setString(1, password);
	             pStmt.setString(2, name);
	             pStmt.setString(3, birthDate);
	             pStmt.setString(4, loginId);
	             pStmt.executeUpdate();


	    	 } catch(SQLException e){
	    		 e.printStackTrace();
	    		 return false;
	         } finally {
	             //終了処理
	             //Connection終了
	             if(conn != null) {
	                 try {
	                     conn.close();
	                 } catch (SQLException e) {
	                 }
	                 conn = null;
	             }
	         }

	    	 return true;
	    }
		public boolean updateUserWithoutP(String loginId,String name,String birthDate) {
	    	Connection conn = null;
	    	 try {
	             // データベースへ接続
	             conn = DBManager.getConnection();

	             //INSERT文の準備
	             String sql = "UPDATE user SET name =?,birth_date = ?,update_date=now() WHERE login_id = ?";

	             PreparedStatement pStmt = conn.prepareStatement(sql);

	             pStmt.setString(1, name);
	             pStmt.setString(2, birthDate);
	             pStmt.setString(3, loginId);
	             pStmt.executeUpdate();


	    	 } catch(SQLException e){
	    		 e.printStackTrace();
	    		 return false;
	         } finally {
	             //終了処理
	             //Connection終了
	             if(conn != null) {
	                 try {
	                     conn.close();
	                 } catch (SQLException e) {
	                 }
	                 conn = null;
	             }
	         }

	    	 return true;
	    }
		public boolean delUser(String loginId) {
	    	Connection conn = null;
	    	 try {
	             // データベースへ接続
	             conn = DBManager.getConnection();

	             //INSERT文の準備
	             String sql = "DELETE from user WHERE login_id = ?";

	             PreparedStatement pStmt = conn.prepareStatement(sql);
	             pStmt.setString(1, loginId);
	             pStmt.executeUpdate();


	    	 } catch(SQLException e){
	    		 e.printStackTrace();
	    		 return false;
	         } finally {
	             //終了処理
	             //Connection終了
	             if(conn != null) {
	                 try {
	                     conn.close();
	                 } catch (SQLException e) {
	                 }
	                 conn = null;
	             }
	         }

	    	 return true;
	    }

		//ユーザ検索用

	    public List<User> searchUser(String loginId,String name,String dateStart,String dateEnd) {
	        Connection conn = null;
	        List<User> userList = new ArrayList<User>();

	        try {
	            // データベースへ接続
	            conn = DBManager.getConnection();

	            // SELECT文を準備
	           //idで検索


	            String sql  = "SELECT * FROM user"
	            				+ " WHERE NULLIF(login_id,'') = COALESCE(?,NULLIF(login_id,''))"
	            				+" AND name LIKE ?"
	            				+" AND birth_date >= ?"
	            				+" AND NULLIF(birth_date,current_date) <= COALESCE(?,NULLIF(birth_date,current_date))"
	            				+" AND id NOT IN (1)";

	             // SELECTを実行し、結果表を取得
	            PreparedStatement pStmt = conn.prepareStatement(sql);

	            if(loginId.isEmpty()) {
	            loginId = null;
	            }if(dateEnd.isEmpty()) {
	            	dateEnd = null;
	            }

	            pStmt.setString(1, loginId);
	            pStmt.setString(2, "%" + name + "%");
		        pStmt.setString(3, dateStart);
		        pStmt.setString(4, dateEnd);

	            ResultSet rs = pStmt.executeQuery();

	            // 結果表に格納されたレコードの内容を
	            // Userインスタンスに設定し、ArrayListインスタンスに追加
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                loginId = rs.getString("login_id");
	                name = rs.getString("name");
	                Date birthDate = rs.getDate("birth_date");
	                String password = rs.getString("password");
	                String createDate = rs.getString("create_date");
	                String updateDate = rs.getString("update_date");
	                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);
	                userList.add(user);

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            // データベース切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        }
	        if (userList.size()==0) {
                return null;
	        }
            return userList;
	        }


}

