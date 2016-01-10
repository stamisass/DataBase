package com.shenkar.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Init {
	private Connection con = null;
	private PreparedStatement statement = null;
	private Table[] tables;
	private Data[] data;
	
	
	/*
	 * TODO:
	 * Keys
	 * להוסיף עמודות ומידע לטבלאות כפי שכתוב שצריך בדף הדרישות, לא הכל נכתב פה לפי הדרישות
	 * 
	 * 
	 * */
	
	
	public Init(Connection con) throws Exception{
		tables = new Table[15];
		data = new Data[15];
		this.con = con;
		if(con==null) {
			System.out.println("Connection failed. Please make sure you created DB named '" 
					+ SQLQueryGenerator.DB + "' at your local phpmyadmin server");
		}
		try {
			System.out.println("setTables");	setTables(); 	// make the tables
			System.out.println("creatTables");	createTables(); // load tables to DB
		} catch (Exception e) {System.out.println(e);System.out.println("!!!");}
		try {
			System.out.println("setData");		setData(); 		// make data
			System.out.println("creatData"); 	createData();  	// load data to DB
		} catch (Exception e) {System.out.println(e);}
		//TODO set keys											//  Keys yet to be set
		System.out.println("init completed");
	}
	
	private void createData() throws SQLException {
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[i].vals.length; j++) {
				statement = con.prepareStatement
						(SQLQueryGenerator.insertInto(tables[i].name, tables[i].atts, data[i].vals[j]));
				statement.executeUpdate();
			}
			System.out.println("created " + tables[i].name + " number " + i);
		}
		
	}
	
	private void createTables() throws SQLException {
		for(int i=0; i<tables.length; i++) {
			statement = con.prepareStatement
					(SQLQueryGenerator.createTable(tables[i].name, tables[i].atts, tables[i].types, tables[i].length,tables[i].key));
			statement.executeUpdate();
			System.out.println("created " + tables[i].name + " number " + i);
		}
	}

	private void setData() throws Exception { // the actual data to be load later to DB
		int i = 0;
		
		/*
		String name = "clients";
		String[] atts = new String[]{"id","fname","lname","phone_no","client_no"};
		String[] types = new String[]{"varchar","varchar","varchar","varchar","int"};
		String[] length = new String[]{"5","20","20","10","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		
		
		String[][] vals = new String[][]{ 
				{"0001","'Amit'","'Aharonson'","0311111","1000"},
				{"0002","'Josh'","'Kablani'","0322222","2000"},
				{"0003","'Chick'","'Korea'","0333333","3000"},
				{"0004","'Yediot'","'Aharonot'","0344444","4000"},
				{"0005","'Fith'","'Element'","0355555","5000"},
				{"0006","'Sense'","'Motion'","0366666","6000"},
				{"0007","'Abra'","'Kadabra'","0377777","7000"},
				{"0008","'Rotem'","'Abohab'","0388888","8000"},
				{"0009","'Schnezel'","'Kebab'","0399999","9000"},
				{"0010","'Alma'","'Katz'","0312121","9191"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "reports";
		atts = new String[]{"report_no","date_of_creation","last_day_to_pay","amount"};
		types = new String[]{"int","date","date","int"};
		length = new String[]{"-1","-1","-1","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"65611","'2010-08-11'","'2017-08-24'","1567450"},
				{"45652","'2011-07-24'","'2018-09-06'","765651"},
				{"79353","'2012-06-30'","'2016-07-09'","786852"},
				{"67814","'2013-05-29'","'2019-05-30'","47663"},
				{"30615","'2014-04-11'","'2020-03-30'","5420764"},
				{"96406","'2015-12-18'","'2017-01-22'","307415"},
				{"30217","'2013-01-26'","'2016-02-17'","786456"},
				{"97638","'2017-01-04'","'2018-04-03'","79547"},
				{"03499","'2010-11-09'","'2012-05-14'","6544278"},
				{"79030","'2010-03-01'","'2011-06-18'","6731860"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "events";
		atts = new String[]{"name","code","type"};
		types = new String[]{"varchar","int","varchar"};
		length = new String[]{"20","-1","20"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"'Efraims birhday'","208110","'birhday'"},
				{"'Ben and sholamit wedding'","107241","'wedding'"},
				{"'CS convention'","206302","'convention'"},
				{"'2016 Class reunion'","305293","'reunion'"},
				{"'Meirs brith'","404114","'brith'"},
				{"'Alon bar mizva'","512185","'bar mizva'"},
				{"'Yael bat mizva'","301266","'bat mizva'"},
				{"'Harry Potter premier'","701047","'premier'"},
				{"'Gilad birthday'","111098","'birthday'"},
				{"'Aaron and michal wedding'","603019","'wedding'"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "rooms";
		atts = new String[]{"capacity","code","name"};
		types = new String[]{"int","int","varchar"};
		length = new String[]{"-1","-1","20"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"1000","011","'Saphire'"},
				{"2000","022","'Ember'"},
				{"3000","033","'Diemond'"},
				{"4000","044","'Opal'"},
				{"5000","055","'Granite'"},
				{"6000","066","'Zink'"},
				{"7000","077","'Corcar'"},
				{"8000","088","'Leaf'"},
				{"9000","099","'Orange'"},
				{"10000","100","'Tango'"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		
		/*
		name = "orders";
		atts = new String[]{"order_no","date","no_of_guests"};
		types = new String[]{"int","date","int"};
		length = new String[]{"-1","-1","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"94411","'2017-08-24'","5500"},
				{"85552","'2018-09-06'","6600"},
				{"76653","'2016-07-09'","7700"},
				{"67714","'2019-05-30'","1000"},
				{"58815","'2020-03-30'","2200"},
				{"49906","'2017-01-22'","3000"},
				{"31117","'2016-02-17'","500"},
				{"22238","'2018-04-03'","1234"},
				{"13399","'2012-05-14'","4567"},
				{"10030","'2011-06-18'","7891"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "jobs";
		atts = new String[]{"code","description"};
		types = new String[]{"int","varchar"};
		length = new String[]{"-1","255"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"10","'waiter'"},
				{"20","'chef'"},
				{"30","'dish washer'"},
				{"40","'clenning'"},
				{"50","'manager'"},
				{"60","'owner'"},
				{"70","'kitchen worker'"},
				{"80","'guard'"},
				{"90","'gardner'"},
				{"100","'firetechnition'"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "employees";
		atts = new String[]{"id","fname","lname","phone_no","address","sex","birth_date","first_day_date","job"};
		types = new String[]{"varchar","varchar","varchar","varchar","varchar","varchar","date","date","varchar"};
		length = new String[]{"5","20","20","10","255","10","-1","-1","30"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		
		vals = new String[][]{
				{"1111","'Shimi'","'son'","0322211","'hedera, ben zion 7'","'man'","'1990-08-24'","'2007-04-30'","'waiter'"},
				{"2222","'Benjamin'","'lani'","0333322","'rishon lezion, tel goma 60'","'man'","'1991-03-04'","'2009-07-07'","'waiter'"},
				{"3333","'Lechem'","'arad'","0344433","'haifa, ehad haam 3'","'woman'","'1982-08-04'","'2006-01-04'","'chef'"},
				{"4444","'Roman'","'Kazablan'","0355544","'tel aviv, qibuz gezer 6'","'man'","'1989-06-22'","'2010-03-30'","'firetechnition'"},
				{"5555","'Ahalan'","'Sahalan'","0366655","'holon, main 123'","'woman'","'1990-04-05'","'2010-05-29'","'kitchen worker'"},
				{"6666","'Kid'","'Rock'","0377766","'jerusalem, barbara streisend 100'","'man'","'1994-01-06'","'2011-10-02'","'clenning'"},
				{"7777","'Jimmi'","'feet'","0388877","'ramat hasharon, bli manoach 4'","'man'","'1964-03-17'","'1995-04-10'","'owner'"},
				{"8888","'Dog'","'cat'","0399988","'afola, atlozorov 56'","'woman'","'1980-12-05'","'1995-04-10'","'manager'"},
				{"9999","'plant'","'river'","0310199","'eilat, dolphine 2'","'woman'","'1985-05-13'","'2012-12-21'","'gardner'"},
				{"1010","'under'","'pants'","0398721","'nesher, eagle 8'","'man'","'1990-02-23'","'2013-05-06'","'clenning'"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "menus";
		atts = new String[]{"name","menu_no"};
		types = new String[]{"varchar","int"};
		length = new String[]{"20","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"'one'","1"},
				{"'two'","2"},
				{"'three'","3"},
				{"'four'","4"},
				{"'five'","5"},
				{"'six'","6"},
				{"'seven'","7"},
				{"'eight'","8"},
				{"'nine'","9"},
				{"'ten'","10"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "meals";
		atts = new String[]{"code","name","price"};
		types = new String[]{"int","varchar","int"};
		length = new String[]{"-1","20","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		vals = new String[][]{
				{"101","'fish'","50"},
				{"202","'chiken'","60"},
				{"303","'beef'","70"},
				{"404","'salad'","30"},
				{"505","'cake'","55"},
				{"606","'cheese'","40"},
				{"707","'soda'","10"},
				{"808","'water'","8"},
				{"909","'wine'","100"},
				{"111","'vodka'","45"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "orders_events";
		atts = new String[]{"order_no","event_code","amount"};
		types = new String[]{"int","int","int"};
		length = new String[]{"-1","-1","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 	
		*/
		
		vals = new String[][]{
				{"94411","208110","0"},
				{"85552","107241","0"},
				{"76653","206302","0"},
				{"67714","305293","0"},
				{"58815","404114","0"},
				{"49906","512185","0"},
				{"31117","301266","0"},
				{"22238","701047","0"},
				{"13399","111098","0"},
				{"10030","603019","0"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "orders_clients";
		atts = new String[]{"order_no","client_no"};
		types = new String[]{"int","int"};
		length = new String[]{"-1","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		//////////////////////////////////////////////////////////////////////////////
		vals = new String[][]{
				{"94411","1000"},
				{"85552","2000"},
				{"76653","3000"},
				{"67714","4000"},
				{"58815","5000"},
				{"49906","6000"},
				{"31117","7000"},
				{"22238","8000"},
				{"13399","9000"},
				{"10030","9191"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		 name = "rooms_events";
		atts = new String[]{"room_code","event_code"};
		types = new String[]{"int","int"};
		length = new String[]{"-1","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		
		vals = new String[][]{
				{"011","208110"},
				{"022","107241"},
				{"033","206302"},
				{"044","305293"},
				{"055","404114"},
				{"066","512185"},
				{"077","301266"},
				{"088","701047"},
				{"099","111098"},
				{"100","603019"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
		
		/*
		name = "orders_employees";
		atts = new String[]{"order_no","employee_id"};
		types = new String[]{"int","int"};
		length = new String[]{"-1","-1"};
		tables[i] = new Table (name, atts, types, length);
		System.out.println("set "+ name + " number " + i);
		++i; 
		*/
		
		vals = new String[][]{
				{"94411","1111"},
				{"85552","2222"},
				{"76653","3333"},
				{"67714","4444"},
				{"58815","5555"},
				{"49906","6666"},
				{"31117","7777"},
				{"22238","8888"},
				{"13399","9999"},
				{"10030","1010"},

				{"94411","2222"},
				{"85552","3333"},
				{"76653","4444"},
				{"67714","5555"},
				{"58815","6666"},
				{"49906","7777"},
				{"31117","8888"},
				{"22238","9999"},
				{"13399","1010"},
				{"10030","1111"},
				
				{"94411","3333"},
				{"85552","4444"},
				{"76653","5555"},
				{"67714","6666"},
				{"58815","7777"},
				{"49906","8888"},
				{"31117","9999"},
				{"22238","1010"},
				{"13399","1111"},
				{"10030","2222"}
				};
		data[i] = new Data(vals);
		System.out.println("set " + i);
		i++;
	}

	private void setTables() { // schemes
		int i = 0;
		
		String name = "clients";
		String[] atts = new String[]{"id","fname","lname","phone_no","client_no"};
		String[] types = new String[]{"varchar","varchar","varchar","varchar","int"};
		String[] length = new String[]{"5","20","20","10","-1"};
		tables[i] = new Table (name, atts, types, length,"id");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "reports";
		atts = new String[]{"report_no","date_of_creation","last_day_to_pay","amount"};
		types = new String[]{"int","date","date","int"};
		length = new String[]{"-1","-1","-1","-1"};
		tables[i] = new Table (name, atts, types, length,"report_no");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "events";
		atts = new String[]{"name","code","type"};
		types = new String[]{"varchar","int","varchar"};
		length = new String[]{"50","-1","20"};
		tables[i] = new Table (name, atts, types, length,"code");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "rooms";
		atts = new String[]{"capacity","code","name"};
		types = new String[]{"int","int","varchar"};
		length = new String[]{"-1","-1","20"};
		tables[i] = new Table (name, atts, types, length,"code");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "orders";
		atts = new String[]{"order_no","date","no_of_guests"};
		types = new String[]{"int","date","int"};
		length = new String[]{"-1","-1","-1"};
		tables[i] = new Table (name, atts, types, length,"order_no");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "jobs";
		atts = new String[]{"code","description"};
		types = new String[]{"int","varchar"};
		length = new String[]{"-1","255"};
		tables[i] = new Table (name, atts, types, length,"code");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "employees";
		atts = new String[]{"id","fname","lname","phone_no","address","sex","birth_date","first_day_date","job"};
		types = new String[]{"varchar","varchar","varchar","varchar","varchar","varchar","date","date","varchar"};
		length = new String[]{"5","20","20","10","255","10","-1","-1","30"};
		tables[i] = new Table (name, atts, types, length,"id");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "menus";
		atts = new String[]{"name","menu_no"};
		types = new String[]{"varchar","int"};
		length = new String[]{"20","-1"};
		tables[i] = new Table (name, atts, types, length,"menu_no");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "meals";
		atts = new String[]{"code","name","price"};
		types = new String[]{"int","varchar","int"};
		length = new String[]{"-1","20","-1"};
		tables[i] = new Table (name, atts, types, length,"code");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "orders_events";
		atts = new String[]{"order_no","event_code","amount"};
		types = new String[]{"int","int","int"};
		length = new String[]{"-1","-1","-1"};
		tables[i] = new Table (name, atts, types, length,"order_no,event_code");
		System.out.println("set "+ name + " number " + i);
		++i; 	
		
		name = "orders_clients";
		atts = new String[]{"order_no","client_no"};
		types = new String[]{"int","int"};
		length = new String[]{"-1","-1"};
		tables[i] = new Table (name, atts, types, length,"order_no,client_no");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "rooms_events";
		atts = new String[]{"room_code","event_code"};
		types = new String[]{"int","int"};
		length = new String[]{"-1","-1"};
		tables[i] = new Table (name, atts, types, length,"room_code,event_code");
		System.out.println("set "+ name + " number " + i);
		++i; 
		
		name = "orders_employees";
		atts = new String[]{"order_no","employee_id"};
		types = new String[]{"int","int"};
		length = new String[]{"-1","-1"};
		tables[i] = new Table (name, atts, types, length,"order_no,employee_id");
		System.out.println("set "+ name + " number " + i);
		++i; 
	}
}
