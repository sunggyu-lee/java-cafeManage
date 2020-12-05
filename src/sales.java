import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class sales {
	
	ArrayList<Integer> allSum;
	ArrayList<Integer> custSum;
	int sum;
	int sum2;
	
	void init() {
		allSum = new ArrayList<>();
		custSum = new ArrayList<>();
	}
	
	void showSales1(Connection conn, String A) throws SQLException {//고객별 매출내역 조회
		String sql = "select name,phone,ordermenu,ordercount,sumprice,to_char(sysdate,'yyyy-mm-dd')" + "from orderlist where name like ";
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql + "'" + A + "%'");
		init();
		System.out.println("주문자명 | 휴대폰번호 | 주문메뉴 | 주문갯수 | 가격 | 주문시간");
		while(rs.next()) {
			String m_name=rs.getString("name");
			String m_phone=rs.getString("phone");
			String m_menu=rs.getString("ordermenu");
			int m_conut=rs.getInt("ordercount");
			int m_price=rs.getInt("sumprice");
			String m_date=rs.getString("to_char(sysdate,'yyyy-mm-dd')");
			System.out.println(m_name+" | "+m_phone+" | "+m_menu+" | "+m_conut+"잔  | "+m_price+"원 | "+m_date);
			this.custSum.add(m_price);
		}
		rs.close();
		stmt.close();
	}
	
	void showSales2(Connection conn) throws SQLException {//매출내역 전부 조회
		String sql =  "select name,phone,ordermenu,ordercount,sumprice,to_char(sysdate,'yyyy-mm-dd') from orderlist where ordertime = to_char(sysdate,'yyyy-mm-dd') order by sysdate";
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		init();
		System.out.println("주문자명 | 휴대폰번호 | 주문메뉴 | 주문갯수 | 가격 | 주문시간");
		while(rs.next()) {
			String m_name=rs.getString("name");
			String m_phone=rs.getString("phone");
			String m_menu=rs.getString("ordermenu");
			int m_conut=rs.getInt("ordercount");
			int m_price=rs.getInt("sumprice");
			String m_date=rs.getString("to_char(sysdate,'yyyy-mm-dd')");
			System.out.println(m_name+" | "+m_phone+" | "+m_menu+" | "+m_conut+"잔  | "+m_price+"원 | "+m_date);
			this.allSum.add(m_price);
		}
		rs.close();
		stmt.close();
	}
	
	void allSales(){//매출전부조회 총 매출액
		for(int i=0;i<allSum.size();i++) {
			sum+=allSum.get(i);
		}
		System.out.println("");
		System.out.println("오늘 총 매출액은 "+sum+"원 입니다.");
		sum=0;
	}
	
	void allSales2(String A){//고객별조회 총 매출액
		for(int i=0;i<custSum.size();i++) {
			sum2+=custSum.get(i);
		}
		System.out.println("");
		System.out.println(A+" 고객님의 오늘 총 매출액은 "+sum2+"원 입니다.");
		sum2=0;
	}
	
	void deleteSales(Connection conn) throws SQLException {//매출내역 제거
		String sql="delete from orderlist";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
		ps.close();
	}
	
	void deleteSales2(Connection conn, String A) throws SQLException {//특정고객의 매출내역 제거
		String sql="delete from orderlist where name=?";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, A);
        ps.executeUpdate();
		ps.close();
	}
}
