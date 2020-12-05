import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

 public class Order {
	ArrayList<String> orMenu;
	ArrayList<Integer> orCount;
	ArrayList<Integer> orPrice;	
	ArrayList<String> meMenu;
	ArrayList<Integer> mePrice;
	String mobile;
	String name;
	int a;

	void init() {//메뉴관리 배열선언
		meMenu = new ArrayList<>();
		mePrice = new ArrayList<>();
		orMenu = new ArrayList<>();
		orCount = new ArrayList<>();
		orPrice = new ArrayList<>();
	}

	void init2() {//주문받을 배열 초기화
		orMenu = new ArrayList<>();
		orCount = new ArrayList<>();
		orPrice = new ArrayList<>();
		for(int i=0;i<this.meMenu.size();i++) {
			this.orMenu.add(i,"");
			this.orCount.add(i, 0);
			this.orPrice.add(i, 0);
		}
	}
	int init3( ) {
		a=meMenu.size();
		return a;
		
	}
	
	void menuList(Connection conn) throws SQLException {
		String sql = "select menuname, menuprice from cafemenu";
		Statement stmt=null;
		stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			String m_name=rs.getString("menuname");
			int m_price=rs.getInt("menuprice");
			this.meMenu.add(m_name);
			this.mePrice.add(m_price);
		}
	}
	
	void showMenu() {
		for(int i=0;i<meMenu.size();i++) {
			System.out.println(i+1+"번메뉴 "+this.meMenu.get(i)+" "+this.mePrice.get(i)+"원");
		}
	}
	
	void setMenu(int A,int B) {
		orMenu.set(A-1, meMenu.get(A-1));
		orCount.set(A-1, orCount.get(A-1)+B);
		orPrice.set(A-1, orPrice.get(A-1)+mePrice.get(A-1)*B);
	}
	
	void updateMenu(int A,int B) {
		this.orCount.set(A-1,orCount.get(A-1)-B);
		this.orPrice.set(A-1, orPrice.get(A-1)-mePrice.get(A-1)*B);
	}
	
	void deleteMenu(int A) {
		this.orMenu.set(A-1,"");
		this.orCount.set(A-1, 0);
		this.orPrice.set(A-1, 0);
	}
	
	void setPhoneName (String A, String B) {
		
		name = A;
		mobile = B;
	}
	
	void deleteMenu() {
		for(int i=this.meMenu.size()-1;i>-1;i--) {
			if(orMenu.get(i).equals("")) {
				this.orMenu.remove(i);
				this.orPrice.remove(i);
				this.orCount.remove(i);
			}
		}
	}
	
	void middleOrder() {
		for(int i=0;i<this.orMenu.size();i++) {
			if(!orMenu.get(i).equals("")) {
				System.out.println(i+1+"번메뉴 "+this.orMenu.get(i)+" "+this.orCount.get(i)+"잔 "+this.orPrice.get(i)+"원");
			}	
		}	
	}
	
	void lastOrder() {
		for(int i=0;i<this.orMenu.size();i++) {
				System.out.println(this.orMenu.get(i)+" "+this.orCount.get(i)+"잔 "+this.orPrice.get(i)+"원");
		}	
	}
	
	void finalMenu(Connection conn) throws SQLException {
		for(int i=0;i<this.orMenu.size();i++) {
			String sql="insert into orderlist values(?,?,?,?,?,to_char(sysdate,'yyyy,mm,dd'))";  
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, this.name);
			ps.setString(2, this.mobile);
			ps.setString(3, this.orMenu.get(i));
			ps.setInt(4, this.orCount.get(i));
			ps.setInt(5, this.orPrice.get(i));
			ps.executeUpdate();
			ps.close();
		}
     }
       
	
}
