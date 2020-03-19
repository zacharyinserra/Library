
public class Loan { // manages loans of books to patrons
	
	public String bookId;
	public String patronId;
	public String dueDate;
	public boolean available;
	
	public Loan(String bookId, String patronId, String dueDate) { //add issue date, due date?
		this.bookId = bookId;
		this.patronId = patronId;
		this.dueDate = dueDate;
		this.available = false;
	}
	
	public Loan(String bookId, String patronId) {
		this.bookId = bookId;
		this.patronId = patronId;
		this.dueDate = "2017-10-3"; // 2 weeks away?
		this.available = false;
	}

	public String toString() {
		return (patronId + ", " + bookId + ", " + dueDate + "\n");
	}
	
	public boolean getAvailability() {
		return available;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public String getPatronId() {
		return patronId;
	}
		
	public String getDueDate() {
		return dueDate;
	}
}
