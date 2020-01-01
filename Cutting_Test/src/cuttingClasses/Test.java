package cuttingClasses;


public class Test {
	private Cutter cutter;
	private Primal primal;
	private int id;
	private String cutterName;
	private int cutterId;
	private String primalName;
	private int primalId;
	private double weight;
	private double trim;
	private double waste;
	
	
	public Test(Cutter cutter, Primal primal, double weight, double trim, double waste) {
		this.setCutter(cutter);
		this.primal = primal;
		this.cutterName = cutter.getFirstName() + cutter.getLastName();
		this.cutterId = cutter.getId();
		this.primalName = primal.getName();
		this.primalId = primal.getId();
		this.weight = weight;
		this.trim = trim;
		this.waste = waste;
	}
	
	public Test(int id,String cutterName, int cutterId, String primalName, int primalId, double weight, double trim, double waste) {
		this.id = id;
		this.cutterName = cutterName;
		this.cutterId = cutterId;
		this.primalName = primalName;
		this.primalId = primalId;
		this.weight = weight;
		this.trim = trim;
		this.waste = waste;
		
	}
	
	

	@Override
	public String toString() {
		return "Test [id=" + id + ", cutterName=" + cutterName + ", cutterId=" + cutterId + ", primalName=" + primalName
				+ ", primalId=" + primalId + ", weight=" + weight + ", trim=" + trim + ", waste=" + waste + "]";
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void setId(int id) {
		this.id = id;
	}

	public synchronized String getCutterName() {
		return cutterName;
	}

	public synchronized void setCutterName(String cutterName) {
		this.cutterName = cutterName;
	}

	public synchronized int getCutterId() {
		return cutterId;
	}

	public synchronized void setCutterId(int cutterId) {
		this.cutterId = cutterId;
	}

	public synchronized String getPrimalName() {
		return primalName;
	}

	public synchronized void setPrimalName(String primalName) {
		this.primalName = primalName;
	}

	public synchronized int getPrimalId() {
		return primalId;
	}

	public synchronized void setPrimalId(int primalId) {
		this.primalId = primalId;
	}

	public synchronized double getWeight() {
		return weight;
	}

	public synchronized void setWeight(double weight) {
		this.weight = weight;
	}

	public synchronized double getTrim() {
		return trim;
	}

	public synchronized void setTrim(double trim) {
		this.trim = trim;
	}

	public synchronized double getWaste() {
		return waste;
	}

	public synchronized void setWaste(double waste) {
		this.waste = waste;
	}

	public Cutter getCutter() {
		return cutter;
	}

	public void setCutter(Cutter cutter) {
		this.cutter = cutter;
	}

	public synchronized Primal getPrimal() {
		return primal;
	}

	public synchronized void setPrimal(Primal primal) {
		this.primal = primal;
	}
	
	

}
