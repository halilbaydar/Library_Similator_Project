package books;
import librarymembers.LibraryMember;
public abstract class Book {
	/**
	 * @param author Halil BAYDAR
	 * @param bookID keeps id of book object
	 * @param bookType keeps type of book object
	 * @param isTaken returns true if book is borrowed
	 * @param whoHas keeps information of who borrow book
	 * @param deadLine keeps date which book has to be returned 
	 * @param isExtended returns true if the deadline of book is already extended
	 */
	private int bookID;
	private String bookType;
	private boolean isTaken;
	private LibraryMember whoHas;
	private boolean isExtended;
	public int limitbook=0;
	/**
	 * @param bookID the id of book object initializes at 1
	 */
	public Book() {
		bookID=0;
	}
	public Book(int bookID) {
		this.bookID=bookID;
	}
	/**
	 * creates the features of book object such as id and type
	 * @param bookID returns the id of book
	 * @param bookType returns the type of book
	 */
	public Book(int bookID, String bookType) { //
		this.bookID=bookID;
		this.bookType=bookType;
	}
	/**
	 * creates method without any implementation ,which subclasses can use
	 * @param member
	 */
	public abstract void returnBook(LibraryMember member);
	/**
	 *supplies access to the id of book object
	 * @return
	 */
	public int getBookID(){  
		return bookID;
	}
	/**
	 * determines max limit the id can get
	 * @param bookID sets bookID less than 6 digid number
	 */
	public void setBookID(int bookID) {  
		if(bookID>999999) {
			System.out.println("Wrong ID!");
		return;
		}
		else {
			this.bookID = bookID;
			}
	}
	/**
	 * 
	 * @return  provides access to type of book object
	 */
	public String getBookType() {
		return bookType;
	}
	/**
	 * 
	 * @return returns isTaken variable
	 */
	public boolean getIsTaken() { 
		return isTaken;
	}
	/**
	 * 
	 * @param isTaken sets the value of isTaken
	 */
	public void setIsTaken(boolean isTaken) { 
		this.isTaken = isTaken;
	}
	/**
	 * 
	 * @return provide access to whoHas variable
	 */
	public LibraryMember getWhoHas() { 
		return whoHas;
	}
	/**
	 * 
	 * @param whoHas sets the value of whoHas
	 */
	public void setWhoHas(LibraryMember whoHas) { 
		this.whoHas = whoHas;
	}
	/**
	 * 
	 * @return provides access to isExtended variable
	 */
	public boolean getIsExtended() {  
		return isExtended;
	}
	/**
	 * 
	 * @param isExtended provides way to sets the value of isExtended
	 */
	public void setIsExtended(boolean isExtended) {  
		this.isExtended=isExtended;
	}
}
