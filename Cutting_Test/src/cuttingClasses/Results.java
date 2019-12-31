package cuttingClasses;

public class Results extends Formulas{
	private int testId;
	private Test test;
	private Primal primal;
	double profitMargin;
	double wholesaleCost;
	double yield;
	double epWeight;
	double epCost;
	double epRetail;
	double valueOfUsableProduct;
	double costOfUsableProduct;
	
	
	public Results(double profitMargin, double wholesaleCost, double yield, double epWeight, double epCost,
			double epRetail, double valueOfUsableProduct, double costOfUsableProduct) {
		super();
		this.profitMargin = profitMargin;
		this.wholesaleCost = wholesaleCost;
		this.yield = yield;
		this.epWeight = epWeight;
		this.epCost = epCost;
		this.epRetail = epRetail;
		this.valueOfUsableProduct = valueOfUsableProduct;
		this.costOfUsableProduct = costOfUsableProduct;
	}


	public Results(Test test, double profitMargin, double wholesaleCost, double yield, double epWeight, double epCost,
			double epRetail, double valueOfUsableProduct, double costOfUsableProduct) {
		super();
		this.testId = test.getId();
		this.profitMargin = profitMargin;
		this.wholesaleCost = wholesaleCost;
		this.yield = yield;
		this.epWeight = epWeight;
		this.epCost = epCost;
		this.epRetail = epRetail;
		this.valueOfUsableProduct = valueOfUsableProduct;
		this.costOfUsableProduct = costOfUsableProduct;
	}


	public int getTestId() {
		return testId;
	}


	public void setTestId(int testId) {
		this.testId = testId;
	}


	public double getProfitMargin() {
		return profitMargin;
	}


	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}


	public double getWholesaleCost() {
		return wholesaleCost;
	}


	public void setWholesaleCost(double wholesaleCost) {
		this.wholesaleCost = wholesaleCost;
	}


	public double getYield() {
		return yield;
	}


	public void setYield(double yield) {
		this.yield = yield;
	}


	public double getEpWeight() {
		return epWeight;
	}


	public void setEpWeight(double epWeight) {
		this.epWeight = epWeight;
	}


	public double getEpCost() {
		return epCost;
	}


	public void setEpCost(double epCost) {
		this.epCost = epCost;
	}


	public double getEpRetail() {
		return epRetail;
	}


	public void setEpRetail(double epRetail) {
		this.epRetail = epRetail;
	}


	public double getValueOfUsableProduct() {
		return valueOfUsableProduct;
	}


	public void setValueOfUsableProduct(double valueOfUsableProduct) {
		this.valueOfUsableProduct = valueOfUsableProduct;
	}


	public double getCostOfUsableProduct() {
		return costOfUsableProduct;
	}


	public void setCostOfUsableProduct(double costOfUsableProduct) {
		this.costOfUsableProduct = costOfUsableProduct;
	}


	@Override
	public double profitMargin(double cost, double retail) {
		return profitMargin(cost, retail);
	}

	public void getResults() {
		wholesaleCost();
		 profitMargin();
		 yieldTest();
		 epWeight();
		 epCost();
		 epRetail();
		 valueOfUsableProduct();
		 costOfUsableProduct();
		 
	}
	

	public void profitMargin() {
		double pMargin = profitMargin(primal.getCostLb(), primal.getRetailLb());
		this.profitMargin = pMargin;
	}

	public void wholesaleCost() {
		double wholeCost = wholesaleCost(primal.getCostLb(), test.getWeight());
		this.wholesaleCost = wholeCost;
	}

	
	public void yieldTest() {
		double yield = yieldTest(epWeight, test.getWeight());
		this.yield = yield;
	}

	public void epWeight() {
		double epWeight = epWeight(test.getWeight(), test.getTrim());
		this.epWeight = epWeight;
	}

	public void epCost() {
		double epCost = epCost(epWeight, yield);
		this.epCost = epCost;
	}

	
	public void epRetail() {
		double epRetail = epRetail(epWeight, primal.getRetailLb());
		this.epRetail = epRetail;
	}

	public void valueOfUsableProduct() {
		double vup = valueOfUsableProduct(this.wholesaleCost, test.getTrim(), primal.getCostLb());
		this.valueOfUsableProduct = vup;
	}

	public void costOfUsableProduct() {
		double cup = costOfUsableProduct(this.epRetail, this.epWeight);
		this.costOfUsableProduct = cup;
		
	}
}
