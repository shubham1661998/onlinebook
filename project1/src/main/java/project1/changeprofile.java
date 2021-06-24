package project1;
import jdbcconnection.Database;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig
public class changeprofile extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/jsp;charset=ISo-8859-1");
		Part prt=req.getPart("changeprofile");
		String name=prt.getSubmittedFileName();
		System.out.println(name);
		System.out.println("In change profile");
		Database db=new Database();
		try
		{
			db.changeprofile(prt);
			res.sendRedirect("infodisplay.jsp");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
