package hotelmanagement;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("\n=================================================================");
			System.out.println("* * * * * * * * * * HOTEL MANAGEMENT SYSTEM * * * * * * * * * * * ");
			System.out.println("=================================================================");
			System.out.print("1.Login\n2.Sign up\n3.Admin Login\n4.exit\nEnter your Choice: ");
			int choice=input.nextInt();
			System.out.println("*****************************************************************\n");
			switch(choice) {
				case 1:login();break;
				case 2:signup();break;
				case 3:adminlogin();break;
				case 4:flag=false;break;
				default:System.out.println("Invalid choice");
			}
		}
	}
	
	public static void signup() {
		Scanner input=new Scanner(System.in);
		CustomerManagement customer=new CustomerManagement();
		System.out.println("\n======================* SIGNUP FORM *===========================");
		System.out.print("Enter the name:");
		String name=input.nextLine();
		System.out.print("Enter the email:");
		String email=input.next();
		System.out.print("Enter the phone:");
		String phone=input.next();
		System.out.print("Enter the address:");
		String address=input.next();
		customer.addcustomer(name, email, phone, address);
		System.out.println("*****************************************************************\n");
	}
	
	
	public static void login() {
		Scanner input=new Scanner(System.in);
		System.out.println("\n=======================* LOGIN FORM *===========================");
		CustomerManagement customer=new CustomerManagement();
		System.out.print("Enter the name: ");
		String name=input.nextLine();
		System.out.print("Enter the email_id: ");
		String email=input.next();
		int customerid=customer.checkuser(name,email);
		if(customerid!=0) {
			System.out.println("Login Success");
			System.out.println("*****************************************************************\n");
			usermenu(customerid);
		}
		else {
			System.out.println("Invalid username or email (or) customer not found");
		}
	}
	
	
	public static void usermenu(int customerid) {
		Scanner input=new Scanner(System.in);
		RoomManagement room=new RoomManagement();
		Booking book=new Booking();
		boolean flag=true;
		while(flag) {
			System.out.println("\n=======================* USER MENU *============================");
			System.out.print("1.Check Room Availability\n2.Book Room\n3.Cancel Booking\n4.View My Booking\n5.LogOut\nEnter your Choice: ");
			int choice=input.nextInt();
			switch(choice) {
				case 1:room.checkRoomAvailability();break;
				case 2:room.checkRoomAvailability();
						System.out.print("Enter the RoomID to Book:");
						int roomid=input.nextInt();
						System.out.print("Enter No of Days to stay:");
						int days=input.nextInt();
						book.bookRoom(customerid, roomid, days);
						break;
				case 3:book.cancelbooking(customerid);break;
				case 4:book.viewmybooking(customerid);break;
				case 5:flag=false;break;
				default:System.out.println("Invalid Choice Try Again");
			}
			System.out.println("*****************************************************************\n");
		}
		
	}
	
	public static void adminlogin() {
		Scanner input=new Scanner(System.in);
		System.out.println("\n=======================* ADMIN LOGIN *============================");
		Booking book=new Booking();
		String adminname="admin";
		String adminemail="admin@gmail.com";
		System.out.print("Enter the name:");
		String name=input.nextLine();
		System.out.print("Enter the email:");
		String email=input.nextLine();
		System.out.println("*****************************************************************\n");
		if(adminname.equals(name) && adminemail.equals(email)) {
			boolean flag=true;
			while(flag) {
				System.out.println("\n=======================* ADMIN MENU *============================");
				System.out.print("1.Customer Details\n2.Room Details\n3.View All Booking\n4.Staff Details\n5.LogOut\nEnter your Choice: ");
				int choice=input.nextInt();
				switch(choice) {
				case 1:customer();break;
				case 2:room();break;
				case 3:book.viewallbookings();break;
				case 4:staff();break;
				case 5:flag=false;break;
				default:System.out.println("Invalid Choice Try Again");
				}
				System.out.println("*****************************************************************\n");
			}
		}
		else {
			System.out.println("Invalid choice Try Again");
		}
	}
	
	
	public static void customer() {
		Scanner input=new Scanner(System.in);
		CustomerManagement customer=new CustomerManagement();
		boolean flag=true;
		int customerid;
		while(flag) {
			System.out.println("\n=================* ADMIN --> CUSTOMER OPERATIONS *=================");
			System.out.print("1.View Customers\n2.Update Customer\n3.Delete Customer\n4.Exit\nEnter your Choice: ");
			int choice=input.nextInt();
			switch(choice){
			case 1:customer.viewcustomers();break;
			case 2:customer.viewcustomers();
					System.out.print("Enter customer id:");
					customerid=input.nextInt();
					input.nextLine();
					System.out.print("Enter the name:");
					String name=input.nextLine();
					System.out.print("Enter the email:");
					String email=input.next();
					System.out.print("Enter the phone:");
					String phone=input.next();
					System.out.print("Enter the address:");
					String address=input.next();
					customer.updatecustomer(customerid,name, email, phone, address);
					break;
			case 3:customer.viewcustomers();
					System.out.print("Enter customer id to delete:");
					customerid=input.nextInt();
					customer.deletecusotomer(customerid);
					break;
			case 4:flag=false;break;
			default:System.out.println("Invalid Choice Try Again");
			}
			System.out.println("*****************************************************************\n");
		}
	}
	
	
	public static void room() {
		Scanner input=new Scanner(System.in);
		RoomManagement room=new RoomManagement();
		boolean flag=true;
		while(flag) {
			System.out.println("\n=================* ADMIN --> ROOM APERATIONS *=================");
			System.out.print("1.Add Room\n2.Update Room\n3.Delete Room\n4.Check Available rooms\n5.Check Booked Rooms\n6.Exit\nEnter your Choice: ");
			int choice=input.nextInt();
			switch(choice) {
			case 1:System.out.print("Enter Room Type:");
					String type=input.next();
					System.out.print("Enter Room Price:");
					double price=input.nextDouble();
					room.addRoom(type, price);
					break;
			case 2:room.printallrooms();
					System.out.print("Enter room id:");
					int roomid=input.nextInt();
					System.out.print("Enter Room Type:");
					String utype=input.next();
					System.out.print("Enter Room Price:");
					double uprice=input.nextDouble();
					room.updateRoom(roomid, utype, uprice);
					break;
			case 3:room.printallrooms();
				System.out.print("Enter room id:");
				int nroomid=input.nextInt();
				room.deleteRoom(nroomid);
				break;
			case 4:room.checkRoomAvailability();break;
			case 5:room.checkBookedRoom();break;
			case 6:flag=false;break;
			default:System.out.println("Invalid coice Try Again");
			}
			System.out.println("*****************************************************************\n");
		}
	}

	public static void staff() {
		Scanner input=new Scanner(System.in);
		StaffManagement staff=new StaffManagement();
		boolean flag=true;
		while(flag) {
			System.out.println("\n=================* ADMIN --> STAFF OPERATIONS *=================");
			System.out.print("1.View Staffs\n2.Add Staff\n3.Update Staffs\n4.Delete Staffs\n5.Exit\nEnter your Choice: ");
			int choice=input.nextInt();
			input.nextLine();
			switch(choice){
			case 1:staff.viewstaffs();break;
			case 2:System.out.print("Enter staff name:");
					String name=input.nextLine();
					System.out.print("Enter position:");
					String pos=input.nextLine();
					System.out.print("Enter salary");
					double salary=input.nextDouble();
					staff.addstaff(name, pos, salary);
					break;
			case 3:staff.viewstaffs();
				System.out.println("Enter staff id:");
				int staffid=input.nextInt();
				input.nextLine();
    			System.out.print("Enter staff name:");
	    		String uname=input.nextLine();
		    	System.out.print("Enter position:");
			    String upos=input.next();
			    System.out.print("Enter salary");
			    double usalary=input.nextDouble();
			    staff.updatetstaff(staffid, uname, upos, usalary);
			    break;
			case 4:staff.viewstaffs();
				System.out.print("Enter staff id:");
				int sid=input.nextInt();
				staff.deleteRoom(sid);
				break;
			case 5:flag=false;break;
			default:System.out.println("Invalid Choice Try Again");
			}
			System.out.println("*****************************************************************\n");
		}
	}
}
