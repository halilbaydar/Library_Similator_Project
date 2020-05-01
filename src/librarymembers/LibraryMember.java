package librarymembers;
import java.util.ArrayList;
import books.Book;
public abstract class LibraryMember {
	/**
	 * id id  keeps the id of member
	 * maxNumberofBooks maxNumberofBooks keeps the max book number member can take
	 * timeLimit timeLimit keeps the time limit of book being have to returned
	 * theHistory keeps the state of all books that have been borrowed ever
	 * getTheHistory returns instance of theHistory array 
	 */
	public  int id;
	private int maxNumberofBooks;
	private int timeLimit;
	public ArrayList <Book> theHistory;
	public abstract ArrayList <Book> getTheHistory();
	public LibraryMember() { 
		id=0; //sets initially the id of member as 0 
	}
	public  int getId() {
		return id; 
	}
	public void setId(int id) {
		if(this.id>999999 || this.id<=0) { //controls the id of books
			System.out.println("Wrong id!, please enter less than 6 digit numbers");
			return;}
		else {

		}
		this.id = id;
	}
	public int getMaxNumberofBooks() { 
		return maxNumberofBooks;
	}
	/**
	 * @param maxNumberofBooks
	 */
	public void setMaxNumberofBooks(int maxNumberofBooks) {
		if(this.maxNumberofBooks==0 || this.maxNumberofBooks==getMaxNumberofBooks()) {
			return;
		}else
			this.maxNumberofBooks = maxNumberofBooks;
	}
	public int getTimeLimit(){
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
}
