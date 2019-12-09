package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Primal {
	
	private int id;
	private String name;
	private String category;
	private Double costLb;
	private Double retailLb;
	private Double margin;
	
	
	private Formulas formula = new Formulas();
		

	public Primal(String primal, Double costLb) {
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = costLb + (costLb * .25);
	}
	
	public Primal(String primal, Double costLb, Double retailLb) {
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = retailLb;
	}



	public Primal(String primal, Double costLb, Double retailLb, Double margin) {
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = retailLb;
		this.margin = margin;
	}



	public Primal(String primal, String cat, Double costLb, Double retailLb, Double margin) {
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = retailLb;
		this.margin = margin;
		this.category = setCategory(cat);
	}
	
	public Primal(int id, String primal, String cat, Double costLb, Double retailLb, Double margin) {
		this.id = id;
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = retailLb;
		this.margin = margin;
		this.category = setCategory(cat);
	}
	


	@Override
	public String toString() {
		return "Primal [primal=" + name + ", category=" + category + ", costLb=" + costLb + ", retailLb=" + retailLb
				+ ", margin=" + margin +  "]";
	}

	public String setCategory(String category) {
		
		switch(Category.valueOf(category)) {
		case beef:
			return Category.beef.toString();
			
			
		case pork:
			return Category.pork.toString();
			
			
		case chicken:
			return Category.chicken.toString();
			
			
		case fish:
			return Category.fish.toString();
			
			
		default:
			return Category.unCategorized.toString();
			
		}
		
		
	}

	public String getCategory() {
		return category;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String primal) {
		this.name = primal;
	}

	public Double getCostLb() {
		return costLb;
	}

	public void setCostLb(Double costLb) {
		this.costLb = costLb;
	}

	public Double getRetailLb() {
		return retailLb;
	}
	
	public void setRetailLb() {
		this.retailLb = costLb *.25;
	}

	public void setRetailLb(Double retailLb) {
		this.retailLb = retailLb;
	}

	public Double getMargin() {
		return margin;
	}
	
	public void setMargin() {
		this.margin = formula.profitMargin(costLb, retailLb);
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	
	// start of the primal utility class
	
	private class PrimalDbUtil {

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
		
		//method to delete Primal
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
			myStmt.setDouble(5, thePrimal.getMargin());
			
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
				Double margin = myRs.getDouble("margin");
				
				//create object
				
				thePrimal =new Primal(name, category, costLb, retailLb, margin);
				
				
				
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
			"(name, category, costLb, retailLb, margin) " +
			"values(?,?,?,?,?)";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//Enter values for prepared statement
			
			myStmt.setString(1, thePrimal.getName());
			myStmt.setString(2, thePrimal.getCategory());
			myStmt.setDouble(3, thePrimal.getCostLb());
			myStmt.setDouble(4, thePrimal.getRetailLb());
			myStmt.setDouble(5, thePrimal.getMargin());
			
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
				Double margin = myRs.getDouble("margin");
				
				
				thePrimal = new Primal(name, category, costLb, retailLb, margin);
				
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
					Double margin = myRs.getDouble("margin");
					
					
					thePrimal = new Primal(name, category, costLb, retailLb, margin);
					
					primalList.add(thePrimal);
				}

				return primalList;
				}finally {
					//closing connections
					
					close(myConn, myStmt, myRs);
				}
			}
		
		
	}
}

enum Category{
	beef, pork, chicken, fish, unCategorized;
	
	
}
