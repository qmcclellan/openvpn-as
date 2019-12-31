package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PrimalDbUtil {
	
	// start of the primal utility class

		private DataSource dataSource;
		
		public PrimalDbUtil(DataSource theDataSource){
			dataSource = theDataSource;
		}
		
		public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			try {
			if(myConn != null) {
				myConn.close();
				
			}if(myStmt != null) {
				myStmt.close();
				
			}if(myRs != null) {
				myRs.close();
			}
			}catch(Exception exc) {
				exc.printStackTrace();
				
			}
		}
		
		
		public List <Primal> pNameList() throws SQLException {

           //create list to hold data from database
			
			List<Primal> primalList = new ArrayList<Primal>();
			
			//create variables for connection, statement, and results
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			//create primal placeholder for objects to be instantiated to list
			
			Primal thePrimal;
			
			try {
			// get connection to database
			
			myConn = dataSource.getConnection();
			
			//create SQL statement to execute
			
			String sql = "select name, category " +
			" from primal order by name ASC, category";
			
			//create statement
			
			myStmt = myConn.createStatement();
			
			// execute statement
			
			myRs= myStmt.executeQuery(sql);
			
			//while loop to process object and add to list
			
			while(myRs.next()) {
			
				//retrieve data from each row 
				
				String name = myRs.getString("name");
				String category = myRs.getString("category");
				
				
				thePrimal = new Primal(name, category);
				
				primalList.add(thePrimal);
			}

			return primalList;
			}finally {
				//closing connections
				
				close(myConn, myStmt, myRs);
			}
			
		}
		
		
		
		public void deletePrimal(String thePrimalId) throws Exception {
			// create connection and statement 
			
			Connection myConn = null;
			
			PreparedStatement myStmt = null;
			
			
			//variable to hold converted string to integer
			int primalId;
			
			
			
			try {
				//convert string to integer
				
				primalId = Integer.parseInt(thePrimalId);
				
				//get connection
				
				myConn = dataSource.getConnection();
				
				//create sql string
				
				String sql = "delete from primal " +
				"where id=?";
				
				//create statement
				
				myStmt = myConn.prepareStatement(sql);
				
				//set parameters
				
				myStmt.setInt(1, primalId);
				
				//execute 
				
				myStmt.execute();
				
			}
			finally {
				close(myConn, myStmt, null);
			}
		}
		
		//method to update an existing primal
		public void updatePrimal(Primal thePrimal) throws Exception {
			
			//create connection and prepared statement
			
			Connection myConn = null;
			
			PreparedStatement myStmt = null;
			
			try {
				
			
			//get connection
				myConn = dataSource.getConnection();
				
			String sql = "update primal " +
			"set name=?, category=?, costLb=?, retailLb=?, margin=? "+
					"where id =?";
			
			//prepare statement
			
			myStmt = myConn.prepareStatement(sql);
			
			//set parameters for statement
			
			myStmt.setString(1, thePrimal.getName());
			myStmt.setString(2, thePrimal.getCategory());
			myStmt.setDouble(3, thePrimal.getCostLb());
			myStmt.setDouble(4, thePrimal.getRetailLb());
			
			//execute statement
			
			myStmt.execute();
			
			}
			finally {
				close(myConn,myStmt,null);
			}
			
		}
		
		//method to retrieve object from database based on id
		
		public Primal getPrimal(String thePrimalId) throws Exception {
			
			//create primal object
			Primal thePrimal = null;
			
			//create connection statement, result set
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			
			//create integer variable to hold converted string
			
			int primalId;
			
			try {
				
				//convert string to integer
				
				primalId = Integer.parseInt(thePrimalId);
				
				//get connection
				myConn = dataSource.getConnection();
				
				//create sql for statement
				
				String sql = "select * from primal where id = ?";
				
				//create prepared statement
				
				myStmt = myConn.prepareStatement(sql);
				
				//set parameters
				
				myStmt.setInt(1, primalId);
				
				// execute query
				
				myRs = myStmt.executeQuery();
				
				//process result
				
				if(myRs.next()) {
				
				String name = myRs.getString("name");
				String category = myRs.getString("category");
				Double costLb = myRs.getDouble("costLb");
				Double retailLb = myRs.getDouble("retailLb");
				
				//create object
				
				thePrimal =new Primal(name, category, costLb, retailLb);
				
				
				
			} else { throw new Exception
				("Could not find:" + thePrimalId);
			}
				
		
			return thePrimal;
			
		}finally {
			close(myConn,myStmt,myRs);
		}
		}
		
		public void addPrimal(Primal thePrimal) {
			//create connection variables
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			
			try {
			//get connection
			myConn = dataSource.getConnection();
			
			//create sql string
			String sql = "insert into primal " +
			"(name, category, costLb, retailLb) " +
			"values(?,?,?,?)";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//Enter values for prepared statement
			
			myStmt.setString(1, thePrimal.getName());
			myStmt.setString(2, thePrimal.getCategory());
			myStmt.setDouble(3, thePrimal.getCostLb());
			myStmt.setDouble(4, thePrimal.getRetailLb());
			
			//execute statement
			
			myStmt.execute(sql);
			
			close(myConn, myStmt, null);
			
			}catch(Exception exc) {
				exc.printStackTrace();
			}
		}
		
		
		//used to make a list of primals ordered by category
		public List<Primal> getPrimalsOrdered() throws SQLException {
			//create list to hold data from database
			
			List<Primal> primalList = new ArrayList<Primal>();
			
			//create variables for connection, statement, and results
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			//create primal placeholder for objects to be instantiated to list
			
			Primal thePrimal;
			
			try {
			// get connection to database
			
			myConn = dataSource.getConnection();
			
			//create SQL statement to execute
			
			String sql = "select * from primal order by category";
			
			//create statement
			
			myStmt = myConn.createStatement();
			
			// execute statement
			
			myRs= myStmt.executeQuery(sql);
			
			//while loop to process object and add to list
			
			while(myRs.next()) {
			
				//retrieve data from each row 
				
				String name = myRs.getString("name");
				String category = myRs.getString("category");
				Double costLb = myRs.getDouble("costLb");
				Double retailLb = myRs.getDouble("retailLb");
				
				
				thePrimal = new Primal(name, category, costLb, retailLb);
				
				primalList.add(thePrimal);
			}

			return primalList;
			}finally {
				//closing connections
				
				close(myConn, myStmt, myRs);
			}
		}
		
		
		//used to make a list of primals unordered by category
			public List<Primal> getPrimals() throws SQLException {
				//create list to hold data from database
				
				List<Primal> primalList = new ArrayList<Primal>();
				
				//create variables for connection, statement, and results
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				//create primal placeholder for objects to be instantiated to list
				
				Primal thePrimal;
				
				try {
				// get connection to database
				
				myConn = dataSource.getConnection();
				
				//create SQL statement to execute
				
				String sql = "select * from primal";
				
				//create statement
				
				myStmt = myConn.createStatement();
				
				// execute statement
				
				myRs= myStmt.executeQuery(sql);
				
				//while loop to process object and add to list
				
				while(myRs.next()) {
				
					//retrieve data from each row 
					
					String name = myRs.getString("name");
					String category = myRs.getString("category");
					Double costLb = myRs.getDouble("costLb");
					Double retailLb = myRs.getDouble("retailLb");
					
					
					thePrimal = new Primal(name, category, costLb, retailLb);
					
					primalList.add(thePrimal);
				}

				return primalList;
				}finally {
					//closing connections
					
					close(myConn, myStmt, myRs);
				}
			}
		
		
	
}
