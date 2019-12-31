package cuttingClasses;



public class CuttingTest{
	
	Formulas formula;
	Primal primal;
	Test test;
	
	int id;
	int primalId;
	int testId;
	double wholesaleCost;
	double profitMargin;
	double yield;
	double epWeight;
	double epCost;
	double epRetail;
	double valueOfUsableProduct;
	double costOfUsableProduct; 
	
	//no arg constructor for database initialization
	public CuttingTest() {
		
	}
	
	public CuttingTest(Test test,Primal primal) {
		 this.test = test;
		 this.primal = primal;
		 getResults();
	}
	
	public CuttingTest(int primalId, double weight, double trim, double wholesaleCost, double profitMargin,
			 double yield, double epWeight, double epCost, double epRetail,
			double valueOfUsableProduct, double costOfUsableProduct) {
		
		this.primalId = primalId;
		this.wholesaleCost = wholesaleCost;
		this.profitMargin = profitMargin;
		this.yield = yield;
		this.epWeight = epWeight;
		this.epCost = epCost;
		this.epRetail = epRetail;
		this.valueOfUsableProduct = valueOfUsableProduct;
		this.costOfUsableProduct = costOfUsableProduct;
	}
	
	//constructor needed for database
	public CuttingTest(int id, int primalId, double weight, double trim,double wholesaleCost, double profitMargin,
			 double yield, double epWeight, double epCost, double epRetail,
			double valueOfUsableProduct, double costOfUsableProduct) {
		
		this.id = id;
		this.primalId = primalId;
		this.wholesaleCost = wholesaleCost;
		this.profitMargin = profitMargin;
		this.yield = yield;
		this.epWeight = epWeight;
		this.epCost = epCost;
		this.epRetail = epRetail;
		this.valueOfUsableProduct = valueOfUsableProduct;
		this.costOfUsableProduct = costOfUsableProduct;
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
		double pMargin = formula.profitMargin(primal.getCostLb(), primal.getRetailLb());
		this.profitMargin = pMargin;
	}

	public void wholesaleCost() {
		double wholeCost = formula.wholesaleCost(primal.getCostLb(), test.getWeight());
		this.wholesaleCost = wholeCost;
	}

	
	public void yieldTest() {
		double yield = formula.yieldTest(epWeight, test.getWeight());
		this.yield = yield;
	}

	public void epWeight() {
		double epWeight = formula.epWeight(test.getWeight(), test.getTrim());
		this.epWeight = epWeight;
	}

	public void epCost() {
		double epCost = formula.epCost(epWeight, yield);
		this.epCost = epCost;
	}

	
	public void epRetail() {
		double epRetail = formula.epRetail(epWeight, primal.getRetailLb());
		this.epRetail = epRetail;
	}

	public void valueOfUsableProduct() {
		double vup = formula.valueOfUsableProduct(this.wholesaleCost, test.getTrim(), primal.getCostLb());
		this.valueOfUsableProduct = vup;
	}

	public void costOfUsableProduct() {
		double cup = formula.costOfUsableProduct(this.epRetail, this.epWeight);
		this.costOfUsableProduct = cup;
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

	

}
