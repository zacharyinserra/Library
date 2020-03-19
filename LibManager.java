import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;

public class LibManager {
	private ArrayList<Book> bookList = new ArrayList<Book>();
	private ArrayList<Patron> patronList = new ArrayList<Patron>();
	private ArrayList<Loan> loanList = new ArrayList<Loan>();
	private String[] menuOptions;

	public static void main(String[] args) {
		LibManager lm = new LibManager();
		lm.execute();
	}

public LibManager() {
		bookList = readBooks("Resources/books.txt");
		patronList = readPatrons("Resources/patrons.txt");
		loanList = readLoans("Resources/loans.txt");

		menuOptions = new String[] { "Add Book", "Add Patron", "List Books", "List Patrons", "List By Author",
				"List By Year", "Lend Book", "Return Book", "Show Borrower", "Show Borrowed Books", "Remove Book", "Remove Patron",
				"Show Overdue Books", "Exit" };
	}

	private void execute() {

		while (true) {
			int opt = getMenuOption();
			switch (opt) {
			case 1:
				addBook();
				break;
			case 2:
				addPatron();
				break;
			case 3:
				listBooks();
				break;
			case 4:
				listPatrons();
				break;
			case 5:
				listByAuthor();
				break;
			case 6:
				listByYear();
				break;
			case 7:
				lendBookToPatron();
				break;
			case 8:
				returnBook();
				break;
			case 9:
				showBorrowers();
				break;
			case 10:
				showBorrowedBooks();
				break;
			case 11:
				removeBook();
				break;
			case 12:
				removePatron();
				break;
			case 13:
				showOverdueBooks();
				break;
			case 14:
				exitProgram();
				break;
			default:
				System.out.println("No such option");
			}
		}

	}

