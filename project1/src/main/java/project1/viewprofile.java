package project1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class viewprofile extends HttpServlet{
	static String loginemail;
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		//System.out.println("In view Profile do post method");
		Connection con;
		Statement stmt;
		PreparedStatement pstmt;
		ResultSet set;
		byte[] imstore=null;
		ServletOutputStream sos=null;
		HttpSession session=req.getSession();
		String loginemail=(String)session.getAttribute("loginemail");
		System.out.println("Loginemail:"+loginemail);
		try
		{
				Class.forName("com.mysql.cj.jdbc.Driver");//load driver
				String url="jdbc:mysql://localhost:3306/project1";//store data in project1 database
				con=DriverManager.getConnection(url,"root","");
				stmt=con.createStatement();
				System.out.println("in image method");
				String img="select picture from registerinfo where e_mail=?";
				pstmt=con.prepareStatement(img);
				pstmt.setString(1,loginemail);
				set=pstmt.executeQuery();
				if(set.next())
				{
					imstore=set.getBytes(1);
				}
				sos=res.getOutputStream();
				sos.write(imstore);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

	
