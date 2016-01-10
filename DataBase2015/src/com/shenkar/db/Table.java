package com.shenkar.db;

import java.util.Arrays;

public class Table {
	@Override
	public String toString() {
		return "Table [name=" + name + ", atts=" + Arrays.toString(atts)
				+ ", types=" + Arrays.toString(types) + ", length="
				+ Arrays.toString(length) + "]";
	}

	public String name;
	public String[] atts;
	public String[] types;
	public String[] length;
	public String key;
	
	public Table(String name, String[] atts, String[] types, String[] length, String key) {
		this.name = name;
		this.atts = atts;
		this.types = types;
		this.length = length;
		this.key = key;
	}
}
