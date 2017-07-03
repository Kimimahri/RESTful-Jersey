package com.mkyong.rest;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Rest {
	
	public static void AddItem(JSONObject object)
	{
		  Connection c;
		    Statement stmt;
		 
		    try {
		        Class.forName("org.postgresql.Driver");
		        c = DriverManager
		                .getConnection("jdbc:postgresql://90.156.142.56:5432/postgres","postgres", "Sup3RPass8d");
		        c.setAutoCommit(false);
		        System.out.println("-- Opened database successfully");
		        String sql;
		        
		        //--------------- INSERT ROWS ---------------
		        stmt = c.createStatement();
		        sql = "INSERT INTO items (name,quantity,price) VALUES ('"+object.get("name")+"', "+object.get("quantity")+", "+object.get("price")+");";
		        stmt.executeUpdate(sql);
		 
		        stmt.close();
		        c.commit();
		        
		        
		        c.close();
		   	 
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
		    }
		    System.out.println("-- Operation done successfully");
		
		
	}
	
	public static void UpdateItem(String msg, JSONObject object)

	{
		  Connection c;
		    Statement stmt;
		 
		    try {
		        Class.forName("org.postgresql.Driver");
		        c = DriverManager
		                .getConnection("jdbc:postgresql://90.156.142.56:5432/postgres","postgres", "Sup3RPass8d");
		        c.setAutoCommit(false);
		        System.out.println("-- Opened database successfully");
		        String sql;
		        
		        //-------------- UPDATE DATA ------------------
		        stmt = c.createStatement();
		        sql = "UPDATE items set name = '"+object.get("name")+"', quantity = "+object.get("quantity")+", price = "+object.get("price")+" where id="+msg+";";
		        stmt.executeUpdate(sql);
		        c.commit();
		        stmt.close();
		 
	
		        
		        c.close();
		   	 
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
		    }
		    System.out.println("-- Operation done successfully");
		
		
	}

	public static void DeleteItem(String object)

	{
		  Connection c;
		    Statement stmt;
		 
		    try {
		        Class.forName("org.postgresql.Driver");
		        c = DriverManager
		                .getConnection("jdbc:postgresql://90.156.142.56:5432/postgres","postgres", "Sup3RPass8d");
		        c.setAutoCommit(false);
		        System.out.println("-- Opened database successfully");
		        String sql;
		        
		        //-------------- DELETE DATA ----------------------
		        stmt = c.createStatement();
		        sql = "DELETE from items where ID= "+object+";";
		        stmt.executeUpdate(sql);
		        c.commit();
		        stmt.close();
		 
	
		        
		        c.close();
		   	 
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
		    }
		    System.out.println("-- Operation deleted done successfully");
		
		
	}
	
	 public static JSONArray SelectItem()

	{
		  Connection c;
		    Statement stmt;
			JSONArray ar=new JSONArray();
			
		    try {
		        Class.forName("org.postgresql.Driver");
		        c = DriverManager
		                .getConnection("jdbc:postgresql://90.156.142.56:5432/postgres","postgres", "Sup3RPass8d");
		        c.setAutoCommit(false);
		        System.out.println("-- Opened database successfully");
		        
		        
		        //--------------- SELECT DATA ------------------
		        stmt = c.createStatement();
		        ResultSet rs = stmt.executeQuery( "SELECT * FROM items;" );

    while ( rs.next() ) {
    	
    				JSONObject obj = new JSONObject();
		            obj.put("id", rs.getInt("id"));
		            obj.put("name", rs.getString("name"));
		            obj.put("quantity", rs.getInt("quantity"));
		            obj.put("price", rs.getDouble("price"));
		            ar.add(obj);
		            
		        }
		        		        
		        rs.close();
		        stmt.close();
		        c.commit();
		 	
		
		        c.close();
		        
		   	 
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
		    }
		    System.out.println("-- Operation SelectAll done successfully");
		    return ar;
		
		
	}
	 public static JSONObject SelectItem (String object) {
		 
		  Connection c;
		    Statement stmt;
		    JSONObject obj = new JSONObject();
			
		    try {
		        Class.forName("org.postgresql.Driver");
		        c = DriverManager
		                .getConnection("jdbc:postgresql://90.156.142.56:5432/postgres","postgres", "Sup3RPass8d");
		        c.setAutoCommit(false);
		        System.out.println("-- Opened database successfully");
		        
		        
		        //--------------- SELECT DATA ------------------
		        stmt = c.createStatement();
		        ResultSet rs = stmt.executeQuery( "SELECT * FROM items Where id = "+object+";" );

  while ( rs.next() ) {
  	
  				
		            obj.put("id", rs.getInt("id"));
		            obj.put("name", rs.getString("name"));
		            obj.put("quantity", rs.getInt("quantity"));
		            obj.put("price", rs.getDouble("price"));
		            
		            
		        }
		        		        
		        rs.close();
		        stmt.close();
		        c.commit();
		 	
		        
		        c.close();
		        
		   	 
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
		    }
		    System.out.println("-- Operation Select done successfully");
		    return obj;
	 }


	public static void main (String args []){
		

		DeleteItem("3");
	
	}
}
