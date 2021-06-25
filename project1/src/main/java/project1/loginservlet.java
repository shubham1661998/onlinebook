package project1;
import jdbcconnection.Database;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class loginservlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String userid=req.getParameter("userid");
		 String pass=req.getParameter("pass");
		Database db=new Database();
	    String t=db.check(userid,pass);
	    //t==1 means it is existing user then login it
	   HttpSession session=req.getSession();
	   session.setAttribute("response", res);
	    if(t==userid)
	    {
	    	 session.setAttribute("loginemail", t);
	    	 res.sendRedirect("infodisplay.jsp");
	    	
	    	
	    }
	    else if(t=="a")
	    {
	    	//System.out.println("E-mail & Password incorrect!");
	    	HttpSession err=req.getSession();
	    	err.setAttribute("message","Invalid E-mail & Password");
	    	res.sendRedirect("index.jsp");
	    }
	}
}
