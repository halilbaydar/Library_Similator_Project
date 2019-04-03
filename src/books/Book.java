package books;
import librarymembers.LibraryMember;
public abstract class Book {
	/**
	 * @param bookID keeps id of book object
	 * @param bookType keeps type of book object
	 * @param isTaken returns true if book is borrowed
	 * @param whoHas keeps information of who borrow book
	 * @param deadLine keeps date which book has to be returned 
	 * @param isExtended returns true if the deadline of book is already extended
	 * 
	 */
	private int bookID;
	private String bookType;
	private boolean isTaken;
	private LibraryMember whoHas;
	private boolean isExtended;
	public int limitbook=0;
	public Book() { //the id of book object initializes at 1
		bookID=0;
	}
	public Book(int bookID) {
		this.bookID=bookID;
	}
	public Book(int bookID, String bookType) { //creates the features of book object such as id and type
		this.bookID=bookID;
		this.bookType=bookType;
	}
	public abstract void returnBook(LibraryMember member); // creates method without any implementation ,which subclasses can use
	public int getBookID(){ // supply access to the id of book object
		return bookID;
	}
	public void setBookID(int bookID) { // determines max limit the id can get
		if(bookID>999999) {
			System.out.println("Wrong ID!");
		return;
		}
		else {
			this.bookID = bookID;
			}
	}
	public String getBookType() { // provide access to type of book object
		return bookType;
	}
	public boolean getIsTaken() { //returns isTaken variable
		return isTaken;
	}
	public void setIsTaken(boolean isTaken) { //sets the value of isTaken
		this.isTaken = isTaken;
	}
	public LibraryMember getWhoHas() { // provide access to whoHas variable
		return whoHas;
	}
	public void setWhoHas(LibraryMember whoHas) { // sets the value of whoHas
		this.whoHas = whoHas;
	}
	
	public boolean getIsExtended() { // provide access to isExtended variable
		return isExtended;
	}
	public void setIsExtended(boolean isExtended) { // provide way to sets the value of isExtended
		this.isExtended=isExtended;
	}
}
