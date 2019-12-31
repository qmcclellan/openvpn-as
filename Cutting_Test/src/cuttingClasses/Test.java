package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Test {
	private int id;
	private Cutter cutter;
	private int cutterId;
	private double weight;
	private double trim;
	private double waste;
	
	public Test(int id, Cutter cutter, double weight, double trim, double waste) {
		this.id = id;
		this.cutterId = cutter.getId();
		this.weight = weight;
		this.trim = trim;
		this.waste = waste;
	}
	
	public Test(int id, int cutterId, double weight, double trim) {
		this.id = id;
		this.cutterId = cutter.getId();
		this.weight = weight;
		this.trim = trim;
	}
	
	public Test(Cutter cutter, double weight, double trim, double waste) {
		this.cutter = cutter;
		this.cutterId = cutter.getId();
		this.weight = weight;
		this.trim = trim;
		this.waste = waste;
	}
	
	
	public Test(int cutterId, double weight, double trim, double waste) {
		
		this.cutterId = cutterId;
		this.weight = weight;
		this.trim = trim;
		this.waste = waste;
	}
	
	public Test(int id, int cutterId, int primalId, double weight, double trim, double waste) {
		this.id = id;
		this.cutterId = cutterId;
		this.weight = weight;
		this.trim = trim;
		this.waste = waste;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", cutterId=" + cutterId +  ", weight=" + weight + ", trim="
				+ trim + ", waste=" + waste + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCutterId() {
		return cutterId;
	}

	public void setCutterId(Cutter cutter) {
		this.cutterId = cutter.getId();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getTrim() {
		return trim;
	}

	public void setTrim(double trim) {
		this.trim = trim;
	}

	public double getWaste() {
		return waste;
	}

	public void setWaste(double waste) {
		this.waste = waste;
	}
	
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
			
			String sql = "select * from test";
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process results
			while(myRs.next()) {
				
				int id = myRs.getInt("id");
				int cutterId = myRs.getInt("cutter_id");
				int primalId = myRs.getInt("primal_id");
				double weight = myRs.getDouble("weight");
				double trim = myRs.getDouble("trim");
				double waste = myRs.getDouble("waste");
				
				test = new Test(id, cutterId, primalId, weight, trim, waste);
				
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
			
			//create sql strin
			
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

				int id = myRs.getInt("id");
				int cutterId = myRs.getInt("cutter_id");
				int primalId = myRs.getInt("primal_id");
				double weight = myRs.getDouble("weight");
				double trim = myRs.getDouble("trim");
				double waste = myRs.getDouble("waste");
				
				test = new Test(id, cutterId, primalId, weight, trim, waste);
				
			}
			return test;
			
		}finally {
			close(myConn, myStmt, myRs);
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
				String sql ="insert into test " +
							"(cutterID, primalId, weight, trim, waste) "+
							"values(?,?,?,?,?)";
				
				//create statement
				
				myStmt = myConn.prepareStatement(sql);
				
				
				//set params
				myStmt.setInt(2, theTest.getCutterId());
				myStmt.setDouble(3, theTest.getWeight());
				myStmt.setDouble(4, theTest.getTrim());
				myStmt.setDouble(5, theTest.getWaste());
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
		
	}//inner class
	

}//outer class
