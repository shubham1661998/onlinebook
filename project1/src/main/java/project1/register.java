package project1;
import jdbcconnection.Database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(name="register",urlPatterns={"/register"})
@MultipartConfig 

public class register extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/jsp;charset=ISo-8859-1");
		String F_name=req.getParameter("fullName");
		String E_mail=req.getParameter("email");
		String P_no=req.getParameter("phone");
		String A_type=req.getParameter("atype");
		String P_word=req.getParameter("password");
		Part prt=req.getPart("picture");
		
		Database db1=new Database();
		int t=db1.jdbcregistration(F_name,E_mail,P_no,A_type,P_word,prt);
		if(t==1) {
			res.sendRedirect("index.jsp");
		}
		else if(t==0)//new user but e-mail all ready use
		{
			
			//PrintWriter out=res.getWriter();
			//out.println("E-mail Already user");
			HttpSession allreadyuse=req.getSession();
			allreadyuse.setAttribute("allreadyuse", t);
			
		}
		
		
	}
			
}
