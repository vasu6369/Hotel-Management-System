package hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class StaffManagement {
	public void addstaff(String name, String position, double salary) {
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO staff (name, position, salary) VALUES (?, ?, ?)");
			ps.setString(1, name);
            ps.setString(2, position);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("Staff member added successfully!");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void updatetstaff(int staffId, String name, String position, double salary) {
        try {
        	Connection con = DataBase.getConnection();
        	PreparedStatement ps = con.prepareStatement("UPDATE staff SET name = ?, position = ?, salary = ? WHERE staff_id = ?");
        	ps.setString(1, name);
            ps.setString(2, position);
            ps.setDouble(3, salary);
            ps.setInt(4, staffId);
            ps.executeUpdate();
          System.out.println("Staff member updated successfully!");
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
	
	public void deleteRoom(int staffId) {
		try {
        	Connection con = DataBase.getConnection();
        	PreparedStatement ps = con.prepareStatement("DELETE FROM staff WHERE staff_id = ?");
        	ps.setInt(1, staffId);
            ps.executeUpdate();
            System.out.println("Staff member deleted successfully!");
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
	
	public void viewstaffs() {
        try {
        	Connection con = DataBase.getConnection();
        	Statement s=con.createStatement();
        	ResultSet rs=s.executeQuery("select * from staff");
        	System.out.println("Staff_Id   name   Position  Salary");
			System.out.println("---------------------------------------------");
        	while(rs.next()) {
        		System.out.println(rs.getInt(1)+"\t  "+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4));
    			System.out.println("---------------------------------------------");
        	}
        }catch(SQLException e) {
        	System.out.println(e);
        }
    }
	
}
