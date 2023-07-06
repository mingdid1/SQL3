package db_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_common.DBConnect;
import db_dto.NewStDTO;

public class NewStDAO {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public NewStDAO() {
		con = DBConnect.getConnect();
	}
	
	public ArrayList<NewStDTO> getList() {
		
		ArrayList<NewStDTO> list = new ArrayList<>();
		String sql = "select * from newst";
		
		try {
			ps = con.prepareStatement( sql );
			rs = ps.executeQuery();
			while( rs.next() ) {
				
				NewStDTO dto = new NewStDTO();
				
				dto.setId( rs.getString("id"));
				dto.setName( rs.getString("name"));
				dto.setAge( rs.getInt("age"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public NewStDTO search(String id) {
		NewStDTO dto = null;
		
		// select * from newst where id = '아이디'
		String sql = "select * from newst where id = '" + id + "'";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// while(rs.next()) : 정보가 하나라서 while 대신 if 사용 가능
			if(rs.next()) { 
				dto = new NewStDTO( rs.getString("id"),
									rs.getString("name"),
									rs.getInt("age"));
				/*
				// 2.
				dto.setId( rs.getString("id"));
				dto.setName( rs.getString("name"));
				dto.setAge( rs.getInt("age"));
				
				// 1.
				System.out.println("id : " + rs.getString("id"));
				System.out.println("name : " + rs.getString("name"));
				System.out.println("age : " + rs.getInt("age"));
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public int insert(NewStDTO dto) {
		
		// insert into newst values('아이디','이름',나이);
		String sql = "insert into newst values(?, ?, ?)";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getAge());

			result = ps.executeUpdate();
			/*
				Query : select에서 Query를 사용한다
			 			결과값이 ResultSet
				Update : select를 제외한 모든 명령어에서 사용한다
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int delete(String id) {
		int result = 0;
		
		String sql = "delete from newst where id= ? ";
		// "delete from newst where id= '" +id+ "'";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int modify(NewStDTO d) {
		int result = 0;
		String sql = "update newst set name = ?, age= ? where id = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, d.getName());
			ps.setInt(2, d.getAge());
			ps.setString(3, d.getId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
