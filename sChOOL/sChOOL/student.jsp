<%-- 
    Document   : student
    Created on : Nov 3, 2014, 3:57:01 PM
    Author     : Operio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.UserBean"%>
<% UserBean userBean = (UserBean)(request.getSession().getAttribute("studentSessionUser"));%>
<!DOCTYPE html>
<html>
	<head>
         <script type = "text/javascript">
         window.history.forward();
         function noBack() { window.history.forward();}
        </script>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/mystyle.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
	
	</head>
	
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
    <%
HttpSession ses = request.getSession(false);
 if(ses.getAttribute("studentSessionUser")==null){
    response.sendRedirect("index.jsp");
    return;
}
%>
    <div class="navbar navbar-inverse">
       <div class="container">
            <ul class="nav nav-pills pull-right">
                <li>
                    
                    <form action="LogOut">
                         <input type="hidden" value="<%=userBean.getSessionId()%> name="session_Id>
                   <INPUT Type="submit" class="btn btn-primary" VALUE="Log Out" >    
                    
                    </form>
                   </li>
          </ul>
        <h3>St. Michael's Learning Center</h3>
      </div>
    </div>
  
	<h2>Hi, Student Username!</h2>
	<iframe id="iFrame" class="iFrame" align="right" name="Frame" ></iframe>
	<br>
        <input class="btn btn-success" type="button"  id="button" value="CLEARANCE" onclick="Frame.location.href='Clearance.jsp'"><br><br>
	<input class="btn btn-success" type="button"  id="button" value="GRADE" onclick="Frame.location.href='Grades.jsp'">
        
      
	</body>
</html>
