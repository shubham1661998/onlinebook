package project1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import jdbcconnection.Database;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet(name="addbook",urlPatterns={"/addbook"})
@MultipartConfig
public class addbook extends HttpServlet 
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		//res.setContentType("text/jsp;charset=ISo-8859-1");
		PrintWriter out=res.getWriter();
		out.println("In book servlet");
				Part prt=req.getPart("addb");
				String bookname=prt.getSubmittedFileName();//get overall file path
				System.out.println("OverallPath name:"+bookname);
				Database db=new Database();
				int t=db.addbook(prt,bookname);
				
				
				if(t==1)
				{
					res.sendRedirect("infodisplay.jsp");
				}
				else
				{
					out.println("Error to store book");
				}
	 }
}
