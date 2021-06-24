<%
	String mess=(String)session.getAttribute("message");
	if(mess!=null)
	{
	%>
	
  			<script type="text/javascript">
  				alert("Invalid E-mail & pass");
  			</script>
	
<% 
	session.removeAttribute("message");
	}
%>
