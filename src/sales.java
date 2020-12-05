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
	
	void showSales1(Connection conn, String A) throws SQLException {//���� ���⳻�� ��ȸ
		String sql = "select name,phone,ordermenu,ordercount,sumprice,to_char(sysdate,'yyyy-mm-dd')" + "from orderlist where name like ";
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql + "'" + A + "%'");
		init();
		System.out.println("�ֹ��ڸ� | �޴�����ȣ | �ֹ��޴� | �ֹ����� | ���� | �ֹ��ð�");
		while(rs.next()) {
			String m_name=rs.getString("name");
			String m_phone=rs.getString("phone");
			String m_menu=rs.getString("ordermenu");
			int m_conut=rs.getInt("ordercount");
			int m_price=rs.getInt("sumprice");
			String m_date=rs.getString("to_char(sysdate,'yyyy-mm-dd')");
			System.out.println(m_name+" | "+m_phone+" | "+m_menu+" | "+m_conut+"��  | "+m_price+"�� | "+m_date);
			this.custSum.add(m_price);
		}
		rs.close();
		stmt.close();
	}
	
	void showSales2(Connection conn) throws SQLException {//���⳻�� ���� ��ȸ
		String sql =  "select name,phone,ordermenu,ordercount,sumprice,to_char(sysdate,'yyyy-mm-dd') from orderlist where ordertime = to_char(sysdate,'yyyy-mm-dd') order by sysdate";
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		init();
		System.out.println("�ֹ��ڸ� | �޴�����ȣ | �ֹ��޴� | �ֹ����� | ���� | �ֹ��ð�");
		while(rs.next()) {
			String m_name=rs.getString("name");
			String m_phone=rs.getString("phone");
			String m_menu=rs.getString("ordermenu");
			int m_conut=rs.getInt("ordercount");
			int m_price=rs.getInt("sumprice");
			String m_date=rs.getString("to_char(sysdate,'yyyy-mm-dd')");
			System.out.println(m_name+" | "+m_phone+" | "+m_menu+" | "+m_conut+"��  | "+m_price+"�� | "+m_date);
			this.allSum.add(m_price);
		}
		rs.close();
		stmt.close();
	}
	
	void allSales(){//����������ȸ �� �����
		for(int i=0;i<allSum.size();i++) {
			sum+=allSum.get(i);
		}
		System.out.println("");
		System.out.println("���� �� ������� "+sum+"�� �Դϴ�.");
		sum=0;
	}
	
	void allSales2(String A){//������ȸ �� �����
		for(int i=0;i<custSum.size();i++) {
			sum2+=custSum.get(i);
		}
		System.out.println("");
		System.out.println(A+" ������ ���� �� ������� "+sum2+"�� �Դϴ�.");
		sum2=0;
	}
	
	void deleteSales(Connection conn) throws SQLException {//���⳻�� ����
		String sql="delete from orderlist";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
		ps.close();
	}
	
	void deleteSales2(Connection conn, String A) throws SQLException {//Ư������ ���⳻�� ����
		String sql="delete from orderlist where name=?";  
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, A);
        ps.executeUpdate();
		ps.close();
	}
}
