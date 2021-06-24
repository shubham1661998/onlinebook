package project1;

import java.io.IOException;
import jdbcconnection.Database;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class bookdelete extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		System.out.println("In Delete book servlet");
		String dbookname=(String)req.getParameter("deletebook");
		Database db=new Database();
		int t=db.deletebook(dbookname);
		if(t==1)
		{
			res.sendRedirect("infodisplay.jsp");
		}
		else
		{
			System.out.println("Error to delete book in database");
		}
		
	}

}
