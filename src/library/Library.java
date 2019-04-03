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
 *@totalFee returns fine because of time-out
 *@books keeps the history of books in library
 *@members keeps the history of the member of the library
 *@tick1 is used to call unit of time in LibrarySimulator
 *@H is used to check whether book is handwritten or not
 *@P is used to check whether book is printed or not
 *@task reads which process is processed
 *@param bookid reads which book is wanted to borrow
 *@param memberid reads which member wants to borrow book
 *@param typeof reads which book or member is wanted to add to the books array or the members array
 *@param bookindex is used to determine in which index to add books to the books array
 *@param memberindex is used to determine in which index to add members to the members array
 */
public class Library{
	public int totalFee;
	private Book[] books;
	private LibraryMember[] members;
	int task,bookid,memberid;
	String typeof;
	int bookindex, memberindex;
	private Scanner scanner;
	public Library(Scanner scanner) {
		this.bookindex=1; //it begins from index 1 due to the description
		this.memberindex=1; //it begins from index 1 due to the description
		books=new Book[(int) 10e6]; //it's size composed of 10e6 due to the description
		members=new LibraryMember[(int) 10e6]; //it's size composed of 10e6 due to the description
		this.scanner=scanner;
	}
	/**
	 * if this variable false, 85.line examines type of book
	 *@param returnbook method is called through printed object in Printed.java according to the type of book 
	 *compares tick with deadline
	 * 55.line calls totalFee method to calculate the penalty of member
	 * 52. line controls the owner of book
	 * 56. line calls returnBook to return book to library
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
						System.out.println("return yapildi");
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
	 *  rise up index of book to make space for next book
	 */
	public void addBook() {
		this.typeof=scanner.next();		
		if(typeof.equals("H")) {
			books[bookindex]=new Handwritten(bookindex);
			bookindex++;	
			System.out.println("kitap eklendi");
		}else if(typeof.equals("P")) {
			books[bookindex]= new Printed(bookindex);
			bookindex++;
			System.out.println("kitap eklendi");
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
			System.out.println("member eklendi");
		}
		else if(typeof.equals("S")) {
			members[memberindex]=new Student(memberindex);
			memberindex++;
			System.out.println("member eklendi");
		}
	}
	/**
	 * creates printed object to call methods in printed.java
	 *@param isisavailable is called to check whether book is in library or not
	 *@param checkmax controls time of and book limit of member 
	 *after necessary checks validity of books, calls responsible methods in printed.java 
	 *line 123 controls whether theHistory array is null or not
	 *line 124 scans theHistory array 
	 *line 127 adds book to this array
	 *line 124 if this book was not added before, adds book to this array
	 *line 129 controls member is student or not
	 *line 133 controls array to get it is null or not
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
						System.out.println("borrow edildi");
						if(members[memberid].theHistory!=null) {
							if( members[memberid].theHistory.contains((Book)books[bookid])){
								return;
							}else {
								members[memberid].theHistory.add(books[bookid]); 
							}
						}
					}else if(members[memberid] instanceof Student) {
						((Printed) books[bookid]).borrowBook(members[memberid], tick);
						System.out.println("borrow edildi");
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
	 * @param isextendible sends boolean value according to validity of extendible limit
	 * controls whether this book time was already extended or not 
	 * checks the deadline of book
	 * according to case, totalFee method works
	 * @param checkmax returns value true or false according to case
	 * line 165 controls the info books to understand it is extended before or not
	 * line 166 calls responsible method to extend the deadline of book
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
				System.out.println("extend edildi");
				}
			}else
				return;
		}
	}
	/**if the type of book is Handwritten, this method is called
	 *@param isisavailable is called to check whether book is in library or not
	 *186 and 187. line checks type of books and type of members
	 *@param readBook method is called through printed object in Handwritten.java according to the type of book
	 *line 1888 calls read book to implements book features 
	 *190.line controls whether this array null or not
	 *191.line scans theHistory array 
	 *194. line adds book to this array
	 */
	public void readInLibrary() {
		this.bookid=scanner.nextInt();
		this.memberid=scanner.nextInt();
		if(books[bookid].getWhoHas()==null) {
			if(books[bookid] instanceof Handwritten) {
				if(members[memberid] instanceof Academic) { 
					((Handwritten) books[bookid]).readBook(members[memberid]); 
					System.out.println("read yapildi");
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
	 * 221. line compares tick with deadline
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
	 * @param totalFee keeps the sum of fine to be paid for each member 
	 * @param member gets member object as a parameter
	 */
	public int totalFee(LibraryMember member, int tick, int bookid ) {
		this.bookid=bookid;
		return totalFee+= tick - ((Printed) books[bookid]).getDeadLine(); 
	}
	/**
	 * provides access for designer to books array
	 */
	public Book[] getBooks() {
		return books;
	}
}