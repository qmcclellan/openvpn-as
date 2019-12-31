package cuttingClasses;

public class Primal {
	
	private int id;
	private String name;
	private String category;
	private Double costLb;
	private Double retailLb;
	
	

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



	public Primal(String primal, String cat, Double costLb, Double retailLb) {
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = retailLb;
		this.category = setCategory(cat);
	}
	
	public Primal(int id, String primal, String cat, Double costLb, Double retailLb) {
		this.id = id;
		this.name = primal;
		this.costLb = costLb;
		this.retailLb = retailLb;
		this.category = setCategory(cat);
	}
	


	public Primal(String name, String category) {
		this.name = name;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Primal [primal=" + name + ", category=" + category + ", costLb=" + costLb + ", retailLb=" + retailLb
				+   "]";
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


}

enum Category{
	beef, pork, chicken, fish, unCategorized;
	
	
}
