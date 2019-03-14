package com.saju.rest.DemoRest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlienRepository {

	Connection con = null;
	
	String url = "jdbc:mysql://localhost:3306/demorest";
	String user = "root";
	String password = "";
	
	public AlienRepository() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
	public List<Alien>getAliens(){
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
		
		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Alien alien = new Alien();
				alien.setId(rs.getInt(1));
				alien.setName(rs.getString(2));
				alien.setPoints(rs.getInt(3));
				aliens.add(alien);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return aliens;
	}
	public Alien getAlien(int id){
		String sql = "select * from alien where id="+id;
		try{
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				Alien alien = new Alien();
				alien.setId(rs.getInt(1));
				alien.setName(rs.getString(2));
				alien.setPoints(rs.getInt(3));
				return alien;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return null;
	}
	
	public Alien create(Alien alien){
		String sql = "insert into alien values(?,?,?)";
		
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, alien.getId());
			st.setString(2, alien.getName());
			st.setInt(3, alien.getPoints());
			st.executeUpdate();
			return getAlien(alien.getId());
		}
		catch(Exception e){
			System.out.println(e);
		}
//		System.out.println("create");
		return null;
	}
	
	public Alien update(Alien alien){
		String sql = "update alien set name=?,points=? where id=?";
		
		try{
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, alien.getName());
			st.setInt(2, alien.getPoints());
			st.setInt(3, alien.getId());
			st.executeUpdate();
			return getAlien(alien.getId());
		}
		catch(Exception e){
			System.out.println(e);
		}
//		System.out.println("create");
		return null;
	}

	public void delete(int id) {
        String sql = "delete from alien where id=?";
		
		try{
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			st.executeUpdate();
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
