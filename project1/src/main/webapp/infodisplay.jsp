<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="jdbcconnection.Database,java.sql.*,java.io.*,java.io.File" %>
 <%!
 Connection con;
 Statement stmt;
 PreparedStatement pstmt;
 ResultSet set;
 byte[] imstore=null;
ServletOutputStream sos=null;
InputStream is;
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Information</title>
 <link rel="stylesheet" href="css/infostyle.css" media="screen" type="text/css" />

</head>
<body>
<div id="login-box">
  <div class="left">
    <h1 class="hed">My Profile</h1>
    <h2>Your Collection</h2>
    <div>
<%
		 String loginemail=(String)session.getAttribute("loginemail");
		 System.out.println("LoginEmail:"+loginemail);	
		 try
		 {
				 
			 	Class.forName("com.mysql.cj.jdbc.Driver");
				 String url="jdbc:mysql://localhost:3306/project1";
				 con=DriverManager.getConnection(url,"root","");
				 String query="select bool_name from books where e_mail=? limit 5";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1,loginemail);
				set=pstmt.executeQuery();
				
				while(set.next())
				{
					String name=set.getString("bool_name");
					out.println(name); 
				}		 				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
 
%>
	<form action="openbook" method="post">
	<label>____________________________</label>
	<input placeholder="enter book name to open" type="text" name="bname">
	<input type="submit" value="open Book">
	</form>
    
    </div>
  </div>
  
  <div class="right">
   
    
    <table>
   
    <tr>
    	<td>
    	<form action="addbook" method="post" enctype="multipart/form-data">
    		 <label class="view">Add Book</label>
    		 <input type="submit" value="Done">
   			 <input type="file" name="addb">
   			</form>
    	</td>
    </tr>
    
    <tr>
    	<td>
    	<form action="bookdelete" method="post">
    		<label class="view">Delete</label>
    		<input type="submit" Value="Done">
    		<input type="text" placeholder="Enter book name" name="deletebook">
    	</form>
      </td>
    </tr>
    <tr>
    	<td>
    	<form action="changeprofile" method="post" enctype="multipart/form-data">
    
    			<label class="view">UpdatePic</label>
    			<input type="submit"  value="Update">
    			<input type="file" name="changeprofile">
    			
	    		
    	</form>
    	</td>
    </tr>
    <tr>
    	<td>
    	<form action="viewprofile" method="post">
    		<input type="submit"  value="View Profile">
    		</form>
    	</td>
    </tr>
 
   
   
     </table>
  </div>
</div>
</body>
 
</html>