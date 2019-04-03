package librarymembers;
import java.util.ArrayList;
import books.Book;
public class Academic extends LibraryMember {
	/**
	 * @param id returns the id of member
	 */
	public Academic(int id) {
		this.id=id;
		this.setMaxNumberofBooks(20); //sets the MaxNumberofBooks of member
		this.setTimeLimit(50); //sets the TimeLimit  of member's book
	}
	/**
	 * (non-Javadoc)
	 * @see librarymembers.LibraryMember#getTheHistory()
	 * returns thHistory abstract array
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		return theHistory;
	}
}