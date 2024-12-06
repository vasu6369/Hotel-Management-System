package hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Booking {

	public void bookRoom(int customerid, int roomid,int days) {
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO bookings (customer_id, room_id, days) VALUES (?, ?, ?)");
			ps.setInt(1, customerid);
            ps.setInt(2, roomid);
            ps.setInt(3,days);
            ps.executeUpdate();
            PreparedStatement pst=con.prepareStatement("UPDATE rooms SET is_available = 0 WHERE room_id = ?");
            pst.setInt(1 ,roomid);
            pst.executeUpdate();
            System.out.println("Room booked successfully!");
		}catch(SQLException e) {
			System.out.println(e);
		}
    }
	
	
	public void cancelbooking(int customerid) {
		try {
			Connection con=DataBase.getConnection();
			int roomid=0;
			PreparedStatement ps=con.prepareStatement("select room_id from bookings WHERE customer_id = ?");
			ps.setInt(1, customerid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				roomid=rs.getInt(1);
			}
			if(roomid!=0) {
				PreparedStatement pst=con.prepareStatement("UPDATE rooms SET is_available = 1 WHERE room_id = ?");
	            pst.setInt(1 ,roomid);
	            pst.executeUpdate();
	            PreparedStatement pds=con.prepareStatement("delete from bookings where customer_id=?");
				pds.setInt(1, customerid);
				pds.executeUpdate();
	            System.out.println("Booking canceled successfully!");
			}
			else {
				System.out.println("Booking Not Found");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void viewallbookings() {
		try {
			Connection con=DataBase.getConnection();
			boolean found=false;
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery("select * from bookings");
			System.out.println("\ncustomer_id  room_id  No_of_days");
			System.out.println("------------------------------------------------");
			while(rs.next()) {
				found=true;
				System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3));
				System.out.println("---------------------------------------------------");
			}
			if(!found) {
				System.out.println("No Booking Yet");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public void viewmybooking(int customerid) {
		try {
			int roomid = 0;
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("select room_id from bookings WHERE customer_id = ?");
			ps.setInt(1, customerid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				roomid=rs.getInt(1);
			}
			if(roomid!=0) {
				RoomManagement.printroom(roomid);
			}
			else {
				System.out.println("No Booking yet");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
}
