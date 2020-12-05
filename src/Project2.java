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
//			System.out.println("jdbc driver 로딩 성공");
			DriverManager.getConnection(url, user, password);
			Connection conn=DriverManager.getConnection(url, user, password);
//			System.out.println("오라클 연결 성공");
			System.out.println("================================================================");
			System.out.println("1. 메뉴관리 2.주문관리 3.매출관리 0.종료");
			System.out.println("================================================================");
			int a1 = s.nextInt();
			while(a1!=0) {
				if(a1==1) {//메뉴관리 실행
					System.out.println("================================================================");
					System.out.println("1. 신규메뉴 추가 2.메뉴 수정 3.메뉴 제거 4.등록된 메뉴 보기 0.종료");
					System.out.println("================================================================");
					int a2=s.nextInt();
					while(a2!=0) {
						if(a2==1) {//신규메뉴추가 실행
							System.out.println("새로 등록하실 메뉴 이름을 입력해주세요. (등록을 취소하려면 엔터를 입력하세요)");
							String b1=t.nextLine();
							while(!b1.equals("")) {
								System.out.println("등록하실 메뉴의 가격을 입력해주세요.");
								int b2=s.nextInt();
								me.newMenu(conn,b1,b2);
								System.out.println("신규메뉴 등록이 완료되었습니다.");
								System.out.println("더 등록하시려면 메뉴 이름을 입력하시고 등록을 취소하려면 엔터를 입력하세요");
								b1=t.nextLine();
							}
						}
						else if(a2==2) {//메뉴수정 실행
							System.out.println("수정하실 메뉴 이름을 입력해주세요. 메뉴수정을 취소하려면 엔터를 입력하세요.");
							String c1 = t.nextLine();
							while(!c1.equals("")) {
								System.out.println("바꾸실 메뉴 이름을 입력해주세요.");
								String c2 = t.nextLine();
								System.out.println("바꾸신 메뉴의 가격을 입력해주세요.");
								int c3 = s.nextInt();
								me.updateMenu(conn, c1, c2, c3);
								System.out.println("메뉴수정이 완료되었습니다.");
								System.out.println("더 수정하실 메뉴가 있으시면 수정하실 메뉴이름을 입력해주시고 수정을 취소하려면 엔터를 입력하세요.");
								c1 = t.nextLine();
							}
						}
						else if(a2==3) {//메뉴제거 실행
							System.out.println("제거하실 메뉴 이름을 입력해주세요. 제거를 취소하려면 엔터를 입력하세요.");
							String d1 = t.nextLine();
							while(!d1.equals("")) {
								System.out.println("정말 "+d1+" 메뉴를 제거하시겠습니까? 제거하면 복구할 수 없습니다.");
								System.out.println("제거하시려면 1을 입력해주세요.(1제외 다른값 입력시 제거취소)");
								int d2 = s.nextInt();
								if(d2==1) {
									me.deleteMenu(conn, d1);
									System.out.println("메뉴 제거가 완료되었습니다.");
								}else {
									System.out.println("메뉴 제거가 취소되었습니다.");
								}
								System.out.println("더 제거하실 메뉴가 있으면 메뉴이름을 입력해주세요. 제거를 취소하려면 엔터를 입력하세요.");
								d1 = t.nextLine();
							}
							
						}
						else if(a2==4) {//등록된 메뉴 보기
							System.out.println("현재 등록된 메뉴입니다.");
							System.out.println("메뉴이름 | 메뉴가격");
							me.showMenu(conn);
						}
						else {
							System.out.println("메뉴번호를 잘못입력하였습니다.");
						}
						System.out.println("================================================================");
						System.out.println("1. 신규메뉴 추가 2.메뉴 수정 3.메뉴 제거 4.등록된 메뉴 보기 0.종료");
						System.out.println("================================================================");
						a2=s.nextInt();
					}
				}
				else if(a1==2) {//주문관리 실행
					or.init();
					or.menuList(conn);
					or.init2();
					System.out.println("================================================================");
					System.out.println("1. 주문메뉴 추가 2. 주문메뉴 제거 3.주문 완료 0.주문 취소");
					System.out.println("================================================================");
					int e1 = s.nextInt();
					while(e1!=0) {
						if(e1==1) {
							System.out.println("주문하실 메뉴번호를 입력하세요 취소하시려면 0번입력");
							or.showMenu();
							int e2 = s.nextInt();
							while(e2!=0) {
								System.out.println("주문하실 메뉴의 수량을 입력하세요");
								int e3 = s.nextInt();
								or.setMenu(e2, e3);
								System.out.println("더 주문하시려면 메뉴번호를 입력하시고 취소하시려면 0번을 입력해주세요.");
								e2 = s.nextInt();
							}
							System.out.println("주문 추가가 완료되었습니다. 주문을 완료하시려면 3번메뉴를 실행해주세요!");
						}
						else if(e1==2) {
							System.out.println("제거하실 메뉴번호를 입력하세요 취소하시려면 0번입력");
							or.middleOrder();
							e1 = s.nextInt();
							while(e1!=0) {
								System.out.println("제거하실 메뉴의 수랑을 입력하세요 0입력시 메뉴를 지웁니다.");
								int e2 = s.nextInt();
								if(e2!=0) {
									or.updateMenu(e1, e2);
									System.out.println(e1+"번메뉴의 수량을 "+e2+" 개 제거하였습니다.");
								}else if(e2==0){
									or.deleteMenu(e1);
									System.out.println(e1+"번 메뉴를 제거했습니다.");
								}
								System.out.println("더 제거하시려면 메뉴번호를 입력하시고 취소하시려면 0번을 입력해주세요.");
								e1 = s.nextInt();
							}
							System.out.println("주문 제거가 완료되었습니다. 주문을 완료하시려면 3번메뉴를 실행해주세요!");
						}
						else if(e1==3) {
							System.out.println("================================================================");
							System.out.println("현재 주문하신 내역입니다.");
							System.out.println("================================================================");
							or.deleteMenu();
							or.lastOrder();
							System.out.println(or.orMenu.size());
							System.out.println("이대로 주문하시겠습니까? 주문하시려면 1번을 취소하시려면 0번을 입력하세요.");
							int e4 = s.nextInt();
							if(e4==1) {
								System.out.println("주문완료를 위해 주문자의 이름을 입력해주세요.");
								String e5 = t.nextLine();
								System.out.println("주문자의 휴대폰번호를 입력해주세요.");
								String e6 = t.nextLine();
								or.setPhoneName(e5,e6);
								or.finalMenu(conn);
								System.out.println("주문이 완료되었습니다.");
								or.init2();
							}else {
								System.out.println("주문이 취소되었습니다.");
								or.init2();
							}
						}
						System.out.println("================================================================");
						System.out.println("1. 주문메뉴 추가 2. 주문메뉴 제거 3.주문 완료 0.주문 취소");
						System.out.println("================================================================");
						e1 = s.nextInt();
					}
				}
				else if(a1==3) {//매출관리 실행
					System.out.println("================================================================");
					System.out.println("1. 매출 전부 조회 2. 고객별 조회 3.매출내역 삭제 0.취소");
					System.out.println("================================================================");
					int f1 = s.nextInt();
					while(f1!=0) {
						if(f1==1) {
							System.out.println("오늘의 판매내역입니다.");
							sa.showSales2(conn);
							sa.allSales();
							System.out.println("나가시려면 0번을 입력하세요.");
							f1 = s.nextInt();	
						}
						else if(f1==2) {
							System.out.println("매출내역을 조회할 고객명을 입력하세요 (나가시려면 엔터 입력)");
							String f2=t.nextLine();
							while(!f2.equals("")) {
								sa.showSales1(conn,f2);
								sa.allSales2(f2);
								System.out.println("매출내역을 조회할 고객명을 입력하세요 (나가시려면 엔터 입력)");
								f2=t.nextLine();
							}
						}
						else if(f1==3) {
							System.out.println("매출내역을 삭제하시겠습니까?");
							System.out.println("전부 삭제하려면 1, 특정고객만 삭제하려면 2, 취소는 엔터를 눌러주세요");
							String f3=t.nextLine();
							if(f3.equals("1")) {
								System.out.println("매출내역을 전부 삭제하시겠습니까? 삭제하면 되돌릴 수 없습니다.");
								System.out.println("삭제하시려면 삭제 라고 입력해주세요. (취소는 엔터 입력)");
								f3=t.nextLine();
								if(f3.equals("삭제")) {
									sa.deleteSales(conn);
									System.out.println("매출내역이 전부 삭제되었습니다.");
								}else {
									System.out.println("삭제가 취소되었습니다.");
								}
							}
							if(f3.equals("2")) {
								System.out.println("매출내역을 삭제할 고객명을 입력하세요");
								f3=t.nextLine();
								System.out.println(f3+" 고객님의 매출내역을 정말 삭제하시겠습니까?");
								System.out.println("삭제하면 되돌릴 수 없습니다. 삭제하시려면 삭제라고 입력해주세요.");
								String f4=t.nextLine();
								if(f4.equals("삭제")) {
									sa.deleteSales2(conn,f3);
									System.out.println(f3+"고객님의 매출내역이 전부 삭제되었습니다.");
								}else {
									System.out.println("삭제가 취소되었습니다.");
								}
							}
						}
						System.out.println("================================================================");
						System.out.println("1. 매출 전부 조회 2. 고객별 조회 3. 매출내역 삭제 0.취소");
						System.out.println("================================================================");
						f1 = s.nextInt();
					}
				}else {
					System.out.println("메뉴 번호를 잘못입력하셨습니다.");
				}
				System.out.println("================================================================");
				System.out.println("1. 메뉴관리 2.주문관리 3.매출관리 0.종료");
				System.out.println("================================================================");
				a1 = s.nextInt();
			}
			System.out.println("카페관리시스템을 종료합니다.");
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}

}