	private int getMenuOption() {

		System.out.println("Select one of the following options");
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + menuOptions[i]);
		}

		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();

		return choice;
	}

	/* MAKE NO CHANGES ABOVE THIS LINE */
	/* COMPLETE ALL THE CODE STUBS BELOW */

	private void exitProgram() {
		System.out.println("Saving..");
		//save();
		System.out.println("Exiting..");
		System.exit(0);
	}
	
	private void save() {
		try {
			FileWriter blfw = new FileWriter("Resources/books.txt");
			BufferedWriter blbw = new BufferedWriter(blfw);
			blbw.write(bookList.toString().replaceAll(",", System.getProperty("line.separator")));
			
			FileWriter plfw = new FileWriter("Resources/patrons.txt");
			BufferedWriter plbw = new BufferedWriter(plfw);
			plbw.write(patronList.toString().replaceAll(",", System.getProperty("line.separator")));
				
			FileWriter llfw = new FileWriter("Resources/loans.txt");
			BufferedWriter llbw = new BufferedWriter(llfw);
			llbw.write(loanList.toString().replaceAll(",", System.getProperty("line.separator")));

			blbw.flush();
			plbw.flush();
			llbw.flush();
			blbw.close();
			plbw.close();
			llbw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private ArrayList<Book> readBooks(String filename) { //reads books.txt to bookList
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] stuff = line.trim().split(";");
				String bookId = stuff[0].trim();
				String title = stuff[1].trim();
				String author = stuff[2].trim();
				int year = Integer.parseInt(stuff[3].trim());
				Book b = new Book(bookId, author, title, year);
				bookList.add(b);
			}
		} catch (IOException e) {
			e.printStackTrace();		
		}
		return bookList;
	}

	private ArrayList<Patron> readPatrons(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] stuff = line.split("\\s+");
				String patronId = stuff[0].trim();
				String name = stuff[1].trim();
				Patron p = new Patron(patronId, name);
				patronList.add(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return patronList;
	}

	private ArrayList<Loan> readLoans(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] stuff = line.split(",");
				String bookId = stuff[0].trim();
				String patronId = stuff[1].trim();
				String dueDate = stuff[2].trim();
				Loan l = new Loan(bookId, patronId, dueDate);
				loanList.add(l);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loanList;
	}

	private Book locateBook(String bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	private Patron locatePatron(String patronId) {
		// TODO Auto-generated method stub
		return null;
	}

	private void showBorrowedBooks() { //shows borrowed books by patron
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a patron ID: ");
		String patronId = s.nextLine();
		
		for (int i = 0; i < loanList.size(); i++) {
			if (patronId.equals(loanList.get(i).getPatronId())) {
				System.out.println(bookList.get(i));
			}
		}
		
		//System.out.println(loanList.toString().replaceAll(",",""));
	}

	private void showBorrowers() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a book ID: ");
		String bookId = s.nextLine();
		
		for (int i = 0; i < loanList.size(); i++) {
			if (bookId.equals(loanList.get(i).getBookId())) { //if book IDs equal
				String patronID = loanList.get(i).getPatronId(); //sets patron ID
				if (patronID.equals(patronList.get(i).getPatronId())) { //if patron IDs equal
					System.out.println(patronList.get(i).getPatronName()); // prints PAtron name
				}
			}
		}		
	}

	private void showOverdueBooks() { // split loan, get duedate, do math
		int currentDay = 19;
		for (int i = 0; i < loanList.size(); i++) {
			String dueDate[] = loanList.get(i).getDueDate().split("-");
			//String year = dueDate[0].trim();
			int month = Integer.parseInt(dueDate[1]);
			int day = Integer.parseInt(dueDate[2]);
			String bookID = loanList.get(i).getBookId();
			if (month < 9) { //if a month ago auto overdue				
				for (int j = 0; j < bookList.size(); j++) {
					if (bookID.equals(bookList.get(j).getBookId())) {
						System.out.println(bookList.get(j));
					}
				}
			}
			else if (month == 9 && day < currentDay) { // if this month check if day is less than current day
				for (int j = 0; j < bookList.size(); j++) { // if less overdue
					if (bookID.equals(bookList.get(j).getBookId())) {
						System.out.println(bookList.get(j));
					}
				}	
			}
		}
	}

	private void lendBookToPatron() { // creates new loan for book and patron, sets dueDate 2 weeks away
		Scanner s = new Scanner(System.in);
		System.out.println("Enter patron ID: ");
		String patronId = s.nextLine();
		System.out.println("Enter book ID: ");
		String bookId = s.nextLine();
		
		Loan l = new Loan(bookId, patronId); 
		for (int j = 0; j < loanList.size(); j++) { // checks if book is already on loan
			if (bookId.equals(loanList.get(j).getBookId())) {
				System.out.println("Book is already loaned");
				break;
			}
		}
		for (int i = 0; i < bookList.size(); i++) { //finds book and adds it to loan list
			if (bookId.equals(bookList.get(i).getBookId())) {
				loanList.add(l);			
			}
		}
	}		

	private void returnBook() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a book ID: ");
		String bookId = s.nextLine();
		
		for (int i = 0; i < loanList.size(); i++) {
			if (bookId.equals(loanList.get(i).getBookId())) {
				loanList.remove(loanList.get(i));
			}
		}
	}

	private void listByYear() { //searches for publication year range, if not found returns "no matches"
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a year of publication max: ");
		int searchYearMax = s.nextInt();
		System.out.println("Enter a year of publication min: ");
		int searchYearMin = s.nextInt();
		
		int j = 0;
		for (int i = 0; i < bookList.size(); i++) {
			int x = bookList.get(i).getYear();
			if (searchYearMax >= x && searchYearMin <= x) {
				System.out.println(bookList.get(i));
				j++;
			}
			if (i == (bookList.size() - 1) && j == 0) { System.out.println("No matches"); }
		}
	}

	private void listByAuthor() { //searches for author, if not found returns "no matches"
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name of an author: ");
		String searchAuthor = s.nextLine();
		
		int j = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (searchAuthor.equalsIgnoreCase(bookList.get(i).getAuthor())) {
				System.out.println(bookList.get(i));
				j++;
			}
			if (i == (bookList.size() - 1) && j == 0) { System.out.println("No matches"); }
		}
	}

	private void listPatrons() {
		System.out.println(patronList.toString().replaceAll(",", ""));
	}
	
	private void listBooks() { // returns a string of all books in bookList
		System.out.println(bookList.toString().replaceAll(",",""));	
	}

	private void addPatron() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter name of new patron: ");
		String name = s.nextLine();
		Patron p = new Patron(name);
		patronList.add(p);		
	}
	
	private void addBook() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter book author: ");
		String author = s.nextLine();
		System.out.println("Enter book title: ");
		String title = s.nextLine();
		System.out.println("Enter year of publication: ");
		int year = s.nextInt();
		Book b = new Book(author, title, year);
		bookList.add(b);		
	}

	private void removePatron() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a patron ID to remove book: ");
		String patronId = s.nextLine();
		
		for (int i = 0; i < patronList.size(); i++) {
			if (patronId.equals(patronList.get(i).getPatronId())) {
				patronList.remove(patronList.get(i));
			}
		}
	}

	private void removeBook() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a book ID to remove book: ");
		String bookId = s.nextLine();
		
		for (int i = 0; i < bookList.size(); i++) {
			if (bookId.equals(bookList.get(i).getBookId())) {
				bookList.remove(bookList.get(i));
			}
		}	
	}

}