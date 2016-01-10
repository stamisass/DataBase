package com.shenkar.db;

import java.util.Arrays;

public class Data { //INSERT INTO ballroom.testTable (a,b,c,d,e) VALUES (30,20,100,200,50);
	@Override
	public String toString() {
		return "Data [vals=" + Arrays.toString(vals) + "]";
	}

	public String[][] vals;
	
	public Data(String[][] vals) {
		this.vals = vals;
	}
}
