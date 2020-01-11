package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ResultsDbUtil {
	
	private DataSource dataSource;

	public ResultsDbUtil(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
		if(myRs!= null) {
			myRs.close();
		}
		if(myStmt != null) {
			myStmt.close();
		}
		if(myConn != null) {
			myConn.close();
		}
	}catch(Exception exc) {
		exc.printStackTrace();
	}
	}
	
	public List<Results> getResultList() throws SQLException{
		Results result = null;
		List<Results>  resultsList = new ArrayList<Results>();
		
		//create variables
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs= null; 
		
		try {
		//get connection
		
		myConn = dataSource.getConnection();
		
		//create sql string
		
		String sql = "select * from results";
		
		//create statement
		
		myStmt = myConn.createStatement();
		
		//process database info
		
		myRs = myStmt.executeQuery(sql);
		
		//create result object
		
		while(myRs.next()) {
			
			int testId = myRs.getInt("testId");
			double wholesaleCost = myRs.getDouble("wholesaleCost");
			double profitMargin = myRs.getDouble("profitMargin");
			double yield = myRs.getDouble("yield");
			double epWeight = myRs.getDouble("epWeight");
			double epCost = myRs.getDouble("epCost");
			double epRetail = myRs.getDouble("epRetail");
			double valueOfUsableProduct = myRs.getDouble("valueOfUsableProduct");
			double costOfUsableProduct = myRs.getDouble("costOfUsableProduct");
			
			result = new Results(testId, wholesaleCost, profitMargin, yield, epWeight, epCost, epRetail, valueOfUsableProduct, costOfUsableProduct);
			
			//add object to list
			resultsList.add(result);
		}
		
		

		return resultsList;
		
	}finally {
		close(myConn, myStmt, myRs);
		}
	}

	public void addResults(Results theResult) {
		
		//create connection variables
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
		
			//create connection
		myConn = dataSource.getConnection();
		
		//create sql string
		
		String sql = "insert into results "+
					"testId, wholesaleCost, profitMargin, yield, epWeight, epCost, epRetail, "
					+ "valueOfUsableProduct, costOfUsableProduct "+
					"values(?,?,?,?,?,?,?,?,?)";
		
		//create statement
		
		myStmt = myConn.prepareStatement(sql);
		
		//set statements
		
		myStmt.setInt(1,theResult.getTestId());
		myStmt.setDouble(2,theResult.getWholesaleCost());
		myStmt.setDouble(3, theResult.getProfitMargin());
		myStmt.setDouble(4, theResult.getYield());
		myStmt.setDouble(5, theResult.getEpWeight());
		myStmt.setDouble(6, theResult.getEpWeight());
		myStmt.setDouble(3, theResult.getEpRetail());
		myStmt.setDouble(3, theResult.getValueOfUsableProduct());
		myStmt.setDouble(3, theResult.getCostOfUsableProduct());
		
		//execute statement
		
		myStmt.execute();
		
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(myConn, myStmt, null);
	}
	}
	
	
	public Results getResult(String theResultId) throws SQLException {
		//create variables
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		Results result = null;
		
		try {
		//get connection
		
		myConn = dataSource.getConnection();
		
		//parse id
		
		int resultId = Integer.parseInt(theResultId);
	
		//create sql string
		
		String sql = " select * from result where id=?";
		
		//create statement
		
		myStmt= myConn.prepareStatement(sql);
		
		//retrieve result
		
		myStmt.setInt(1, resultId);
		
		//execute
		myRs = myStmt.executeQuery();
		
		while(myRs.next()) {
			
			int testId = myRs.getInt("testId");
			double wholesaleCost = myRs.getDouble("wholesaleCost");
			double profitMargin = myRs.getDouble("profitMargin");
			double yield = myRs.getDouble("yield");
			double epWeight = myRs.getDouble("epWeight");
			double epCost = myRs.getDouble("epCost");
			double epRetail = myRs.getDouble("epRetail");
			double valueOfUsableProduct = myRs.getDouble("valueOfUsableProduct");
			double costOfUsableProduct = myRs.getDouble("costOfUsableProduct");
			
			 result = new Results(testId, wholesaleCost, profitMargin, yield, epWeight, epCost, epRetail, valueOfUsableProduct, costOfUsableProduct);
			
		}
		return result;
		
	}finally {
		//close statements
		close(myConn, myStmt, myRs);
	}
	}
	public void deleteResults(String theResultId) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		//parse string to integer
		
		int resultId = Integer.parseInt(theResultId);
		
		//get connection to database
		
		try {
			myConn = dataSource.getConnection();
		
		//create sql string
			
			String sql = "delete * from results where id=?";
			
		//create statement
			
			myStmt = myConn.prepareStatement(sql);
			
		//set id parameter
			
			myStmt.setInt(1, resultId);
			
		//execute
			 
			myStmt.execute();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
			close(myConn, myStmt, null);
	}
		
	}
	
	
	
