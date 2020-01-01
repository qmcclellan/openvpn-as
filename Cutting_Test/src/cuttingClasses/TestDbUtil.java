package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class TestDbUtil{
	DataSource dataSource;
	
	public TestDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		
		try {
		if(myConn != null) {
			myConn.close();
		}
		if(myStmt !=null) {
			myStmt.close();
		}
		if(myRs != null) {
			myRs.close();
		}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public List<Test> getTests() throws SQLException{
		//variables for test object and list class
		
		List<Test> testList = new ArrayList<>();
		Test test = null;
		
		//create new objects for connections
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get connection
		myConn = dataSource.getConnection();
		
		//create statement
		myStmt  = myConn.createStatement();
		
		//create sql string
		
		String sql = "select * from test order by cutterId";
		
		//execute query
		myRs = myStmt.executeQuery(sql);
		
		//process results
		while(myRs.next()) {
			
			//retrieve data from each row
			int testId = myRs.getInt("testId");
			String cutterName = myRs.getString("cutterName");
			int cutterId = myRs.getInt("cutterId");
			String primalName = myRs.getString("primalName");
			int primalId = myRs.getInt("primalId");
			double weight = myRs.getDouble("weight");
			double trim = myRs.getDouble("trim");
			double waste = myRs.getDouble("waste");
			
			//instantiate test from database
			test = new Test(testId, cutterName, cutterId, primalName, primalId, weight, trim, waste);
			
			//add to list
			testList.add(test);
			
		}
		
		return testList;
	}finally {
		//close connections
		close(myConn, myStmt, myRs);
	}
	}//end of test list method
	
	public Test getTest(String testId)throws Exception {
		//variables for test and id number
		Test test = null;
		
		int theTestId ;
		
		//connection variables
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get connection
		
		myConn = dataSource.getConnection();
		
		//create sql string
		
		String sql = " select * test where id =?";
		
		//create prepared statement
		
		myStmt = myConn.prepareStatement(sql);
		
		//parse string to integer
		
		theTestId = Integer.parseInt(testId);
		
		//set params
		
		myStmt.setInt(1, theTestId);
		
		//execute query
		myRs = myStmt.executeQuery();
		
		//process results
		while(myRs.next()) {

			
			
		}
		return test;
		
	}finally {
		close(myConn, myStmt, myRs);
	}
	}
	
public void updateStudent(Test theTest) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
		// get db connection
		
		myConn= dataSource.getConnection();
		
		//create sql statements
		
		String sql = "update test "
		+"set cutterName =?, cutterId=?, primalName=?, primalId=?, weight=?, trim=?, waste=? "
		+"where id=?";
		
		//prepare statement
		
		myStmt = myConn.prepareStatement(sql);
		
		//set params
		
		myStmt.setString(1, theTest.getCutterName());
		myStmt.setInt(2, theTest.getCutterId());
		myStmt.setString(3, theTest.getPrimalName());
		myStmt.setInt(4, theTest.getPrimalId());
		myStmt.setDouble(5, theTest.getWeight());
		myStmt.setDouble(6, theTest.getTrim());
		myStmt.setDouble(5, theTest.getWaste());
		
		//execute sql
		
		myStmt.execute();
		
	}
		finally {
			close(myConn, myStmt, null);
		}
	}
	
	public void addTest(Test theTest) throws SQLException {
		
		//variables
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get connection
			myConn = dataSource.getConnection();
			
			//create sql string
			
			String sql = "insert into test "+
						"(cutterName, cutterId, primalName, primalId, weight, trim, waste) "+
						"values(?,?,?,?,?,?,?)"	;
			
			//create statement
			
			myStmt = myConn.prepareStatement(sql);
			
			
			//set params
			
			myStmt.setString(1, theTest.getCutterName() );
			myStmt.setInt(2, theTest.getId());
			myStmt.setString(3, theTest.getPrimalName());
			myStmt.setInt(4, theTest.getPrimalId());
			myStmt.setDouble(5,theTest.getWeight());
			myStmt.setDouble(6, theTest.getTrim());
			myStmt.setDouble(7,theTest.getWaste());
			
			//Execute query
			
			myStmt.execute(sql);
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		close(myConn, myStmt, null);
	}
	
	public void deleteTest(String theTestId) throws SQLException {
		//variables
		int testId;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
		//get connection
		myConn = dataSource.getConnection();
			
		//create sql string
		String sql = "delete from test where id =?";
		
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		
		//parse string
		testId = Integer.parseInt(theTestId);
		
		//set parameter
		myStmt.setInt(1, testId);
		
		//execute query
		myStmt.execute();
		
	}finally {
		close(myConn, myStmt, null);
	}
}
	
}

