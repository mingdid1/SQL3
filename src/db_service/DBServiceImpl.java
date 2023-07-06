package db_service;

import java.util.ArrayList;
import java.util.Scanner;

import db_dao.NewStDAO;
import db_dto.NewStDTO;

public class DBServiceImpl implements DBService{
	NewStDAO dao;
	
	public DBServiceImpl() {
		dao = new NewStDAO();
	}
	
	@Override
	public void display() {
		Scanner sc = new Scanner(System.in);
		String id, name;
		int age;
		int num;
		
		while(true) {
			System.out.println("1.모든 데이터 확인");
			System.out.println("2.검색 데이터 확인");
			System.out.println("3.데이터 추가");
			System.out.println("4.데이터 수정");
			System.out.println("5.데이터 삭제");
			System.out.println("6.종료");
			num = sc.nextInt();
			
			switch(num) {
			case 1:
				ArrayList<NewStDTO> list = getList();
				System.out.println("======= 회원 정보 =======");
				System.out.println("id\tname\tage");
				System.out.println("=======================");
				for (NewStDTO d : list) {
					System.out.print(d.getId() + "\t");
					System.out.print(d.getName() + "\t");
					System.out.println(d.getAge());
					System.out.println("=======================");
				}
				break;
			
			case 2: 
				System.out.println("검색 id 입력");
				id = sc.next();
				NewStDTO dto = search( id );
				if (dto == null) {
					System.out.println("존재하지 않는 회원입니다");
				}else {
					System.out.println("id : " + dto.getId());
					System.out.println("name : " + dto.getName());
					System.out.println("age : " + dto.getAge());
				}
				break;
			
			case 3:
				while(true){
					System.out.println("추가할 id 입력");
					id = sc.next();
					NewStDTO d = search(id);
					if (d == null) {
						break;
					}
					System.out.println("존재하는 id 입니다");
				}
				System.out.println("추가할 name 입력");
				name = sc.next();
				System.out.println("추가할 age 입력");
				age = sc.nextInt();

				int result = insert(id, name, age);
				if (result ==1) {
					System.out.println("회원가입 성공!!");
				}else {
					System.out.println("문제가 발생했습니다!!");
				}
				break;
			
			case 4:
				while(true){
					System.out.println("수정 할 id 입력");
					id = sc.next();
					NewStDTO d = search(id);
					if (d != null) {
						break;
					}
					System.out.println("존재하지 않는 id 입니다");
				}
				System.out.println("수정 할 name 입력");
				name = sc.next();
				System.out.println("수정 할 age 입력");
				age = sc.nextInt();
				
				NewStDTO d = new NewStDTO(id, name, age);
				
				int re = modify( d );
				if (re ==1) {
					System.out.println("수정 성공!!");
				}else {
					System.out.println("문제가 발생했습니다!!");
				}
				break;
			
			case 5:
				System.out.println("삭제 id 입력");
				id = sc.next();
				int re1 = delete(id);
				if (re1 == 1) {
					System.out.println("삭제 성공!!");
				}else {
					System.out.println("문제가 발생했습니다!!");
				}
				break;
			
			case 6: 
				return;
			}
		}
	}

	
	@Override
	public int modify(NewStDTO d) {
		return dao.modify(d);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	@Override
	public int insert(String id, String name, int age) {
		
		/*
			NewStDTO dto = new NewStDTO();
			dto.setId(id);
			dto.setName(name);
			dto.setAge(age);
		*/
		
		NewStDTO dto = new NewStDTO(id, name, age);
		return dao.insert(dto);
	}

	@Override
	public NewStDTO search(String id) {
		NewStDTO dto = dao.search(id);
		return dto;
	}
	
	@Override
	public ArrayList<NewStDTO> getList() {
		ArrayList<NewStDTO> list = dao.getList();
		return list;
	}

}
