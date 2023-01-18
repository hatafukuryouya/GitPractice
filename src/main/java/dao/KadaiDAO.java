package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Kadai16;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class KadaiDAO {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	// 引数の book インスタンスを元にデータを全件表示するメソッド
	// データを全件取得する
	public static List<Kadai16> selectAllKadai16() {
			
			// 返却用変数
		List<Kadai16> result = new ArrayList<>();
		String sql = "SELECT * FROM kadai16 ";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					int id=rs.getInt("id");
					String name=rs.getString("name");
					int age=rs.getInt("age");
					String  gender= rs.getString("gender");
					String tel=rs.getString("tel");
					String mail=rs.getString("mail");
					
					Kadai16 kadai16 =new Kadai16(id,name,age,gender,tel,mail,null,null,null);
					
					result.add(kadai16);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	
	public static int registerKadai16(Kadai16 kadai16) {
		String sql = "INSERT INTO kadai16 VALUES(default, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		
		// ランダムなソルトの取得(今回は32桁で実装)
		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(kadai16.getPassword(), salt);
		System.out.println("生成されたソルト:"+ salt);
		System.out.println("生成されたハッシュ値:"+hashedPw);
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, kadai16.getName());
			pstmt.setInt(2, kadai16.getAge());
			pstmt.setString(3, kadai16.getGender());
			pstmt.setString(4, kadai16.getTel());
			pstmt.setString(5, kadai16.getMail());
			pstmt.setString(6, salt);
			pstmt.setString(7, hashedPw);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	public static int deleteKadai16(String mail) {
		String sql = " DELETE FROM kadai16 WHERE mail =?";
		int result = 0;
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1,mail);
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件削除しました。");
		}
		return result;
	}
	// メールアドレスを元にソルトを取得
		public static String getSalt(String mail) {
			String sql = "SELECT salt FROM account WHERE mail = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String salt = rs.getString("salt");
						return salt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// ログイン処理
		public static Kadai16 login(String mail, String hashedPw) {
			String sql = "SELECT * FROM kadai16 WHERE mail = ? AND password = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);
				pstmt.setString(2, hashedPw);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String salt = rs.getString("salt");
						
						
						return new Kadai16(id, name, 0, mail, salt, null, null, null, null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
}
