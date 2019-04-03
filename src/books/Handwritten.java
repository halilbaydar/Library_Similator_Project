package books;
import interfaces.ReadInLibrary;
import librarymembers.LibraryMember;
public class Handwritten extends Book implements ReadInLibrary{
	/**
	 *@param bookID returns the id of book object
	 *@param bookType returns the id of book object
	 *@param member returns the id of book object
	 */
	public Handwritten(int bookID) {
		super(bookID);
		this.setIsTaken(false); //sets istaTen as initially false
		this.setWhoHas(null); //sets whoHas as initially null
	} 
	public Handwritten(int bookID, String bookType) {
		super(bookID, bookType);
		this.setIsTaken(false); //sets istaTen as initially false
		this.setWhoHas(null); //sets whoHas as initially null
	}
	/**
	 * (non-Javadoc)
	 * @see interfaces.ReadInLibrary#readBook(librarymembers.LibraryMember)
	 * sets the isTaken value of book as true 
	 * assigns member to the owner of book
	 */
	@Override
	public void readBook(LibraryMember member) {
		this.setIsTaken(true); 
		this.setWhoHas(member); 
	}
	/**
	 * (non-Javadoc)
	 * @see books.Book#returnBook(librarymembers.LibraryMember)
	 * returns member object
	 * sets the isTaken value of book as false
	 * sets the owner of book as null
	 */
	@Override
	public void returnBook(LibraryMember member) {
		this.setIsTaken(false); 
		this.setWhoHas(null); 
	}

}