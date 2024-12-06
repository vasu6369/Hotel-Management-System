package hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerManagement {
	
	public void addcustomer(String name, String email, String phone, String address) {
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into customers (name, email, phone, address) values (?, ?, ?, ?)");
			 ps.setString(1, name);
             ps.setString(2, email);
             ps.setString(3, phone);
             ps.setString(4, address);
             ps.executeUpdate();
             System.out.println("Registration successfully");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void updatecustomer(int customerId,String name, String email, String phone, String address) {
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("update customers set name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?");
			ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setInt(5, customerId);
            ps.executeUpdate();
             System.out.println("Customer updated successfully!");
             ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public void deletecusotomer(int customerid) {
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from customers WHERE customer_id = ?");
			ps.setInt(1, customerid);
             System.out.println("Customer deleted successfully");
             ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void viewcustomers() {
		try {
			Connection con=DataBase.getConnection();
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery("select * from customers");
			System.out.println("\nId  Name  Email \t Phone    Address");
			System.out.println("--------------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"   "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				System.out.println("--------------------------------------------------");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public int checkuser(String name,String email) {
		int customerid = 0;
		try {
			Connection con=DataBase.getConnection();
			PreparedStatement ps=con.prepareStatement("select customer_id from customers WHERE name = ? and email=?");
			ps.setString(1, name);
			ps.setString(2, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				customerid=rs.getInt(1);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return customerid;
	}
	
}