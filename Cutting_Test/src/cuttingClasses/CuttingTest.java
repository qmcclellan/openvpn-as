package cuttingClasses;

import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class CuttingTest{
	int id;
	Formulas formula;
	Primal primal;
	double weight;
	double trim;
	Date date;
	
	double profitMargin;
	double wholesaleCost;
	double yield;
	double epWeight;
	double epCost;
	double epRetail;
	double valueOfUsableProduct;
	double costOfUsableProduct;
	
	public CuttingTest() {
		
	}
	
	public CuttingTest(Primal primal,double weight, double trim) {
		 this.primal = primal;
		 this.weight = weight;
		 this.trim = trim;
		 this.date = new Date();
	}
	

	
	public void profitMargin(Primal primal) {
		double pMargin = formula.profitMargin(primal.getCostLb(), primal.getRetailLb());
		this.profitMargin = pMargin;
	}

	public void wholesaleCost(Primal primal, double weight) {
		double wholeCost = formula.wholesaleCost(primal.getCostLb(), weight);
		this.wholesaleCost = wholeCost;
	}

	
	public void yieldTest(double epWeight, double weight) {
		double yield = formula.yieldTest(epWeight, weight);
		this.yield = yield;
	}

	public void epWeight(double weight, double trim) {
		double epWeight = formula.epWeight(weight, trim);
		this.epWeight = epWeight;
	}

	public void epCost(double epWeight, double yield) {
		double epCost = formula.epCost(epWeight, yield);
		this.epCost = epCost;
	}

	
	public void epRetail(double epWeight, Primal primal) {
		double epRetail = formula.epRetail(epWeight, primal.getRetailLb());
		this.epRetail = epRetail;
	}

	public void valueOfUsableProduct(double apCost, double wasteWeight,  double costLb) {
		double vup = formula.valueOfUsableProduct(apCost, wasteWeight, costLb);
		this.valueOfUsableProduct = vup;
	}

	public void costOfUsableProduct(double epRetail, double epWeight) {
		double cup = formula.costOfUsableProduct(epRetail, epWeight);
		this.costOfUsableProduct = cup;
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
// inner class for database utility


	 public class TestDbUtil {

		DataSource dataSource;
		
	public TestDbUtil(DataSource theDataSource) {
			
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

	public List<CuttingTest> getTestList(){
		List<CuttingTest> testList = new ArrayList<>();
		
		return testList;
	}

	public CuttingTest getTest(String testId) {
		CuttingTest test = new CuttingTest();//delete no arg constructor
		
		return test;
	}

	public void addTest(CuttingTest test) {
		
	}

	public void updateTest(CuttingTest cuttingTest) {
		
	}

	public void deleteTest(String testId) {
		
	}

	 }







	
	
	
	
	
	
}
