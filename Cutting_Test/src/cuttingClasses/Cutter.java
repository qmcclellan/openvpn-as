package cuttingClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Cutter  {
int id;
String firstName;
String lastName;
int numOfYears;

public Cutter( String firstName, String lastName) {
	
	this.firstName = firstName;
	this.lastName = lastName;
	
}


public Cutter( int id, String firstName, String lastName) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	
}


public Cutter( String firstName, String lastName, int numOfYears) {
	
	this.firstName = firstName;
	this.lastName = lastName;
	this.numOfYears = numOfYears;
	
}

public Cutter( int id, String firstName,String lastName, int numOfYears) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.numOfYears = numOfYears;
	
}


@Override
public String toString() {
	return "Cutter [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", numOfYears=" + numOfYears
			+ "]";
}


public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}




public String getFirstName() {
	return firstName;
}




public void setFirstName(String firstName) {
	this.firstName = firstName;
}




public String getLastName() {
	return lastName;
}




public void setLastName(String lastName) {
	this.lastName = lastName;
}




public int getNumOfYears() {
	return numOfYears;
}

public void setNumOfYears(int numOfYears) {
	this.numOfYears = numOfYears;
}

private class CutterDbUtil{
	
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
	
	public List<Cutter> getCutterList(Cutter cutter) throws SQLException{
		//create an arrylist to hold our cutters
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
			
			cutter = new Cutter(id, firstName, lastName, numYears);
			
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
	
	public void updateCutter(Cutter cutter) {
		
		
	}
	
	public void deleteCutter(String theCutter) {
		
	}
}//end db class

}//end cutter class
