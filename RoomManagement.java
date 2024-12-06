package hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class RoomManagement {

	public void addRoom(String roomtype, double price) {
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into rooms (room_type, price, is_available) values (?, ?, 1)");
			 ps.setString(1, roomtype);
             ps.setDouble(2, price);
             ps.executeUpdate();
             System.out.println("Room added Successfully");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public void updateRoom(int roomid, String roomtype, double price) {
        try {
        	Connection con = DataBase.getConnection();
        	PreparedStatement ps = con.prepareStatement("UPDATE rooms SET room_type = ?, price = ? WHERE room_id = ?");
                ps.setString(1, roomtype);
                ps.setDouble(2, price);
                ps.setInt(3, roomid);
                ps.executeUpdate();
                System.out.println("Room updated successfully!"); 
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
	
	public void deleteRoom(int roomid) {
		try {
        	Connection con = DataBase.getConnection();
        	PreparedStatement ps = con.prepareStatement("DELETE FROM rooms WHERE room_id = ?");
        	ps.setInt(1, roomid);
        	ps.executeUpdate();
            System.out.println("Room deleted successfully!"); 
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
	
	
	public void checkRoomAvailability() {
        try {
        	Connection con = DataBase.getConnection();
        	boolean found=false;
        	Statement s=con.createStatement();
        	ResultSet rs=s.executeQuery("select * from rooms where is_available = 1");
        	System.out.println("Available Rooms");
        	System.out.println("Room ID   Type   Price 	Is_Available");
			System.out.println("-----------------------------------------");
        	while(rs.next()) {
        		found=true;
        		System.out.println(rs.getInt(1)+"\t  "+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
    			System.out.println("-----------------------------------------");
        	}
        	if(!found) {
        		System.out.println("No Rooms Are Available");
        	}
        }catch(SQLException e) {
        	System.out.println(e);
        }
    }
	
	public void printallrooms() {
        try {
        	Connection con = DataBase.getConnection();
        	Statement s=con.createStatement();
        	ResultSet rs=s.executeQuery("select * from rooms");
        	System.out.println("Room ID   Type   Price");
			System.out.println("---------------------------------------------");
        	while(rs.next()) {
        		System.out.println(rs.getInt(1)+"\t  "+rs.getString(2)+"\t"+rs.getDouble(3));
    			System.out.println("---------------------------------------------");
        	}
        }catch(SQLException e) {
        	System.out.println(e);
        }
    }
	
	public void checkBookedRoom() {
        try {
        	Connection con = DataBase.getConnection();
        	Statement s=con.createStatement();
        	ResultSet rs=s.executeQuery("select * from rooms where is_available = 0");
        	System.out.println("Booked Rooms");
        	System.out.println("Room ID   Type   Price");
			System.out.println("---------------------------------------------");
        	while(rs.next()) {
        		System.out.println(rs.getInt(1)+"\t  "+rs.getString(2)+"\t"+rs.getDouble(3));
    			System.out.println("---------------------------------------------");
        	}
        }catch(SQLException e) {
        	System.out.println(e);
        }
    }
	
	public static void printroom(int roomid) {
		try {
        	Connection con = DataBase.getConnection();
        	PreparedStatement ps=con.prepareStatement("select * from rooms where room_id = ?");
        	ps.setInt(1, roomid);
        	ResultSet rs=ps.executeQuery();
        	System.out.println("My Bookings");
        	System.out.println("Room ID   Type   Price");
			System.out.println("---------------------------------------------");
        	while(rs.next()) {
        		System.out.println(rs.getInt(1)+"\t  "+rs.getString(2)+"\t"+rs.getDouble(3));
    			System.out.println("---------------------------------------------");
        	}
        }catch(SQLException e) {
        	System.out.println(e);
        }
	}
	
	
	
} 

