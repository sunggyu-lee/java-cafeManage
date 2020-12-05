import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuManage {

	void newMenu(Connection conn,String A, int B) throws SQLException{
		String sql="insert into cafemenu values(?,?)";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, A);
		ps.setInt(2, B);
		ps.executeUpdate();
		ps.close();
	}
	
	void updateMenu(Connection conn, String A, String B, int C) throws SQLException {
		String sql="update cafemenu set menuname=?, menuprice=? where menuname=?";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, B);
		ps.setInt(2, C);
		ps.setString(3, A);
		ps.executeUpdate();
		ps.close();
	}
	
	void deleteMenu(Connection conn, String A) throws SQLException {
		String sql="delete from cafemenu where menuname=?";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, A);
        ps.executeUpdate();
		ps.close();
	}
	
	void showMenu(Connection conn) throws SQLException {
		String sql = "select menuname, menuprice from cafemenu order by menuname";
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			String m_name=rs.getString("menuname");
			int m_price=rs.getInt("menuprice");
			System.out.println(m_name+" "+m_price+"¿ø");
		}
		rs.close();
	}
}
