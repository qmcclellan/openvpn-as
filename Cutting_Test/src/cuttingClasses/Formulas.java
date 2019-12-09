package cuttingClasses;

public class Formulas {
	
	
	public double profitMargin(double cost, double retail) {
		double profit = retail - cost;
		
		double profitMargin = profit / cost;
		
		return profitMargin;
	}
	
	double wholesaleCost(double apWeight, double apCost) {
		
		double wholeCost = apWeight* apCost;
		
		return wholeCost;
	}
	
	public double yieldTest(double epWeight, double apWeight) {
		
		double yield = (epWeight/ apWeight) * 100;
		
		return yield;
	}
	
	public double epWeight(double apWeight, double trim) {
		
		double epWeight= apWeight - trim;
		
		return epWeight;
	}
	
	
	public double epCost(double epWeight, double yield) {
		
		double factor = 100/yield;
		
		double epCost = epWeight * factor;
		
		return epCost;
		
	}
	
	public double epRetail(double epWeight, double retail) {
		
		double epRetail = epWeight * retail;
		
		return epRetail;
	}
	
	
	
	public double valueOfUsableProduct(double apCost, double wasteWeight, double costLb) {
		
		double wasteCost =  wasteWeight * costLb ;
		
		double vou=apCost - wasteCost;
		
		return vou;
	}
	
	public double costOfUsableProduct(double epRetail, double epWeight) {
		double cup = epRetail/ epWeight;
		
		return cup;
	}
}
