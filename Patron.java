
public class Patron {
	
	public String name;
	public String patronId;
	static int counter = 1000;
	
	public Patron(String name) {
		this.name = name;
		this.patronId = setPatronId();
	}
	
	public Patron(String patronId, String name) {
		this.patronId = patronId;
		this.name = name;
	}
	
	public String toString() {
		return (patronId + "   " + name + "\n");
	}
	
	public String setPatronId() { // sets patron id
		patronId = "P"+counter;
		counter++;
		return patronId;
	}
	
	public String getPatronId() {
		return patronId;
	}
	
	public String getPatronName() {
		return name;
	}
}
