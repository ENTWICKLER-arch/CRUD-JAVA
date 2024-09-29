package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;

public class Demo {

	public static final String driverClassName = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/batch_10";
	public static final String username = "root";
	public static final String password = "1234";
	public static final String getDetails = "SELECT * FROM prerana";
	public static final String getDetailsById = "SELECT * FROM prerana WHERE ID = ? ";
	public static final String insert = "INSERT INTO  * FROM prerana VALUES(?,?,?)";
	public static final String update = "UPDATE prerana SET NAME = ?, COURSE= ? , WHERE ID = ?";
	public static final String delete = "DELETE  * FROM prerana WHERE ID = ?";
	//private static final String COURSE = null;
	
	
	public static void main (String[]args)throws Exception{
		
	
		Class.forName(driverClassName);
		Connection con = DriverManager.getConnection(url , username , password) ;
		
		PreparedStatement ps1= con.prepareStatement(getDetails);
		PreparedStatement ps2= con.prepareStatement(getDetailsById);
		PreparedStatement ps3= con.prepareStatement(insert);
		PreparedStatement ps4= con.prepareStatement(update);
		PreparedStatement ps5= con.prepareStatement(delete); 
		
		InputStreamReader isr = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader (isr);
	
		loop:while(true ) {
			System.out.println("1. Get All Student Details");
			System.out.println("2. Get All Student Detail by Id ");
			System.out.println("3. Add New Student Details");
			System.out.println("4. Update Record by id");
			System.out.println("5. Delete record by id");
			System.out.println("6. exit \n \n \n\n\n");
			
			
			System.out.println("Enter Your Choice  :  ");
			String Choice = br. readLine();
			
			switch(Choice) {
			
			case "1" : 
			{
				ResultSet rs = ps1.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getInt(1)+ "\t" + rs.getString(2)+ "\t\t" + rs.getString(3));
			}}break;
			
			case "2" :
			{
				System.out.println("Enter Student id :");
				int id = Integer.parseInt((br.readLine()));
				ps2.setInt(1, id);
				
				ResultSet rs = ps2.executeQuery();
				if (rs.next())
				{
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3));
				}
				
			} break ;
			
			case "3" :{
				
				System.out.println("Enter ducat id : ");
				Integer id = Integer.parseInt(br.readLine());
				
				System.out.println("Enter Student Name :");
				String name = br.readLine();
				
				ps3.setInt(1,id);
				ps3.setString(2, name);
				String course = null;
				ps3.setString(3,course);
				
				int k =ps3.executeUpdate();
				if (k>0) {
					System.out.println("Record Inserted");
				} else {
					System.out.println("Failed to Insert");
				}
				
				
			} break ;
			
			case "4" : 
			{ 
				System.out.println("Enter Student id :");
				int id = Integer.parseInt(br.readLine());
				ps2.setInt(1, id);
				
				ResultSet rs = ps2.executeQuery();
				if (rs.next())
				{
					System.err.println("old name : " + rs.getString(2));
					System.err.println("old course :" + rs.getString(3)+ "\n\n");
					
					System.out.println("Enter new name : ");
					String name = br.readLine();
					
					System.out.println("Enter new course : ");
					String course = br.readLine();
					
					ps4.setString(1, name);
					ps4.setString(2, course);
					ps4.setInt(3, id);
					
					int k = ps4.executeUpdate();
					if(k>0) {
						System.out.println("Record Updated");
						
					}else {
						System.err.println("Failed To update");
					}
					}
				else{
				
				   System.err.println("Invalid Student Id")	;
				}
				
				
			} break;
			
			case "5" : {
				System.out.println("Enter Student id :");
				int id = Integer.parseInt(br.readLine());
				ps2.setInt(1, id);
				
				ResultSet rs = ps2.executeQuery();
				if (rs.next())
				{
					ps5.setInt(1, id);
					int k = ps5.executeUpdate();
					if(k>0) {
						System.out.println("Record Deleted");
						
					}else {
						System.err.println("Failed To Delete");
					}
					
					
				}else {
					System.err.println("Record not found")	;
				
				}
				
			}break ;
			
			case "6": {
				System.err.println("Good bye");
				
			    break loop ;}
			default : {
				System.err.println("Invalid option");
			}
				
			}
			}
		}}
			
			

		
	
		
		
	

