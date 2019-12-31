package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

		public class CuttingTestDbUtil {

			DataSource dataSource;
			
		public CuttingTestDbUtil(DataSource theDataSource) {
				
				this.dataSource = theDataSource;
				
		}

		public void close(Connection conn, Statement myStmt, ResultSet myRs) throws SQLException {
			//method use to close all connections
			
			if(conn != null) {
				conn.close();
				
			}else if(myStmt != null) {
				myStmt.close();
				
			}else if(myRs != null) {
				myRs.close();
			}
		}

		public List<CuttingTest> getTestList() throws SQLException{
			//list
			List<CuttingTest> testList = new ArrayList<>();
			
			//create a temporary test
			CuttingTest test;
			
			//create connection variables 
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			try {
			//get connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "select * from results ";
			
			//create statement
			myStmt = myConn.createStatement();
			
			//execute statement
			myRs= myStmt.executeQuery(sql);
			
			//process resultSet
			
			while(myRs.next()) {
				
				//retrieve data from each row
				int testId = myRs.getInt("testId");
				int primalId = myRs.getInt("primalId");
				double weight = myRs.getDouble("weight");
				double trim = myRs.getDouble("trim");
				double wholeCost = myRs.getDouble("wholeCost");
				double profitMargin = myRs.getDouble("profitMargin");
				double yield = myRs.getDouble("yield");
				double epWeight = myRs.getDouble("epWeight");
				double epCost = myRs.getDouble("epCost");
				double epRetail = myRs.getDouble("epRetail");
				double valueOfUsableProduct = myRs.getDouble("valueOfUsableProduct");
				double costOfUsableProduct = myRs.getDouble("CostOfUsableProduct");
				
				
				test = new CuttingTest(testId, primalId, weight,  trim, wholeCost,  profitMargin,
						 yield,  epWeight,  epCost, epRetail,
						 valueOfUsableProduct,  costOfUsableProduct);
				
				testList.add(test);
				
			}
			
			return testList;
		}finally {
			close(myConn, myStmt, myRs);
		}
		}

		public CuttingTest getTest(String resultId) throws Exception {
			CuttingTest test = null;
			
			//create connection variables
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			
			//integer place holder
			int theResultId;
			
			try {
				//get connection
				myConn = dataSource.getConnection();
				
				
				//create ssl string
				
				String sql = "select * from results where id =?";
				
				//setup prepared statement
				
				myStmt = myConn.prepareStatement(sql);
				
				//parse string
				
				theResultId = Integer.parseInt(resultId);
				
				//set parameters
				
				myStmt.setInt(1, theResultId);
				
				//execute 
				
				myRs = myStmt.executeQuery();
				
				//process query
				
				while(myRs.next()) {
					
					int primalId = myRs.getInt("primalId");
					int testId = myRs.getInt("testId");
					double weight = myRs.getDouble("weight");
					double trim = myRs.getDouble("trim");
					double wholeCost = myRs.getDouble("wholeCost");
					double profitMargin = myRs.getDouble("profitMargin");
					double yield = myRs.getDouble("yield");
					double epWeight = myRs.getDouble("epWeight");
					double epCost = myRs.getDouble("epCost");
					double epRetail = myRs.getDouble("epRetail");
					double valueOfUsableProduct = myRs.getDouble("valueOfUsableProduct");
					double costOfUsableProduct = myRs.getDouble("CostOfUsableProduct");
					
					
					test = new CuttingTest(primalId,testId, weight,  trim, wholeCost,  profitMargin,
							  yield,  epWeight,  epCost, epRetail,
							 valueOfUsableProduct,  costOfUsableProduct);
					
				}
					
				
			return test;
			
		}finally {
			//method to close connection
			close(myConn, myStmt,myRs);
		}
		}

		public void addTest(CuttingTest test) throws Exception{
			//variables for connection
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
			//get connection
			
			myConn= dataSource.getConnection();
			
			//create sql string
			 String sql = "insert into results "+
					 	"(primalId, testId, wholeCost,  profitMargin, yield,  epWeight,  epCost, epRetail, valueOfUsableProduct,  costOfUsableProduct)"+
					 	"values(?,?,?,?,?,?,?,?,?,?)";
			 
			 //create statement
			 myStmt = myConn.prepareStatement(sql);
			 
			 //set parameters
			 
			 myStmt.setInt(1, test.primal.getId());
			 myStmt.setInt(2, test.getId());
			 myStmt.setDouble(3, test.getWholesaleCost());
			 myStmt.setDouble(4, test.getProfitMargin());
			 myStmt.setDouble(5, test.getYield());
			 myStmt.setDouble(6, test.getEpWeight());
			 myStmt.setDouble(7, test.getEpCost());
			 myStmt.setDouble(8, test.getEpRetail());
			 myStmt.setDouble(9, test.getValueOfUsableProduct());
			 myStmt.setDouble(10, test.getCostOfUsableProduct());
			 
			 //execute statement
			 
			 myStmt.execute();
			 
			}catch(Exception Exc) {
				
				close(myConn, myStmt, null);
			}
			
		}
				
		public void deleteTest(String theResultId) throws SQLException {
			
			//create variables
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			int resultId = Integer.parseInt(theResultId);
			
			
			try {
			//get connection
			myConn = dataSource.getConnection();
			
			//create sql string
			String sql = "delete from results "+
							"where id = ?";
			
			//create prepared statement
			myStmt= myConn.prepareStatement(sql);
			
			//set parameters
			myStmt.setInt(1, resultId);
			
			//execute statement
			myStmt.execute();
			
			
		}finally {
			close(myConn, myStmt, null);
		}

		 }

		 	
		}

