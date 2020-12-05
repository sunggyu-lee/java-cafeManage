import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Project2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "system";
		String password = "human123";
		Scanner s = new Scanner(System.in);
		Scanner t = new Scanner(System.in);
		MenuManage me = new MenuManage();
		Order or = new Order();
		sales sa = new sales();
		try {
			Class.forName(driver);
//			System.out.println("jdbc driver �ε� ����");
			DriverManager.getConnection(url, user, password);
			Connection conn=DriverManager.getConnection(url, user, password);
//			System.out.println("����Ŭ ���� ����");
			System.out.println("================================================================");
			System.out.println("1. �޴����� 2.�ֹ����� 3.������� 0.����");
			System.out.println("================================================================");
			int a1 = s.nextInt();
			while(a1!=0) {
				if(a1==1) {//�޴����� ����
					System.out.println("================================================================");
					System.out.println("1. �űԸ޴� �߰� 2.�޴� ���� 3.�޴� ���� 4.��ϵ� �޴� ���� 0.����");
					System.out.println("================================================================");
					int a2=s.nextInt();
					while(a2!=0) {
						if(a2==1) {//�űԸ޴��߰� ����
							System.out.println("���� ����Ͻ� �޴� �̸��� �Է����ּ���. (����� ����Ϸ��� ���͸� �Է��ϼ���)");
							String b1=t.nextLine();
							while(!b1.equals("")) {
								System.out.println("����Ͻ� �޴��� ������ �Է����ּ���.");
								int b2=s.nextInt();
								me.newMenu(conn,b1,b2);
								System.out.println("�űԸ޴� ����� �Ϸ�Ǿ����ϴ�.");
								System.out.println("�� ����Ͻ÷��� �޴� �̸��� �Է��Ͻð� ����� ����Ϸ��� ���͸� �Է��ϼ���");
								b1=t.nextLine();
							}
						}
						else if(a2==2) {//�޴����� ����
							System.out.println("�����Ͻ� �޴� �̸��� �Է����ּ���. �޴������� ����Ϸ��� ���͸� �Է��ϼ���.");
							String c1 = t.nextLine();
							while(!c1.equals("")) {
								System.out.println("�ٲٽ� �޴� �̸��� �Է����ּ���.");
								String c2 = t.nextLine();
								System.out.println("�ٲٽ� �޴��� ������ �Է����ּ���.");
								int c3 = s.nextInt();
								me.updateMenu(conn, c1, c2, c3);
								System.out.println("�޴������� �Ϸ�Ǿ����ϴ�.");
								System.out.println("�� �����Ͻ� �޴��� �����ø� �����Ͻ� �޴��̸��� �Է����ֽð� ������ ����Ϸ��� ���͸� �Է��ϼ���.");
								c1 = t.nextLine();
							}
						}
						else if(a2==3) {//�޴����� ����
							System.out.println("�����Ͻ� �޴� �̸��� �Է����ּ���. ���Ÿ� ����Ϸ��� ���͸� �Է��ϼ���.");
							String d1 = t.nextLine();
							while(!d1.equals("")) {
								System.out.println("���� "+d1+" �޴��� �����Ͻðڽ��ϱ�? �����ϸ� ������ �� �����ϴ�.");
								System.out.println("�����Ͻ÷��� 1�� �Է����ּ���.(1���� �ٸ��� �Է½� �������)");
								int d2 = s.nextInt();
								if(d2==1) {
									me.deleteMenu(conn, d1);
									System.out.println("�޴� ���Ű� �Ϸ�Ǿ����ϴ�.");
								}else {
									System.out.println("�޴� ���Ű� ��ҵǾ����ϴ�.");
								}
								System.out.println("�� �����Ͻ� �޴��� ������ �޴��̸��� �Է����ּ���. ���Ÿ� ����Ϸ��� ���͸� �Է��ϼ���.");
								d1 = t.nextLine();
							}
							
						}
						else if(a2==4) {//��ϵ� �޴� ����
							System.out.println("���� ��ϵ� �޴��Դϴ�.");
							System.out.println("�޴��̸� | �޴�����");
							me.showMenu(conn);
						}
						else {
							System.out.println("�޴���ȣ�� �߸��Է��Ͽ����ϴ�.");
						}
						System.out.println("================================================================");
						System.out.println("1. �űԸ޴� �߰� 2.�޴� ���� 3.�޴� ���� 4.��ϵ� �޴� ���� 0.����");
						System.out.println("================================================================");
						a2=s.nextInt();
					}
				}
				else if(a1==2) {//�ֹ����� ����
					or.init();
					or.menuList(conn);
					or.init2();
					System.out.println("================================================================");
					System.out.println("1. �ֹ��޴� �߰� 2. �ֹ��޴� ���� 3.�ֹ� �Ϸ� 0.�ֹ� ���");
					System.out.println("================================================================");
					int e1 = s.nextInt();
					while(e1!=0) {
						if(e1==1) {
							System.out.println("�ֹ��Ͻ� �޴���ȣ�� �Է��ϼ��� ����Ͻ÷��� 0���Է�");
							or.showMenu();
							int e2 = s.nextInt();
							while(e2!=0) {
								System.out.println("�ֹ��Ͻ� �޴��� ������ �Է��ϼ���");
								int e3 = s.nextInt();
								or.setMenu(e2, e3);
								System.out.println("�� �ֹ��Ͻ÷��� �޴���ȣ�� �Է��Ͻð� ����Ͻ÷��� 0���� �Է����ּ���.");
								e2 = s.nextInt();
							}
							System.out.println("�ֹ� �߰��� �Ϸ�Ǿ����ϴ�. �ֹ��� �Ϸ��Ͻ÷��� 3���޴��� �������ּ���!");
						}
						else if(e1==2) {
							System.out.println("�����Ͻ� �޴���ȣ�� �Է��ϼ��� ����Ͻ÷��� 0���Է�");
							or.middleOrder();
							e1 = s.nextInt();
							while(e1!=0) {
								System.out.println("�����Ͻ� �޴��� ������ �Է��ϼ��� 0�Է½� �޴��� ����ϴ�.");
								int e2 = s.nextInt();
								if(e2!=0) {
									or.updateMenu(e1, e2);
									System.out.println(e1+"���޴��� ������ "+e2+" �� �����Ͽ����ϴ�.");
								}else if(e2==0){
									or.deleteMenu(e1);
									System.out.println(e1+"�� �޴��� �����߽��ϴ�.");
								}
								System.out.println("�� �����Ͻ÷��� �޴���ȣ�� �Է��Ͻð� ����Ͻ÷��� 0���� �Է����ּ���.");
								e1 = s.nextInt();
							}
							System.out.println("�ֹ� ���Ű� �Ϸ�Ǿ����ϴ�. �ֹ��� �Ϸ��Ͻ÷��� 3���޴��� �������ּ���!");
						}
						else if(e1==3) {
							System.out.println("================================================================");
							System.out.println("���� �ֹ��Ͻ� �����Դϴ�.");
							System.out.println("================================================================");
							or.deleteMenu();
							or.lastOrder();
							System.out.println(or.orMenu.size());
							System.out.println("�̴�� �ֹ��Ͻðڽ��ϱ�? �ֹ��Ͻ÷��� 1���� ����Ͻ÷��� 0���� �Է��ϼ���.");
							int e4 = s.nextInt();
							if(e4==1) {
								System.out.println("�ֹ��ϷḦ ���� �ֹ����� �̸��� �Է����ּ���.");
								String e5 = t.nextLine();
								System.out.println("�ֹ����� �޴�����ȣ�� �Է����ּ���.");
								String e6 = t.nextLine();
								or.setPhoneName(e5,e6);
								or.finalMenu(conn);
								System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�.");
								or.init2();
							}else {
								System.out.println("�ֹ��� ��ҵǾ����ϴ�.");
								or.init2();
							}
						}
						System.out.println("================================================================");
						System.out.println("1. �ֹ��޴� �߰� 2. �ֹ��޴� ���� 3.�ֹ� �Ϸ� 0.�ֹ� ���");
						System.out.println("================================================================");
						e1 = s.nextInt();
					}
				}
				else if(a1==3) {//������� ����
					System.out.println("================================================================");
					System.out.println("1. ���� ���� ��ȸ 2. ���� ��ȸ 3.���⳻�� ���� 0.���");
					System.out.println("================================================================");
					int f1 = s.nextInt();
					while(f1!=0) {
						if(f1==1) {
							System.out.println("������ �Ǹų����Դϴ�.");
							sa.showSales2(conn);
							sa.allSales();
							System.out.println("�����÷��� 0���� �Է��ϼ���.");
							f1 = s.nextInt();	
						}
						else if(f1==2) {
							System.out.println("���⳻���� ��ȸ�� ������ �Է��ϼ��� (�����÷��� ���� �Է�)");
							String f2=t.nextLine();
							while(!f2.equals("")) {
								sa.showSales1(conn,f2);
								sa.allSales2(f2);
								System.out.println("���⳻���� ��ȸ�� ������ �Է��ϼ��� (�����÷��� ���� �Է�)");
								f2=t.nextLine();
							}
						}
						else if(f1==3) {
							System.out.println("���⳻���� �����Ͻðڽ��ϱ�?");
							System.out.println("���� �����Ϸ��� 1, Ư������ �����Ϸ��� 2, ��Ҵ� ���͸� �����ּ���");
							String f3=t.nextLine();
							if(f3.equals("1")) {
								System.out.println("���⳻���� ���� �����Ͻðڽ��ϱ�? �����ϸ� �ǵ��� �� �����ϴ�.");
								System.out.println("�����Ͻ÷��� ���� ��� �Է����ּ���. (��Ҵ� ���� �Է�)");
								f3=t.nextLine();
								if(f3.equals("����")) {
									sa.deleteSales(conn);
									System.out.println("���⳻���� ���� �����Ǿ����ϴ�.");
								}else {
									System.out.println("������ ��ҵǾ����ϴ�.");
								}
							}
							if(f3.equals("2")) {
								System.out.println("���⳻���� ������ ������ �Է��ϼ���");
								f3=t.nextLine();
								System.out.println(f3+" ������ ���⳻���� ���� �����Ͻðڽ��ϱ�?");
								System.out.println("�����ϸ� �ǵ��� �� �����ϴ�. �����Ͻ÷��� ������� �Է����ּ���.");
								String f4=t.nextLine();
								if(f4.equals("����")) {
									sa.deleteSales2(conn,f3);
									System.out.println(f3+"������ ���⳻���� ���� �����Ǿ����ϴ�.");
								}else {
									System.out.println("������ ��ҵǾ����ϴ�.");
								}
							}
						}
						System.out.println("================================================================");
						System.out.println("1. ���� ���� ��ȸ 2. ���� ��ȸ 3. ���⳻�� ���� 0.���");
						System.out.println("================================================================");
						f1 = s.nextInt();
					}
				}else {
					System.out.println("�޴� ��ȣ�� �߸��Է��ϼ̽��ϴ�.");
				}
				System.out.println("================================================================");
				System.out.println("1. �޴����� 2.�ֹ����� 3.������� 0.����");
				System.out.println("================================================================");
				a1 = s.nextInt();
			}
			System.out.println("ī������ý����� �����մϴ�.");
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException e) {
			System.out.println("����Ŭ ���� ����");
		}
	}

}
