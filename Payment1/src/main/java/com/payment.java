package com;
import java.sql.*;
public class payment
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
 "jdbc:mysql://127.0.0.1:3306/item", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readItems()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th><th>Item Price</th>" + 
 "<th>Card Number</th> <th>Update</th><th>Remove</th></tr>";
 String query = "select * from items";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String paymentID = Integer.toString(rs.getInt("paymentID"));
 String customerID = rs.getString("customerID"); 
 String CustomerName = rs.getString("CustomerName");
 String Amount = Double.toString(rs.getDouble("Amount"));
 String cardNo = Integer.toString(rs.getInt("cardNo"));
 // Add into the html table
 output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + paymentID 
 + "'>" + customerID + "</td>";
 output += "<td>" + CustomerName + "</td>";
 output += "<td>" + Amount + "</td>";
 output += "<td>" + cardNo + "</td>";
 // buttons
output += "<td><input name='btnUpdate'type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
 + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"
 + paymentID   + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertItem(String code, String name,String price, String card)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting  to the database for inserting.";
 }
 // create a prepared statement
 String query = " insert into items (`paymentID`,`customerID`,`CustomerName`,`Amount`,`cardNo`)"
 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, code);
		 preparedStmt.setString(3, name);
		 preparedStmt.setDouble(4, Double.parseDouble(price));
		 preparedStmt.setDouble(5, Double.parseDouble(card));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newItems + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updateItem(String ID, String code, String name,String price, String card)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for updating.";
		 }
		 // create a prepared statement
		 String query = "UPDATE items SET customerID=?,CustomerName=?,Amount=?,cardNo=? WHERE paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, code);
		 preparedStmt.setString(2, name);
		 preparedStmt.setDouble(3, Double.parseDouble(price));
		 preparedStmt.setDouble(4, Double.parseDouble(card));
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newItems + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteItem(String paymentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 }
		 // create a prepared statement
		 String query = "delete from items where paymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newItems = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newItems + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		}