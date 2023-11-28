package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeCRUD_Operations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
       
        int choice;
		do {
        	System.out.println("Welcome to Employee Crud Operations");
        	System.out.println("1.Insert\n2.Display\n3.Search\n4.Update\n5.Delete");
        	System.out.println("Enter Your choice: ");
            choice=sc.nextInt();
        	switch(choice) {
        	case 1:
        		Insert();
        		break;
        	case 2:
        		Display();
        		break;
        	case 3:
        		Search();
        		break;
        	case 4:
        		Update();
        		break;
        	case 5:
        		Delete();
        		break;
        	default:
				System.out.println("You entered invalid input.please enter valid input.");
				break;
        	}	
        }while(choice!=0);
	}
	private static void Delete() {
		// TODO Auto-generated method stub
		try {
			Connection con=null;
			ResultSet res=null;
			PreparedStatement pstmt=null;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("Driver Loaded successfully.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt1","root","root");
			System.out.println("Connection is established");
			
			String s="delete from Employee where Employee_id=?";
			pstmt=con.prepareStatement(s);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the id of the Employee");
			int tempId=sc.nextInt();
			
			pstmt.setInt(1, tempId);
			int rows = pstmt.executeUpdate();
			System.out.println(rows);
			if(rows == 0) {
				System.out.println("Employee details are not deleted");
			}
			else {
				System.out.println("Employee details are deleted successfully");
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void Update() {
		// TODO Auto-generated method stub
		try {
			Connection con=null;
			ResultSet res=null;
			PreparedStatement pstmt=null;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("Driver Loaded successfully.");
	
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt1","root","root");
			System.out.println("Connection is established");
			
			String s="update Employee set Employee_name=? where Employee_id=?";
			pstmt=con.prepareStatement(s);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the id of the Employee");
			int tempId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the updated Name");
			String tempName=sc.next();
			
			pstmt.setString(1, tempName);
			pstmt.setInt(2, tempId);
			int rows = pstmt.executeUpdate();
			System.out.println(rows);
			if(rows == 0) {
				System.out.println("Employee details are not updated");
			}
			else {
				System.out.println("Employee details are updated successfully");
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void Search() {
		// TODO Auto-generated method stub
		try {
			Statement stmt=null;
			Connection con=null;
			ResultSet res=null;
			PreparedStatement pstmt=null;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("Driver Loaded successfully.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt1","root","root");
			System.out.println("Connection is established");
			
			String s="select * from Employee where Employee_id=?";
			pstmt = con.prepareStatement(s);
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter the id of the Employee");
			int tempId=sc1.nextInt();
			pstmt.setInt(1, tempId);
			res=pstmt.executeQuery();
			if(res.next()) {
				int id= res.getInt(1);
			    String name= res.getString(2);
				int salary= res.getInt(3);

				System.out.println(id+"   "+name+"   "+salary);
			}
			else {
				System.out.println("Employee Details are not found.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void Display() {
		// TODO Auto-generated method stub
		try {
			Statement stmt=null;
			Connection con=null;
			ResultSet res=null;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("Driver Loaded successfully.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt1","root","root");
			System.out.println("Connection is established");
			
			String s="select * from Employee";
			stmt = con.createStatement();
			res = stmt.executeQuery(s);
			
			while(res.next()) {
				int id= res.getInt(1);
			    String name= res.getString(2);
				int salary= res.getInt(3);
				System.out.println(id+"   "+name+"  "+salary);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void Insert() {
		// TODO Auto-generated method stub
		try {
			Statement stmt=null;
			Connection con=null;
			PreparedStatement pstmt=null;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("Driver Loaded successfully.");

			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt1","root","root");
			System.out.println("Connection is established");

			String s="insert into Employee values(?,?,?)";

			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter the id of the Employee");
			int tempId=sc1.nextInt();
			sc1.nextLine();

			System.out.println("Enter the name of the employee");
			String tempName=sc1.next();
		
			System.out.println("Enter the Salary of the employee");
			long tempSalary=sc1.nextInt();
			pstmt = con.prepareStatement(s);

			pstmt.setInt(1, tempId);
			pstmt.setString(2, tempName);
			pstmt.setLong(3, tempSalary);
			int res = pstmt.executeUpdate();
			if(res == 0) {
				System.out.println("Employee details are not added successfully");
			}
			else {
				System.out.println("Employee details are added successfully");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
