package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

class CutterDbUtil{
	
	private DataSource dataSource;
	
	CutterDbUtil(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		try{
			if(myConn != null) {
		
			myConn.close();
			
		} if(myStmt != null) {
			myStmt.close();
			
		} if(myRs != null) {
			myRs.close();
	}
		}
	catch(SQLException exc) {
		exc.printStackTrace();
	}
	}
	
	public List<Cutter> getCutterList() throws SQLException{
		//create an array list to hold our cutters
		List<Cutter> cutterList = new ArrayList<>();
		
		//variables needed
		
		Connection myConn =null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get connection
		myConn = dataSource.getConnection();
		
		//create and sql string to be executed
		
		String sql = "select * from cutter";
				
		//create statement
		
		myStmt = myConn.createStatement();
		
		//execute query
		
		myRs = myStmt.executeQuery(sql);
		
		while(myRs.next()) {
			
			int id = myRs.getInt("id");
			String firstName = myRs.getString("firstName");
			String lastName = myRs.getString("lastName");
			int numYears = myRs.getInt("numYears");
			
			Cutter cutter = new Cutter(id, firstName, lastName, numYears);
			
			cutterList.add(cutter);
		}
		//return the list
		return cutterList;
	}finally {
		close(myConn, myStmt, myRs);
	}
	}
	
	public void addCutter(Cutter cutter) {
		
		//create variables
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
		//get connection
		
		myConn = dataSource.getConnection();
		
		//create statement
		
		
		
		String sql = "insert into cutter " +
					"(firstName, lastName) " +
					"values(?,?)";
		
		
		myStmt = myConn.prepareStatement(sql);
		
		//Execute
		myStmt.execute();
		
		//get prepared statement
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public Cutter getCutter(String cutterId) {
		Cutter theCutter = null;
		
		
		return theCutter;
	}
	
	public void updateCutter(Cutter cutter) throws SQLException {
		//set up variables
		
		Connection myConn = null;
		PreparedStatement myStmt =  null;
		try {
		//get Connection
		
		myConn = dataSource.getConnection();
		
		//create sql string
		String sql = "update cutter " +
				"firstName = ?, lastName = ?, numOfYears = ? " +
				"where id = ?";
		
		//create prepared statement
		myStmt = myConn.prepareStatement(sql);
		
		//set parameters
		myStmt.setString(1, cutter.getFirstName());
		myStmt.setString(2, cutter.getLastName());
		myStmt.setInt(3, cutter.getNumOfYears());
		
		//execute statement
		
		myStmt.execute();
		
		}finally {
			//close open connections
			close(myConn, myStmt, null);
		}
		
	}
	
	public void deleteCutter(String theCutterId) throws SQLException {
		//create variables
		
		Connection myConn= null;
		PreparedStatement myStmt = null;
		
		int cutterId = Integer.parseInt(theCutterId);
		
		try{
		//get connection
		myConn = dataSource.getConnection();
		
		//create sql string
		String sql = "delete from cutter "+
		"where id = ?";
		
		
		//create statement
		myStmt = myConn.prepareStatement(sql);
		
		//set parameters for prepared statement
		
		myStmt.setInt(1, cutterId);
		
		//execute statement
		
		myStmt.execute();
		
		
	}finally {
		close(myConn, myStmt, null);
	}}

}//end db class

