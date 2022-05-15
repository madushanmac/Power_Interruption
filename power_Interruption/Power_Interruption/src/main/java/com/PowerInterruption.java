package com;

import java.sql.*;

public class PowerInterruption {

	public Connection connect() 
	{ 
	 Connection conn = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powerInterruption", "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return conn; 
	}
    
    
    //method to insert data
    public String insertInterruptionDetails(String interruptionID, String date, String location, String description) {
    	
    	
    	String Output = "";
    	
    	try {
    		Connection conn = connect();
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO powerInterruptions (interruptionID,date,location,description) VALUES (?,?,?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, interruptionID);
        	preparedStatement.setString(2, date);
        	preparedStatement.setString(3, location);
        	preparedStatement.setString(4, description);
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	Output = "Item inserted successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to insert the Interruption Details";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to update data
    public String updatePowerinterruptionDetails(String interruptionID, String date, String location, String description) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "UPDATE powerInterruptions SET interruptionID = ?,date = ?,location = ?,description = ? WHERE interruptionID = ?";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, date);
        	preparedStatement.setString(2, location);
        	preparedStatement.setString(3, description);
        	preparedStatement.setString(4, interruptionID);
        	
        	
        	//execute the SQL statement
        	preparedStatement.executeUpdate();
        	conn.close();
        	
        	Output = "details updated successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to update the details";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    
    //method to read data
    public String readdetails() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM powerinterruptions";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table border='1'><tr><th>Interruption ID</th>" +"<th>date</th><th>location</th>"
    		+ "<th> Description</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";
        	
        	while(resultSet.next()) {
        		String interruptionID = Integer.toString(resultSet.getInt("interruptionID"));
        		String date = resultSet.getString("date");
        		String location = resultSet.getString("location");
        		String description = resultSet.getString("description");
        		
        		// Add a row into the HTML table
        		Output += "<tr><td><input id='interruptionID' name='hidInterruptionIDUpdate' type='hidden' value='"+interruptionID+"'>" + date + "</td>"; 
        		Output += "<td>" + location + "</td>"; 
       
        		Output += "<td>" + description + "</td>";
        		
        		// buttons
        		Output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary'></td>" 
        				+ "<td><form method='post' action='items.jsp'>"
        				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-sm btn-danger'>"
        				+ "<input name='hidItemIDDelete' type='hidden' value='" + interruptionID + "'>"
        				+ "</form></td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the items";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to delete data
    public String deleteDetails(String interruptionID) {
    	String Output = "";
    	Connection conn = connect();
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "DELETE FROM powerinteruptions WHERE interruptionID = ?";
        	
        	//binding data to the SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setInt(1, Integer.parseInt(interruptionID));
        	
        	//executing the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	Output = "Deleted successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to delete the details";
    		System.err.println(e.getMessage());
    	}
    	return Output;
    }
}
