package com.shenkar.db;

public class SQLQueryGenerator {
	/*
	 * run SQLQueryGenerator.main() to see how to use this functions (at the bottom of this class)
	 * */
	
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String NONE = "";
	public static final String DB = "ballroomot"; // the name of our DB, use only this constant to refer our DB
	
	public static String orderBy(String query, String[] attributes, String order) { //return null on failure
		query= (String) query.subSequence(0, query.length()-1);
		
		if(attributes.length <= 0) {
			return null;
		}
		
		if(order != ASC && order != DESC && order !="") {
			return null;
		}
		
		query+=" ORDER BY ";
		
		for(int i=0; i<attributes.length; ++i) {
			if(i>0)
				query+=",";
			query+= attributes[i];
		}
		
		return query+" "+order+";";
	}
	
	public static String createTable(String name, String[] atts, String[] types, String[] length,String key) {
		String query = "CREATE TABLE " + DB + "." + name + " (";
		int i;
		
		for(i=0; i<atts.length; ++i) {
			if(i>0)
				query += ",";
			
			query += atts[i] + " " + types[i];
			
			if(types[i] == "varchar")
				query += "(" + length[i] + ")";
		}
		query += ", primary key" + "(" + key + ")";
		return query+");";
	}
	
	public static String insertInto(String name, String[] atts, String[] values) {
		String query = "INSERT INTO " + DB + "." + name + " (";
		int i;
		
		if(atts.length != values.length) {
			return null;
		}
		
		for(i=0; i<atts.length; i++) {
			if(i>0)
				query += ",";
			query += atts[i];
		}
		
		query += ") VALUES (";
		
		for(i=0; i<values.length; i++) {
			if(i>0)
				query += ",";
			query +=values[i];
		}
		
		return query + ");";
	}
	
	public static String select(String[] select, String[] from, String[] where) { //return null on failure
		String query = "SELECT ";
		int i;
		
		if(select.length <= 0) {		//SELECT
			return null;
		}
		
		for(i=0; i<select.length; ++i) { 
			if(i>0)
				query+=",";
			query+= select[i];
		}
		
		if(from.length > 0) {			//FROM
			query+=" FROM ";
		} else {
			return null;
		}
		
		for(i=0; i<from.length; ++i) {
			if(i>0)
				query+=",";
			query+= DB + "." +from[i];
		}
		
		if(where.length > 0) {			//WHERE - column_name operator value
			query+=" WHERE ";
			query+=where[0]+where[1]+where[2];
		}
	
		return query+";";
	}
	
	public static String delete(String[] from, String[] where) { //return null on failure
		String query = "DELETE FROM ";
		int i;
		
		if(from.length <= 0) {
			return null;
		}
		
		for(i=0; i<from.length; ++i) { //DELETE FROM
			if(i>0)
				query+=",";
			query+= DB + "." +from[i];
		}
		
		if(where.length > 0) {			//WHERE - column_name operator value
			query+=" WHERE ";
			query+=where[0]+where[1]+where[2];
		}
		
		return query+";";
	}
	
	public static String update(String update, String[] set, String[] where) { //return null on failure
		String query = "UPDATE ";
		int i;
		
		if(update.length() <= 0) {
			return null;
		}
		
		query+= DB + "." +update;
		
		if(set.length > 0) {			//SET column_name operator value
			query+=" SET ";
		} else {
			return null;
		}
		
		for(i=0; i<set.length; i+=3) {
			if(i>0)
				query+=",";
			query+=set[i]+set[i+1]+set[i+2];
		}
		
		if(where.length > 0) {			//WHERE - column_name operator value
			query+=" WHERE ";
			query+=where[0]+where[1]+where[2];
		}
	
		return query+";";
	}
	
	
	public static String max(String column, String from) { //return null on failure
		String query = "SELECT MAX";
		
		if(column.length() <= 0 || from.length() <= 0) {
			return null;
		}
		
		query+="("+column+")"+" FROM "+from;
		
		return query+";";
	}
	
	public static String min(String column, String from) { //return null on failure
		String query = "SELECT MIN";
		
		if(column.length() <= 0 || from.length() <= 0) {
			return null;
		}
		
		query+="("+column+")"+" FROM "+from;
		
		return query+";";
	}

	public static String sum(String column, String from) { //return null on failure
		String query = "SELECT SUM";
		
		if(column.length() <= 0 || from.length() <= 0) {
			return null;
		}
		
		query+="("+column+")"+" FROM "+from;
		
		return query+";";
	}
	
	public static String avg(String column, String from) { //return null on failure
		String query = "SELECT AVG";
		
		if(column.length() <= 0 || from.length() <= 0) {
			return null;
		}
		
		query+="("+column+")"+" FROM "+from;
		
		return query+";";
	}
	
	public static String count(String column, String from) { //return null on failure
		String query = "SELECT COUNT";
		
		if(column.length() <= 0 || from.length() <= 0) {
			return null;
		}
		
		query+="("+column+")"+" FROM "+from;
		
		return query+";";
	}

	
	//test
	
	public static void main(String[] args){
		String[] select = {"a","b","c"};
		String[] from = {"d","e","f"};
		String[] where= {"g","=","1"};
		String[] attributes = {"u","v"};
		String query = SQLQueryGenerator.select(select,from,where);
		System.out.println(query);
		query = SQLQueryGenerator.delete(from,where);
		System.out.println(query);
		query = SQLQueryGenerator.update("amsterdam",from,where);
		query = SQLQueryGenerator.orderBy(query, attributes, SQLQueryGenerator.ASC);
		System.out.println(query);
		String name = "testTable";
		String[] atts = {"a","b","c","d","e"};
		String[] types = {"varchar","varchar","int","int","varchar"};
		String[] length = {"30","20","100","200","50"};
		String key = "a";
		query = SQLQueryGenerator.createTable(name, atts, types, length,key);
		System.out.println(query);
		String[] values = {"30","20","100","200","50"};
		query = SQLQueryGenerator.insertInto(name, atts, values);
		System.out.println(query);
	}
	
	
}

