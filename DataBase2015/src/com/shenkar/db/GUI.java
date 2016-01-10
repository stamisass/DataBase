package com.shenkar.db;
import java.util.Arrays;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI implements Runnable{
	/*
	 * TODO
	 * main, main1, main2 are gui experiments
	 * 
	 * */
	
	public static void main(String[] args) {
		try {
			GUI gui = new GUI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	
	
	/*
	public static void main2(String[] args) throws Exception {
		mainFrame = new JFrame("Events manager interface");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// add components
		executeButton = new JButton("execute query"); // set action to button
		executeButton.setSize(new Dimension(50, 50));
		tablesComboBox = new JComboBox<String>(new String[]{
				"clients",
				"employees",
				"events",
				"jobs",
				"meals",
				"menus",
				"orders",
				"orders_of_events",
				"reports",
				"rooms"}); // set action to combo box
		tablesComboBox.setSize(new Dimension(100, 40));
		
		
		mainFrame.add(tablesComboBox);
		mainFrame.add(executeButton);
		//
		mainFrame.pack();
		mainFrame.setSize(new Dimension(1000, 800));
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		
		mainFrame.setLayout(new GridLayout(5,2));
		mainFrame.setVisible(true);
	}
	*/
	
	/* table 
	public static void main(String[] args) throws Exception {
		from = new String[]{"employees"};
		where = new String[]{};
		name = from[0];
		select = describe();
			//atts = select;
			//values = new String[]{"'omer cofman'","'00000'","'eeeee'"};
			//insertInto();
		buildTable();
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		String frameName = "";
		for(int i=0; i<from.length; i++) {
			frameName += from[i];
			frameName += " ";
		}
		tableFrame = new JFrame(frameName);
		tableFrame.add(scrollPane);
		tableFrame.setSize(1000, 400);
		tableFrame.setVisible(true);
	}
	*/
	
	private void drowTable() throws Exception {
		buildTable();
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		String frameName = "";
		for(int i=0; i<from.length; i++) {
			frameName += from[i];
			frameName += " ";
		}
		tableFrame = new JFrame(frameName);
		tableFrame.add(scrollPane);
		tableFrame.setSize(1000, 400);
		tableFrame.setVisible(true);
	}
	
	public GUI(){
		run();
		globalCode = 0;
	}

	
	@Override	
	public void run() {
		try {
			f = new JFrame("Ballroom Management Tool");
			f.setLayout(new FlowLayout());
			
			//one
			olHead	=	new JLabel("Show information about employees");
			olEvent = 	new JLabel("by event: ");
			olRoom 	=	new JLabel("by room: ");
			olEmp	=	new JLabel("by employee: ");
				// ocbEvents
				select = new String[]{"name"};
				from = new String[]{"events"};
				where = new String[]{};
				String[][] result2 = select();
				String[] sevents = new String[16];
				sevents[0] = "none";
				for(int i=0; i<result2.length; i++) {
					sevents[i+1] = result2[i][0];
				}
				// ocbRooms
				select = new String[]{"name"};
				from = new String[]{"rooms"};
				where = new String[]{};
				String[][] result = select();
				String[] ssrooms = new String[16];
				ssrooms[0] = "none";
				for(int i=0; i<result.length; i++) {
					ssrooms[i+1] = result[i][0];
				}
				// ocbRooms
				select = new String[]{"fname"};
				from = new String[]{"employees"};
				where = new String[]{};
				String[][] result1 = select();
				String[] semp = new String[16];
				semp[0] = "none";
				for(int i=0; i<result1.length; i++) {
					semp[i+1] = result1[i][0];
				}
			JComboBox ocbEvents	=	new JComboBox(sevents);
			JComboBox ocbRooms	=	new JComboBox(ssrooms);
			JComboBox ocbEmp	=	new JComboBox(semp);
			obExe	= 	new JButton("execute");
			obExe.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String input = new String();
						input = String.valueOf(ocbEvents.getSelectedItem());
						if(input != "none" && input != "null") {
							select 	= new String[]{"code"};
							from 	= new String[]{"events"};
							where	= new String[]{"name","=","'"+input+"'"};
							String[][] returned = select();
							String extract = returned[0][0]; System.out.println(extract);
							select 	= new String[]{"order_no"};
							from 	= new String[]{"orders_events"};
							where	= new String[]{"event_code","=",extract};
							returned = select();
							extract = returned[0][0]; System.out.println(extract);
							select 	= new String[]{"employee_id"};
							from 	= new String[]{"orders_employees"};
							where	= new String[]{"order_no","=",extract};
							returned = select();
							String[] empArr = new String[returned.length]; System.out.println("befor for");
							for(int itr=0; itr < returned.length; itr++) {
								empArr[itr] = returned[itr][0]; System.out.println(empArr[itr]); System.out.println(itr);
							}
							System.out.println("befor second for");
							for(int itr=0; itr < empArr.length; itr++) {
								if(empArr[itr] == null) continue;
								from = new String[]{"employees"}; System.out.println(1);
								where = new String[3]; System.out.println(2);
								where[2] = empArr[itr]; System.out.println(3);
								where[0] = "id";
								where[1] = "=";
								name = "employees"; 
								select = describe(); System.out.println(4);
								drowTable(); System.out.println(5);
								System.out.println(empArr[itr]);
							}
						}
						
						input = String.valueOf(ocbRooms.getSelectedItem());
						if(input != "none" && input != "null") {
							select 	= new String[]{"code"};
							from 	= new String[]{"rooms"};
							where	= new String[]{"name","=","'"+input+"'"};
							String[][] returned = select();
							String extract = returned[0][0]; System.out.println(extract);
							select 	= new String[]{"event_code"};
							from 	= new String[]{"rooms_events"};
							where	= new String[]{"room_code","=",extract};
							returned = select();
							extract = returned[0][0]; System.out.println(extract);
							select 	= new String[]{"order_no"};
							from 	= new String[]{"orders_events"};
							where	= new String[]{"event_code","=",extract};
							returned = select();
							extract = returned[0][0]; System.out.println(extract);
							select 	= new String[]{"employee_id"};
							from 	= new String[]{"orders_employees"};
							where	= new String[]{"order_no","=",extract};
							returned = select();
							String[] empArr = new String[returned.length]; System.out.println("befor for");
							for(int itr=0; itr < returned.length; itr++) {
								empArr[itr] = returned[itr][0]; System.out.println(empArr[itr]); System.out.println(itr);
							}
							System.out.println("befor second for");
							for(int itr=0; itr < empArr.length; itr++) {
								if(empArr[itr] == null) continue;
								from = new String[]{"employees"}; System.out.println(1);
								where = new String[3]; System.out.println(2);
								where[2] = empArr[itr]; System.out.println(3);
								where[0] = "id";
								where[1] = "=";
								name = "employees"; 
								select = describe(); System.out.println(4);
								drowTable(); System.out.println(5);
								System.out.println(empArr[itr]);
							}
						}
						
						input = String.valueOf(ocbEmp.getSelectedItem());
						if(input != "none" && input != "null") {
							from = new String[]{"employees"};
							where = new String[]{"fname","=","'"+input+"'"};
							name = "employees";
							select = describe();
							drowTable();
						}
					}catch(Exception e1) {System.out.println(e1);}
				}
			});
			one = new JPanel();
			one.add(olHead);
			one.add(olEvent);
			one.add(ocbEvents);
			one.add(olRoom);
			one.add(ocbRooms);
			one.add(olEmp);
			one.add(ocbEmp);
			one.add(obExe);
			f.add(one);
			
			//two
			tlChangeEvent 	= new JLabel("Change the number of participants at event: ");
			JLabel tlUpdateNo = new JLabel("New value: ");
			JTextField ttfNewVal = new JTextField(10);
			tlChangeMeal 	= new JLabel("Change information about a meal: ");
			JLabel tlUpdateName = new JLabel("Update name: ");
			JTextField ttfUpdateName = new JTextField(10);
			JLabel tlUpdatePrice = new JLabel("Update price: ");
			JTextField ttfUpdatePrice = new JTextField(10);
			JLabel deleteMeal = new JLabel("Delete meal: ");
			
			// tcbEvents
			select = new String[]{"name"};
			from = new String[]{"events"};
			where = new String[]{};
			String[][] result4 = select();
			String[] sevents4 = new String[16];
			sevents4[0] = "none";
			for(int i=0; i<result4.length; i++) {
				sevents4[i+1] = result4[i][0];
			}
			// tcbMeals
			select = new String[]{"name"};
			from = new String[]{"meals"};
			where = new String[]{};
			String[][] result5 = select();
			String[] smeals = new String[16];
			smeals[0] = "none";
			for(int i=0; i<result5.length; i++) {
				smeals[i+1] = result5[i][0];
			}
			JComboBox tcbEvents = new JComboBox(sevents4);
			JComboBox tcbMeals = new JComboBox(smeals);
			JComboBox tcbMeals2 = new JComboBox(smeals);
			tbExe = new JButton("execute");
			tbExe.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String input = new String();
						input = String.valueOf(tcbEvents.getSelectedItem()); System.out.println(input);
						if(input != "null" && input != "none") {
							String newVal = ttfNewVal.getText(); System.out.println(newVal);
							try {
								Integer.parseInt(newVal);
							} catch(Exception e2) {System.out.println(e2);}
							select 	= new String[]{"code"};
							from 	= new String[]{"events"};
							where	= new String[]{"name","=","'"+input+"'"};
							String[][] returned = select();
							String extract = returned[0][0]; System.out.println(extract);
							select 	= new String[]{"order_no"};
							from 	= new String[]{"orders_events"};
							where	= new String[]{"event_code","=",extract};
							returned = select();
							extract = returned[0][0]; System.out.println(extract);
							updateupdate = new String("orders");
							set = new String[]{"no_of_guests","=",newVal};
							where = new String[]{"order_no","=",extract};
							update();
						}
						
						input = String.valueOf(tcbMeals.getSelectedItem()); System.out.println(input);
						if(input != "null" && input != "none") {
							String newVal = ttfUpdatePrice.getText(); System.out.println(newVal);
							try {
								Integer.parseInt(newVal);
							} catch(Exception e2) {System.out.println(e2);}
							updateupdate = new String("meals");
							set = new String[]{"price","=",newVal};
							where = new String[]{"name","=","'"+input+"'"}; System.out.println(1);
							update();
							newVal = ttfUpdateName.getText(); System.out.println(newVal);
							updateupdate = new String("meals");
							set = new String[]{"name","=","'"+newVal+"'"};
							where = new String[]{"name","=","'"+input+"'"}; System.out.println(2);
							update();
						}
						
						input = String.valueOf(tcbMeals2.getSelectedItem()); System.out.println(input);
						if(input != "null" && input != "none") {
							from = new String[]{"meals"};
							where = new String[]{"name","=","'"+input+"'"};
							delete();
						}
						
					} catch(Exception e3) {System.out.println(e3);}
				}
			});
			
			two = new JPanel();
			two.add(tlChangeEvent);
			two.add(tcbEvents);
			two.add(tlUpdateNo);
			two.add(ttfNewVal);
			two.add(tlChangeMeal);
			two.add(tcbMeals);
			two.add(tlUpdateName);
			two.add(ttfUpdateName);
			two.add(tlUpdatePrice);
			two.add(ttfUpdatePrice);
			two.add(deleteMeal);
			two.add(tcbMeals2);
			two.add(tbExe);
			f.add(two);

			//three
			JLabel EventBook = new JLabel ("Book an Event:");
			JLabel EventName = new JLabel ("Event Name:");
			JLabel EvenType	=	new JLabel("Event Type:");
			JLabel EventDate = 	new JLabel("Event Date: ");
			JLabel NumOfP 	=	new JLabel("Number of Guests: ");
			JLabel EventRoom	=	new JLabel("Event Room: ");
			
			TextField ename = new TextField(10);
			TextField etype = new TextField(10);
			TextField edate = new TextField(10);
			TextField eguests = new TextField(10);
			
			select = new String[]{"name"};
			from = new String[]{"rooms"};
			where = new String[]{};
			String[][] result11 = select();
			String[] srooms = new String[16];
			srooms[0] = "none";
			for(int i=0; i<result11.length; i++) {
				srooms[i+1] = result11[i][0];
			}
			JComboBox eroom = new JComboBox(srooms);
			
			JButton EvExe	= 	new JButton("execute");
			EvExe.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					
					
					String eename = new String();
					eename = String.valueOf(ename.getText());
					
					String eetype = new String();
					eetype = String.valueOf(etype.getText());
					
					String eedate = new String();
					eedate = String.valueOf(edate.getText());
					
					String eeguests = new String();
					eeguests = String.valueOf(eguests.getText());
					
					String eeroom = new String();
					eeroom = String.valueOf(eroom.getSelectedItem());
					
					
				
					
					
					select = new String[] {"order_no"};
					from = new String[] {"orders"};
					where = new String[] {"date","=" ,"'"+eedate+"'"};
					
					String[][] returned3 = select();
					String extract3 = returned3[0][0]; System.out.println(extract3);
		
					
						if(extract3 != null){
							
							select = new String[] {"event_code"};
							from = new String[] {"orders_events"};
							where = new String[] {"order_no", "=", extract3};
							returned3 = select();
							extract3 = returned3[0][0]; System.out.println(extract3);
							
							select = new String[] {"room_code"};
							from =  new String[] {"rooms_events"};
							where = new String[] {"event_code" , "=" , extract3};
							
							returned3 = select();
							extract3 = returned3[0][0]; System.out.println(extract3);
							String room_code = extract3;
							
							select = new String[] {"name"};
							from = new String[] {"rooms"};
							where = new String[] {"code" ,"=" , extract3};
							returned3 = select();
							extract3 = returned3[0][0]; System.out.println(extract3);
							
							 if(extract3 != eeroom){
								 int order_no = globalCode;globalCode++;
								 int event_code = 0;globalCode++;
								  // TODO order number and event_code generator
								 
								 name = "orders";
								 atts = new String[]{"order_no","date","no_of_guests"};
								 values = new String[]{"'"+String.valueOf(order_no)+"'" , "'"+eedate+"'"  , "'"+eeguests+"'"}; 
								 
								 insertInto();
								 System.out.println("orders table updated:");
								 System.out.println(Arrays.toString(values));
								 
								 name = "orders_events";
								 atts = new String[]{"order_no","event_code","amount"};
								 values = new String[]{"'"+String.valueOf(order_no)+"'" ,"'"+String.valueOf(event_code) +"'" , "0"}; 
								 insertInto();
								 System.out.println("orders_events table updated:");
								 System.out.println(Arrays.toString(values));
								 
								 name = "rooms_events";
								 atts = new String[]{"room_code","event_code"};
								 values = new String[]{"'"+room_code+"'" ,"'"+String.valueOf(event_code)+"'"}; 
								 insertInto();
								 System.out.println("rooms_events table updated:");
								 System.out.println(Arrays.toString(values)); 
								 
								 name = "events";
								 atts = new String[]{"name","code","type"};
								 values = new String[]{"'"+eename+"'","'"+String.valueOf(event_code)+"'","'"+eetype+"'"}; 
								 insertInto();
								 System.out.println("events table updated:");
								 System.out.println(Arrays.toString(values)); 
							 }else{System.out.println("The order can not be booked!!");}
						}else{
							int order_no = globalCode;globalCode++;
							 int event_code = globalCode;globalCode++;
							 // TODO order number and event_code generator
							 select = new String[] {"code"};
								from = new String[] {"rooms"};
								where = new String[] {"name","=" ,"'"+eeroom+"'"};
								
								String[][] returned4 = select();
								String extract4 = returned4[0][0]; System.out.println(extract4);
								
							
							 
							 name = "orders";
							 atts = new String[]{"order_no","date","no_of_guests"};
							 values = new String[]{"'"+String.valueOf(order_no)+"'" , "'"+eedate+"'"  , "'"+eeguests+"'"}; 
							 
							 insertInto();
							 System.out.println("orders table updated:");
							 System.out.println(Arrays.toString(values));
							 
							 name = "orders_events";
							 atts = new String[]{"order_no","event_code","amount"};
							 values = new String[]{"'"+String.valueOf(order_no)+"'" ,"'"+String.valueOf(event_code) +"'" , "0"}; 
							 insertInto();
							 System.out.println("orders_events table updated:");
							 System.out.println(Arrays.toString(values));
							 
							 name = "rooms_events";
							 atts = new String[]{"room_code","event_code"};
							 values = new String[]{"'"+extract4+"'" ,"'"+String.valueOf(event_code)+"'"}; 
							 insertInto();
							 System.out.println("rooms_events table updated:");
							 System.out.println(Arrays.toString(values)); 
							 
							 name = "events";
							 atts = new String[]{"name","code","type"};
							 values = new String[]{"'"+eename+"'","'"+String.valueOf(event_code)+"'","'"+eetype+"'"}; 
							 insertInto();
							 System.out.println("events table updated:");
							 System.out.println(Arrays.toString(values)); 
						}		
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 	
				}
			});
			three = new JPanel();
			three.add(EventBook);
			three.add(EventName);
			three.add(ename);
			three.add(EvenType);
			three.add(etype);
			three.add(EventDate);
			three.add(edate);
			three.add(NumOfP);
			three.add(eguests);
			three.add(EventRoom);
			three.add(eroom);
			three.add(EvExe);
			f.add(three);
			
			
			
			//four
			JLabel flFrom = new JLabel("Show business information by date. from: ");
			JLabel flTo	= new JLabel("To: ");
			JTextField ftfFrom = new JTextField(10);
			JTextField ftfTo = new JTextField(10);
			JButton fbExe = new JButton("execute");
			fbExe.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String sFrom = ftfFrom.getText(); System.out.println(sFrom);
					String sTo = ftfTo.getText(); System.out.println(sTo);
					if(sFrom != "null" && sTo != "null") {
						from = new String[]{"orders"};
						where = new String[]{"date>"+"'"+sFrom+"'"," and ","date<"+"'"+sTo+"'"};
						name = "orders";
						try {
							select = describe();
							drowTable();
						} catch (Exception e1) {e1.printStackTrace();}
						
					}
				}
			});
			
			four = new JPanel();
			four.add(flFrom);
			four.add(ftfFrom);
			four.add(flTo);
			four.add(ftfTo);
			four.add(fbExe);
			f.add(four);
			
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(1800,200);
			f.setVisible(true);
		} catch(Exception e) {System.out.println(e);}
	}
	
	//for the new interface
		private JPanel one;
		private JPanel two;
		private JPanel three;
		private JPanel four;
		//one
		private JLabel olHead;
		private JLabel olEvent;
		private JLabel olRoom;
		private JLabel olEmp;
		private JButton obExe;
		//two
		private JLabel tlChangeEvent;
		private JLabel tlChangeMeal;
		public JButton tbExe;
		
	
	public void frame1() throws Exception{
		f = new JFrame();
		f.setVisible(true);
		f.setSize(600,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout());
		
		String[] op = new String[]{
				"none",
				"select",
				"update",
				"insert",
				"delete"
		};
		
		operation = new JComboBox(op);
		
		String[] res = showTables();
		String[] sshowTables = new String[15];
		sshowTables[0] = "none";
		for(int i=0;i<res.length;i++) {
			sshowTables[i+1] = res[i];
		}
		
		table1 = new JComboBox(sshowTables);
		table2 = new JComboBox(sshowTables);
		//
		select = new String[]{"name"};
		from = new String[]{"rooms"};
		where = new String[]{};
		String[][] result = select();
		String[] ssrooms = new String[16];
		ssrooms[0] = "none";
		for(int i=0; i<result.length; i++) {
			ssrooms[i+1] = result[i][0];
		}
		//
		room = new JComboBox(ssrooms);
		rooms = new JComboBox(ssrooms);
		
		//
		select = new String[]{"name"};
		from = new String[]{"events"};
		where = new String[]{};
		String[][] result2 = select();
		String[] sevents = new String[16];
		sevents[0] = "none";
		for(int i=0; i<result2.length; i++) {
			sevents[i+1] = result2[i][0];
		}
		//
		
		event = new JComboBox(sevents);
		eventtype = new JTextField(10);
		date = new JTextField(10);
		NofINvites = new JTextField(10);
		fromm = new JTextField(10);
		to = new JTextField(10);
		freeText = new JTextField(10);
		exe = new JButton("execute query");
		l1 = new JLabel("Order Event:");
		l2 = new JLabel("Event type:");
		l3 = new JLabel("Date:");
		l4 = new JLabel("# of invites:");
		l5 = new JLabel("Room:");
		l6 = new JLabel("Show info by date:");
		l7 = new JLabel("from:");
		l8 = new JLabel("to:");
		l9 = new JLabel("Show info by room:");
		l10 = new JLabel("Show info by event:");
		l11 = new JLabel("Free text");
		//res = new JTable(10,10);
		
			  
	    JPanel p = new JPanel();
		p.add(table1);
		p.add(table2);
		p.add(operation);
		p.add(l11);
		p.add(freeText);
		f.add(p);
		f.setLayout(new GridLayout(0,1));
		
		JPanel p1 = new JPanel();
		p1.add(l1);
		f.add(p1);
		
		JPanel p2 = new JPanel();
		p2.add(l2);
		p2.add(eventtype);
		f.add(p2);
		
		JPanel p3 = new JPanel();
		p3.add(l3);
		p3.add(date);
		f.add(p3);
		
		JPanel p4 = new JPanel();
		p4.add(l4);
		p4.add(NofINvites);
		f.add(p4);
		
		JPanel p5 = new JPanel();
		p5.add(l5);
		p5.add(rooms);
		f.add(p5);
		
		JPanel p6 = new JPanel();
		p6.add(l6);
		f.add(p6);
		
		JPanel p7 = new JPanel();
		p7.add(l7);
		p7.add(fromm);
		f.add(p7);
		
		JPanel p8 = new JPanel();
		p8.add(l8);
		p8.add(to);
		f.add(p8);
		
		JPanel p9 = new JPanel();
		p9.add(l9);
		p9.add(room);
		f.add(p9);
		
		JPanel p11 = new JPanel();
		p11.add(l10);
		p11.add(event);
		f.add(p11);
		
		JPanel p10 = new JPanel();
		p10.add(exe);
		f.add(p10);
		
//		JPanel p11 = new JPanel();
	//	p11.add(res);
		//f.add(p11);
		
		
		exe.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               //TODO
            }
        });
	}
	
	private static Connection con;
	private static PreparedStatement statement;
	private static String[] select;	//	for SQLQueryGenerator functions
	private static String[] from;	//
	private static String[] where;	//
	private static String name;		//
	private static String[] atts;	//
	private static String[] values;	//
	private static JTable table;
	private static final Integer tableDataLength = 15; 
	private static JScrollPane scrollPane;
	private static JFrame tableFrame;
	private static JFrame mainFrame;
	private static JButton executeButton;
	private static JComboBox<String> tablesComboBox;
	private static GridLayout gridlayout;
	private JFrame f;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	private JLabel l7;
	private JLabel l8;
	private JLabel l9;
	private JLabel l10;
	private JLabel l11;
	private JComboBox operation;
	private JComboBox table1;
	private JComboBox table2;
	private JComboBox room;
	private JComboBox rooms;
	private JComboBox event;
	
	private JTextField eventtype;
	private	JTextField date;
	private	JTextField NofINvites;
	private	JTextField fromm;
	private	JTextField to;
	private	JTextField freeText;
	private	JButton exe;
	private JTable res;
	
	private int globalCode;
	
	
	private static String[][] select() throws Exception {	// SQL SELECT, set 'select', 'from', 'where' variables before calling this function
		con = getConnection();
		System.out.println(SQLQueryGenerator.select(select, from, where));
		statement = con.prepareStatement(SQLQueryGenerator.select(select, from, where));
		ResultSet rs = statement.executeQuery();
		
		String[][] tableData = new String[tableDataLength][tableDataLength];
		
		for(int j=0; rs.next(); j++) {
			ArrayList<String> array = new ArrayList<String>();
			String[] list = new String[]{};
			
			for(int i=0; i<select.length; i++) {
				System.out.print(rs.getString(select[i]));
				System.out.print(" ");
				array.add(rs.getString(select[i]));
				list = array.toArray(new String[array.size()]); 
			}
			
			tableData[j] = list;
			System.out.println();
		}
		
		System.out.println("Select Over");
		return tableData;
	}
	
	private static String updateupdate;
	private static String[] set;
	
	private static void delete() throws SQLException {  // String[] from, String[] where
		System.out.println(SQLQueryGenerator.delete(from, where));
		statement = con.prepareStatement(SQLQueryGenerator.delete(from, where));
		statement.executeUpdate();
		System.out.println("Table deleted");
	}
	
	private static void update() throws SQLException { // String update, String[] set, String[] where
		System.out.println(SQLQueryGenerator.update(updateupdate, set, where));
		statement = con.prepareStatement(SQLQueryGenerator.update(updateupdate, set, where));
		statement.executeUpdate();
		System.out.println("Table Updated");
	}
	
	private static void insertInto() throws SQLException { // SQL INSERT INTO, set 'name', 'atts', 'values' variables before calling this function
			statement = con.prepareStatement(SQLQueryGenerator.insertInto(name, atts, values));
			statement.executeUpdate();
			System.out.println("Table inserted");
	}
	
	private static Connection getConnection() throws Exception { // like in main, only connects directly to our specific DB
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/" + SQLQueryGenerator.DB;
			
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,username,password);
			System.out.println("connected to phpmyadmin server");
			return con;
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	private static String[] describe() throws Exception { // gets you all the attributes of a table, set 'name' variable before calling this function
		con = getConnection();
		statement = con.prepareStatement("DESCRIBE " + SQLQueryGenerator.DB + "." + name);
		ResultSet rs = statement.executeQuery();
		ArrayList<String> columnNames = new ArrayList<String>();
		while(rs.next()) {
			System.out.print(rs.getString("field"));
			System.out.print(" ");
			columnNames.add(rs.getString("field"));
		}
		System.out.println("Describe Over");
		String[] list = columnNames.toArray(new String[columnNames.size()]); 
		return list;
	}
	
	private static String[] showTables() throws Exception { // gets you all the attributes of a table, set 'name' variable before calling this function
		con = getConnection();
		statement = con.prepareStatement("show tables");
		ResultSet rs = statement.executeQuery();
		ArrayList<String> columnNames = new ArrayList<String>();
		while(rs.next()) {
			System.out.print(rs.getString("Tables_in_" + SQLQueryGenerator.DB));
			System.out.print(" ");
			columnNames.add(rs.getString("Tables_in_" + SQLQueryGenerator.DB));
		}
		System.out.println("showTables Over");
		String[] list = columnNames.toArray(new String[columnNames.size()]); 
		return list;
	}
	
	private static void buildTable() {	// build GUI table, Showing the data you SELECT()ed. set select()'s and describe()'s variables before calling this function
		try {
			table = new JTable(select(), describe());
			System.out.println("Table Created");
		} catch (Exception e) {e.printStackTrace();}
	}


	
}
