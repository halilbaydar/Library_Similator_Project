package library;
import java.util.Scanner;
import books.Book;
import books.Handwritten;
import books.Printed;
import librarymembers.Academic;
import librarymembers.LibraryMember;
import librarymembers.Student;
/**
 *@author Halil BAYDAR 
 *totalFee returns fine because of time-out
 *books keeps the history of books in library
 *members keeps the history of the member of the library
 *tick1 is used to call unit of time in LibrarySimulator
 *H is used to check whether book is handwritten or not
 *P is used to check whether book is printed or not
 *task reads which process is processed
 *bookid reads which book is wanted to borrow
 *memberid reads which member wants to borrow book
 *typeof reads which book or member is wanted to add to the books array or the members array
 *bookindex is used to determine in which index to add books to the books array
 *memberindex is used to determine in which index to add members to the members array
 */
public class Library{
	public int totalFee;
	private Book[] books;
	private LibraryMember[] members;
	int task,bookid,memberid;
	String typeof;
	int bookindex, memberindex;
	private Scanner scanner;
	/**
	 * @param scanner gets scanner parameter to use in the class
	 */
	public Library(Scanner scanner) {
		this.bookindex=1; //
		this.memberindex=1; //it begins from index 1 due to the description
		books=new Book[(int) 10e6]; //it's size composed of 10e6 due to the description
		members=new LibraryMember[(int) 10e6]; //it's size composed of 10e6 due to the description
		this.scanner=scanner;
	}
	/**
	 * if this variable false, 85.line examines type of book
	 * returnBook method is called through printed object in Printed.java according to the type of book 
	 * @param tick is compared with deadline
	 * 54.line calls totalFee method to calculate the penalty of member
	 * 51. line controls the owner of book
	 * 55. line calls returnBook to return book to library
	 */
	public void returnBook(int tick) {
		this.bookid=scanner.nextInt(); 
		this.memberid=scanner.nextInt();
		if(books[bookid]!=null && members[memberid]!=null && books[bookid].getIsTaken()==true) {
			if(books[bookid].getWhoHas().equals(members[memberid]) ) { 
				if(books[bookid] instanceof Printed) {
					if(tick>((Printed) books[bookid]).getDeadLine()){ 
						totalFee(members[memberid], tick, this.bookid);//calls totalFee method to calculate the penalty of member
						books[bookid].returnBook(members[memberid]); //calls returnBook to return book to library
					}
				}else if(books[bookid] instanceof Handwritten){
					books[bookid].returnBook(members[memberid]);
				}
			}
		}
	}
	/**
	 * examines the type of book to correlate class it belongs to
	 * enter index of book and type via parameter
	 * rise up index of book to make space for next book
	 */
	public void addBook() {
		this.typeof=scanner.next();		
		if(typeof.equals("H")) {
			books[bookindex]=new Handwritten(bookindex);
			bookindex++;	
		}else if(typeof.equals("P")) {
			books[bookindex]= new Printed(bookindex);
			bookindex++;
		}else
			return;
	}
	/**
	 * examines the type of member to correlate class it belongs to
	 * enter index of member and type via parameter
	 * rise up index of member to make space for next book
	 */
	public void addMember() {
		this.typeof=scanner.next();		
		if(typeof.equals("A")) {
			members[memberindex]=new Academic(memberindex);
			memberindex++;
		}
		else if(typeof.equals("S")) {
			members[memberindex]=new Student(memberindex);
			memberindex++;
		}
	}
	/**
	 * creates printed object to call methods in printed.java
	 *@param tick gets as parameter to use in the method
	 *checkmax controls time of and book limit of member 
	 *after necessary checks validity of books, calls responsible methods in printed.java 
	 *line 123 controls whether theHistory array is null or not 
	 *line 126 adds book to this array
	 *line 114 controls member is student or not
	 *line 116 controls array to get it is null or not
	 *line 117 if this book was not added before, adds book to this array
	 *next lines again have same processes
	 */
	public void borrowBook(int tick) {
		this.bookid=scanner.nextInt();
		this.memberid=scanner.nextInt();
		if(members[memberid]!=null && books[bookid] instanceof Printed && books[bookid]!=null) {
			if(books[bookid].getWhoHas()==null) {
				checkmax(bookid, memberid, tick);
				if(checkmax==false) {
					if(members[memberid] instanceof Academic) { 
						((Printed) books[bookid]).borrowBook(members[memberid], tick);
						if(members[memberid].theHistory!=null) {
							if( members[memberid].theHistory.contains((Book)books[bookid])){
								return;
							}else {
								members[memberid].theHistory.add(books[bookid]); 
							}
						}
					}else if(members[memberid] instanceof Student) {
						((Printed) books[bookid]).borrowBook(members[memberid], tick);
						if(members[memberid].theHistory!=null) {
							if( members[memberid].theHistory.contains((Book)books[bookid])){ 
								return;
							}else {
								members[memberid].theHistory.add(books[bookid]); 
							}
						}
					}else
						return;
				}
			}
		}else if(books[bookid] instanceof Handwritten) {
			return;
		} 
	}
	/**
	 * @param tick gets as parameter to use in the method
	 * controls whether this book time was already extended or not 
	 * checks the deadline of book
	 * according to case, totalFee method works
	 * 
	 * line 156 controls the info books to understand it is extended before or not
	 * line 157 calls responsible method to extend the deadline of book
	 */
	public void extendBook(int tick) {
		this.bookid=scanner.nextInt();
		this.memberid=scanner.nextInt();
		if(books[bookid]!=null) {
			if( members[memberid]!=null && books[bookid].getWhoHas()==members[memberid] && books[bookid]!=null) {
				if(tick>((Printed) books[bookid]).getDeadLine()){
					return;
				}else if(books[bookid].getIsExtended()==false && tick<=((Printed) books[bookid]).getDeadLine()) {
					((Printed) books[bookid]).extend(members[memberid], tick);
				}
			}else
				return;
		}
	}
	/**if the type of book is Handwritten, this method is called
	 *
	 *176 and 177. line checks type of books and type of members
	 *readBook method is called through printed object in Handwritten.java according to the type of book
	 *line 178 calls read book to implements book features 
	 *179.line controls whether this array null or not
	 *180.line scans theHistory array 
	 *183.line adds book to this array
	 */
	public void readInLibrary() {
		this.bookid=scanner.nextInt();
		this.memberid=scanner.nextInt();
		if(books[bookid].getWhoHas()==null) {
			if(books[bookid] instanceof Handwritten) {
				if(members[memberid] instanceof Academic) { 
					((Handwritten) books[bookid]).readBook(members[memberid]); 
					if(members[memberid].theHistory!=null){ 
						if(members[memberid].theHistory.contains((Book)books[bookid])){ 
							return;
						}else{
							members[memberid].theHistory.add(books[bookid]); 
						}
					}
				}
				return;
			}else
				return;
		}
	}
	/**
	 * 
	 * @param bookid keeps id of book as a parameter to use in method
	 * @param memberid keeps id of member as a parameter to use in method
	 * @param tick keeps id of member as a parameter to use in method
	 * @param checkmax returns true or false value according to case
	 * lines between 217 and 220 scan books array to find the values of book and members by using for loop
	 * 220. line compares tick with deadline
	 */
	boolean checkmax;
	public void checkmax(int bookid, int memberid , int tick) {
		this.memberid=memberid;
		this.bookid=bookid; 
		if(members[memberid].getMaxNumberofBooks()-books[bookid].limitbook!=0) {
			for(int i=1; i<bookindex; i++) {
				if(!books[i].equals(null)){
					if(books[i].getIsTaken()==true) {
						if(books[i].getWhoHas().equals(members[memberid]) ) {
							if(tick>((Printed) books[i]).getDeadLine()){
								checkmax=true;
							}else
								checkmax=false;
						}
					}
				}else
					return;
			}
		}
	}
	/**
	 * if the deadline was exceeded, member has to pay fine
	 * @param bookid gets book id by using parameter
	 * @param tick  gets as parameter to use in the method
	 * @param member gets member object as a parameter
	 * @return totalFee keeps the sum of fine to be paid for each member 
	 */
	public int totalFee(LibraryMember member, int tick, int bookid ) {
		this.bookid=bookid;
		return totalFee+= tick - ((Printed) books[bookid]).getDeadLine(); 
	}
	/**
	 * provides access for designer to books array
	 * @return books array
	 */
	public Book[] getBooks() {
		return books;
	}
}