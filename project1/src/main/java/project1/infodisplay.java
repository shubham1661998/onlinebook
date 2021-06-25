package project1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jdbcconnection.Database;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class infodisplay extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		Connection con;
		PreparedStatement pstmt;
		Database db=new Database();
		ResultSet set;
		String loginemail=db.Loginemail();
		System.out.println("infodisplay login:"+loginemail);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 String url="jdbc:mysql://localhost:3306/project1";
			 con=DriverManager.getConnection(url,"root","");
			 String query="select bool_name from books where e_mail=? limit 5";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,loginemail);
			set=pstmt.executeQuery();
			ArrayList<String> bname=new ArrayList<String>();
			while(set.next())
			{
				String name=set.getString("bool_name");
				bname.add(name);
			}
			System.out.println(bname);
			req.setAttribute("bname",bname);
			//req.getRequestDispatcher("infodisplay.jsp").forward(req, res);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
