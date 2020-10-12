package com.project.utils;

import java.sql.*;


public class ConnectionUtility {

	public static Connection getDerbyConnection() {
		Connection derbyConnection=null;
		
		try
		{
			String derbyURL = "jdbc:derby:BugReport;";
			
			

			//derby.language.sequence.preallocator=1;
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			
			derbyConnection = DriverManager.getConnection(derbyURL);
			
			
			if(derbyConnection!=null) {
				System.out.println("Connection Established");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			
		}
		
		
		return derbyConnection;
	}
	
	
	
}
