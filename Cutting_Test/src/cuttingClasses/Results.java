package cuttingClasses;

public class Results extends Formulas{
	private int resultId;
	private Test test;
	int testId;
	double wholesaleCost;
	double profitMargin;
	double yield;
	double epWeight;
	double epCost;
	double epRetail;
	double valueOfUsableProduct;
	double costOfUsableProduct;
	
	public Results() {
		
	}
	
	public Results(int resultId, Test test) {
		getResults();
		
	}
	
	public Results(Test test) {
		
		getResults();
	}
	
	public Results(int resultId, int testId, double wholesaleCost, double margin,
			 double epWeight, double epCost, double yield, double epRetail, 
			double valueUsableProduct, double costUsableProduct  ) {
		
		this.resultId = resultId;
		this.testId = testId;
		this.wholesaleCost =  wholesaleCost;
		this.profitMargin = margin;
		this.epWeight = epWeight;
		this.epCost = epCost;
		this.yield = yield;
		this.epRetail = epRetail;
		this.valueOfUsableProduct = valueUsableProduct;
		this.costOfUsableProduct = costUsableProduct;
		
	}
	
	public Results(int testId, double wholesaleCost, double margin, 
			 double epWeight, double epCost, double yield, double epRetail, 
			double valueUsableProduct, double costUsableProduct  ) {
		
		this.testId = testId;
		this.wholesaleCost =  wholesaleCost;
		this.profitMargin = margin;
		this.epWeight = epWeight;
		this.epCost = epCost;
		this.yield = yield;
		this.epRetail = epRetail;
		this.valueOfUsableProduct = valueUsableProduct;
		this.costOfUsableProduct = costUsableProduct;
		
	}
	
	public void getResults() {
		wholesaleCost();
		 profitMargin();
		 epWeight();
		 epCost();
		 epRetail();
		 yieldTest();
		 valueOfUsableProduct();
		 costOfUsableProduct();
		 
	}

	public double wholesaleCost() {
		double wholesaleCost = super.wholesaleCost(test.getWeight(), test.getPrimal().getCostLb());
		this.setWholesaleCost(wholesaleCost);
		return wholesaleCost;
	}

	
	public double profitMargin() {
		double profitMargin = super.profitMargin(test.getPrimal().getCostLb(), test.getPrimal().getRetailLb());
		this.setProfitMargin(profitMargin);
		return profitMargin ;
	}
	
	public double epWeight() {
		double epWeight = super.epWeight(test.getWeight(), test.getTrim());
		this.setEpWeight(epWeight);
		return epWeight ;
	}

	
	public double epCost() {
		double epCost =  super.epCost(this.epWeight, this.yield);
		this.setEpCost(epCost);
		return epCost;
	}
		
	
	public double yieldTest() {	
		double yield = super.yieldTest(this.epWeight, test.getWeight());
		this.setYield( yield);
		return yield;
	}
	

	
	public double epRetail() {
		double epRetail = super.epRetail(this.epWeight, test.getPrimal().getRetailLb());
		this.setEpRetail(epRetail);
		return epRetail;
				
	}

	
	public double valueOfUsableProduct() {
		double vup = super.valueOfUsableProduct(getWholesaleCost(), test.getWaste(), test.getPrimal().getCostLb());
		this.setValueOfUsableProduct(vup);
		return vup;
	}

	
	public double costOfUsableProduct() {
		double cup =  super.costOfUsableProduct(getEpRetail(), getEpWeight());
		this.setCostOfUsableProduct(cup);
		return cup;
	}

	@Override
	public String toString() {
		return "Results [resultId=" + resultId + ", test=" + test + ", testId=" + testId + ", profitMargin="
				+ profitMargin + ", wholesaleCost=" + wholesaleCost + ", yield=" + yield + ", epWeight=" + epWeight
				+ ", epCost=" + epCost + ", epRetail=" + epRetail + ", valueOfUsableProduct=" + valueOfUsableProduct
				+ ", costOfUsableProduct=" + costOfUsableProduct + "]";
	}
	

	public synchronized int getResultId() {
		return resultId;
	}

	public synchronized void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public synchronized Test getTest() {
		return test;
	}

	public synchronized void setTest(Test test) {
		this.test = test;
	}

	public synchronized int getTestId() {
		return testId;
	}

	public synchronized void setTestId(int testId) {
		this.testId = testId;
	}
	
	public synchronized double getWholesaleCost() {
		return wholesaleCost;
	}

	public synchronized void setWholesaleCost(double wholesaleCost) {
		this.wholesaleCost = wholesaleCost;
	}

	public synchronized double getProfitMargin() {
		return profitMargin;
	}

	public synchronized void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}

	
	public synchronized double getYield() {
		return yield;
	}

	public synchronized void setYield(double yield) {
		this.yield = yield;
	}

	public synchronized double getEpWeight() {
		return epWeight;
	}

	public synchronized void setEpWeight(double epWeight) {
		this.epWeight = epWeight;
	}

	public synchronized double getEpCost() {
		return epCost;
	}

	public synchronized void setEpCost(double epCost) {
		this.epCost = epCost;
	}

	public synchronized double getEpRetail() {
		return epRetail;
	}

	public synchronized void setEpRetail(double epRetail) {
		this.epRetail = epRetail;
	}

	public synchronized double getValueOfUsableProduct() {
		return valueOfUsableProduct;
	}

	public synchronized void setValueOfUsableProduct(double valueOfUsableProduct) {
		this.valueOfUsableProduct = valueOfUsableProduct;
	}

	public synchronized double getCostOfUsableProduct() {
		return costOfUsableProduct;
	}

	public synchronized void setCostOfUsableProduct(double costOfUsableProduct) {
		this.costOfUsableProduct = costOfUsableProduct;
	}
	
	
	

}
