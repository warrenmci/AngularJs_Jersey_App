package com.salon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.salon.config.DBConfig;
import com.salon.entities.Person;

public class PersonDao {
	DBConfig db = new DBConfig();
	

	public Person find() {
		return new Person(01,"Candice", "McInnes", "Senior Stylist");
	}
	
	public List<Person> findAll() {
		Connection conn = db.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Person> people = new ArrayList<Person>();
		
		String sql = "SELECT * FROM staff";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				people.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 
		return people;
	}
	
	
	public Person findById(int id) {
		Connection conn = db.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Person person = null;
		
		String sql = "SELECT * FROM staff WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				person = new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 
		return person;
	}
	
	public List<Person> findByFirstName(String st) {
		String sql = "SELECT * FROM staff WHERE LOWER(firstname)=LOWER(?)";
		return findByText(st, sql);
	}
	
	public List<Person> findByLastName(String st) {
		String sql = "SELECT * FROM staff WHERE LOWER(lastname)=LOWER(?)";
		return findByText(st, sql);
	}
	
	public List<Person> findByPosition(String st) {
		String sql = "SELECT * FROM staff WHERE LOWER(position) LIKE LOWER(?)";
		return findByText("%" + st + "%", sql);
	}
	
	public List<Person> findByText(String st, String sql) {
		Connection conn = db.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Person> people = new ArrayList<Person>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, st);
			rs = ps.executeQuery();
			while(rs.next()) {
				people.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 
		return people;
	}
	
	public Person addStaff(Person person) {
		Connection conn = db.getDBConnection();
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO staff (firstName, lastName, position) VALUES (?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getPosition());
			ps.executeQuery();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 
		return person;
	}
	
	public Person updateStaff(Person person) {
		Connection conn = db.getDBConnection();
		PreparedStatement ps = null;
		
		String sql = "UPDATE staff SET firstName=?, lastName=?, position=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getPosition());
			ps.setInt(4, person.getId());
			ps.executeQuery();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} 
		return person;
	}
	
	public void removeStaff(int id) {
		Connection conn = db.getDBConnection();
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM staff WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
