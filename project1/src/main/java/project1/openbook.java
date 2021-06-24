package project1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class openbook extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		System.out.println("In open book");
		String bname=req.getParameter("bname");
		System.out.println("BookNmae:"+bname);
		Connection con;
		Statement stmt;
		PreparedStatement pstmt;
		ResultSet set;
		byte[] bstore=null;
		ServletOutputStream sos=null;
		HttpSession session=req.getSession();
		//String loginemail=(String)session.getAttribute("loginemail");
		try
		{
				Class.forName("com.mysql.cj.jdbc.Driver");//load driver
				String url="jdbc:mysql://localhost:3306/project1";//store data in project1 database
				con=DriverManager.getConnection(url,"root","");
				stmt=con.createStatement();
				System.out.println("in image method");
				String bk="select book from books where bool_name=?";
				pstmt=con.prepareStatement(bk);
				pstmt.setString(1,bname);
				set=pstmt.executeQuery();
				if(set.next())
				{
					bstore=set.getBytes(1);
				}
				
				sos=res.getOutputStream();
				
				sos.write(bstore); //error in this line
				System.out.println("Error1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
