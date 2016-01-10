package com.shenkar.db;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Main {
	
	public static void main(String[] args) {
		Connection con;
		try {
			con = getConnection(); // connects to DB server
			createDB(con);			//creats DB on server
			@SuppressWarnings("unused")
			Init init = new Init(con); // creats tables and content in DB
			
			GUI gui = new GUI();	// run GUI
			Thread t = new Thread(gui);
			t.start();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static Connection getConnection() throws Exception { // connects to DB server
		try {
			
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/"; // + SQLQueryGenerator.DB;
			
			String username = "root";
			
			
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,username,"");
			System.out.println("connected to phpmyadmin server");
			return con;
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	public static void createDB(Connection con) throws Exception {
		try {
			PreparedStatement statement = con.prepareStatement("CREATE DATABASE " + SQLQueryGenerator.DB);
			statement.executeUpdate();
			System.out.println(SQLQueryGenerator.DB + " created");
		} catch (Exception e) {System.out.println(e);}
	}
	
   
	
	
	/*  //how to 'select' from phpmyadmin from java
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ballroomot","root","root");
	PreparedStatement statement = con.prepareStatement("select * from names");
	ResultSet result = statement.executeQuery();

	while(result.next()) {
		System.out.println(result.getString(1)+" "+result.getString(2));
	}
	*/


}
