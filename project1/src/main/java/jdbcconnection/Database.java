package jdbcconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class Database {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet set;
	static String loginemail;
	byte[] imstore=null;
	ServletOutputStream sos=null;
	HttpServletResponse res;
	//for load a Driver
	public void loadDriver()
	{
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//load driver
			String url="jdbc:mysql://localhost:3306/project1";//store data in project1 database
			con=DriverManager.getConnection(url,"root","");
			stmt=con.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	 
	//check email id is already use or not
	public String check(String E_mail,String Pass) {
		try
		{
			loadDriver();
			String query="select e_mail,p_word from registerinfo";//In project1 database registerinfo table here
			set=stmt.executeQuery(query);
			while(set.next())
			{
				String email=set.getString("e_mail");
				String pass=set.getString("p_word");
				if(E_mail.equals(email) && Pass.equals(pass)) 
				{
					loginemail=E_mail;
					return E_mail;
				}
			 }
			return "a";
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			return "e";
		}
		
	}
	
	
	
	//new user registration
	public int jdbcregistration(String F_name,String E_mail,String P_no,String A_type,String Pass,Part prt)
	{
		try
		{
			loadDriver();
			String query="select e_mail from registerinfo";
			set=stmt.executeQuery(query);
			while(set.next())
			{
				String email=set.getString("e_mail");
				if(E_mail.equals(email)) 
				{
					JOptionPane.showInputDialog("This E-mail id already use");	
					return 0;
				}
			 }
			//new user add information in data base
			String q2="insert into registerinfo(f_name,e_mail,p_no,a_type,P_word,picture) values (?,?,?,?,?,?)";
			pstmt=con.prepareStatement(q2);
			pstmt.setString(1,F_name);
			pstmt.setString(2,E_mail);
			pstmt.setString(3,P_no);
			pstmt.setString(4,A_type);
			pstmt.setString(5,Pass);
			InputStream ips=prt.getInputStream();//insert  a image
			pstmt.setBlob(6, ips);
			pstmt.executeUpdate();
			return 1;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 2;
		}
		
	}
	
	
	public int addbook(Part prt,String bookname)
	{
		try
		{
			loadDriver();
			System.out.println("In books add method");
			System.out.println("Email:"+loginemail);
			System.out.println("Name of book:"+bookname);
			String add="insert into books(book,e_mail,bool_name) values (?,?,?)";
			pstmt=con.prepareStatement(add);
			InputStream ips=prt.getInputStream();//insert  a file
			pstmt.setBlob(1, ips);
			pstmt.setString(2, loginemail);
			pstmt.setString(3, bookname);
			pstmt.executeUpdate();
			System.out.println("insert book in data base");
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public void changeprofile(Part prt) throws SQLException, IOException
	{
		try
		{
			loadDriver();
			String add="update registerinfo set picture=? where e_mail=?";
			pstmt=con.prepareStatement(add);
			InputStream ips=prt.getInputStream();//  a image
			pstmt.setBlob(1, ips);
			pstmt.setString(2, loginemail);
			pstmt.executeUpdate();
			System.out.println("Image update");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int deletebook(String dbookname)
	{
		loadDriver();
		System.out.println("In delete book method");
		String query="delete from books where bool_name=? and e_mail=?";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, dbookname);
			pstmt.setString(2,loginemail);
			pstmt.executeUpdate();
			System.out.println("Delete a book in database");
			return 1;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
		
		
	}
}
	
	

