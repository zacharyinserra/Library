import java.util.ArrayList;

public class Book {
	
	public String author;
	public String title;
	public int year;
	public String bookId;
	static int counter = 1001;
	//public ArrayList<Book> bookList = new ArrayList<Book>();
	
	public Book(String bookId, String author, String title, int year) {
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.year = year;		
	}
	
	public Book(String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
		this.bookId = setBookId();
	}
	
	public String toString() {
		return (bookId + " ; " + title + " ; "  + author + " ; " + year + "\n");
	}
		
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public String setBookId() { // sets book identifier b1, b2, b3, ...
		bookId = "B"+counter;
		counter++;
		return bookId;
	}
}	

