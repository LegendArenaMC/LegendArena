package net.thenamedev.legendapi.sql;

public enum SCHEMA {
	
	MySQL,
	SQLite;
	
	@Override
	public String toString(){
		return ""; //who really thought a recursive call was a good idea...?
	}

}
