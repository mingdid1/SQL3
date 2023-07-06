package db_common;

import java.util.Scanner;

import a_people.Apeople;
import db_service.DBService;
import db_service.DBServiceImpl;

/*
	common : 공통적으로 사용하는 파일
	service : 연산 담당
	dao : DB접근 담당
	dto : 데이터 이동하는 역할
	main : 기본 틀 (컨트롤러)
			controller - 사용자 요청을 받고 표현하는 공간


 */
public class MainClass {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int num;
		
		DBService service = new DBServiceImpl();
		Apeople a = new Apeople();
		
		while (true) {
			System.out.println("1.new St 이동");
			System.out.println("2.a Pe 이동");
			System.out.println("3. 종료");
			num = sc.nextInt();
			
			switch( num ) {
			case 1 : service.display(); break;
			case 2 : a.display(); break;
			case 3 :
				return;
			}
		}
	}
}
