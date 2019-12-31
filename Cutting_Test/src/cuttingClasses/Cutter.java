package cuttingClasses;

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

}//end cutter class
