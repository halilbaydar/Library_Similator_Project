package librarymembers;
import java.util.ArrayList;
import books.Book;
public class Student extends LibraryMember{
	/**
	 * @param id returns id of member
	 */
	public Student(int id){
		this.id=id;
		this.setMaxNumberofBooks(10); //sets the MaxNumberofBooks of member
		this.setTimeLimit(20); //sets the TimeLimit  of member's book
	}
	/**
	 * (non-Javadoc)
	 * @see librarymembers.LibraryMember#getTheHistory()
	 * returns thHistory abstract array
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		// TODO Auto-generated method stub
		return this.theHistory;
	}
}