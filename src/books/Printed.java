package books;
import interfaces.Borrow;
import interfaces.ReadInLibrary;
import librarymembers.LibraryMember;
/**
 * extends Book
 * implements ReadInLibrary and Borrow
 * @param limitbook is used to exchange with the MaxNumberofBooks of member
 */
public class Printed extends Book implements ReadInLibrary, Borrow{
	private int deadLine;
	/**
	 * 
	 * @param bookID
	 * returns bookID coming from parent class
	 * sets deadline of book as initially 0
	 * sets isExtended initially as false
	 * sets whoHas initially as  null
	 */
	public Printed(int bookID) {
		super(bookID); 
		this.setDeadLine(0); 
		this.setIsExtended(false); 
		this.setWhoHas(null); 
		this.setIsTaken(false);
	}
	/**
	 * 
	 * @param bookID
	 * @param bookType
	 * returns bookID and bookType coming from parent class
	 * sets deadline of book as initially 0
	 * sets isExtended initially as false
	 * sets whoHas initially as  null
	 */
	public Printed(int bookID, String bookType) {
		super(bookID, bookType); 
		this.setDeadLine(0);  
		this.setIsExtended(false);  
		this.setWhoHas(null); 
		this.setIsTaken(false);
	}
	/**
	 * (non-Javadoc)
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 * @member returns certain member object
	 * sets as true because book is borrowed
	 * assigns the owner of book
	 */
	@Override
	public void readBook(LibraryMember member) {
		this.setIsTaken(true); 
		this.setWhoHas(member);  
		this.setIsTaken(true);
	}
	/**
	 * @param member gets member object as a parameter
	 *@param tick gets tick value as a parameter
	 *
	 */
	@Override
	public void borrowBook(LibraryMember member, int tick) {
		this.setDeadLine(tick+ member.getTimeLimit());
		this.setWhoHas(member);
		this.setIsExtended(false);
		this.limitbook++;
		this.setIsTaken(true);
	}
	/**
	 * @param member gets member object as a parameter
	 * @param tick gets tick value as a parameter
	 * sets new deadline
	 * sets the time extended of book as true
	 */
	@Override
	public void extend(LibraryMember member, int tick) {
		this.setDeadLine(this.getDeadLine()+member.getTimeLimit()); 
		this.setIsExtended(true);  
	}
	/**
	 * (non-Javadoc)
	 * @see books.Book#returnBook(librarymembers.LibraryMember)
	 * @param member gets member object as parameter
	 * to return book, sets isTaken as false
	 * sets the owner of book as null to return book
	 * to return, the MaxNumberofBooks of member is up one
	 * sets new limit of the number of book
	 */
	@Override
	public void returnBook(LibraryMember member) {
		this.setIsTaken(false); 
		this.setWhoHas(null);
		this.limitbook--;
		this.setIsTaken(false);
	}
	public int getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}
}