<%-- 
    Document   : StudentList
    Created on : Nov 4, 2014, 1:08:44 AM
    Author     : Operio
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/mystyle.css" rel="stylesheet">
<link href="css/carousel.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="Beans.UserBean"%>
<%@page import="DAO.UserDAO"%>
<% UserBean userBean = (UserBean)(request.getSession().getAttribute("facultySessionUser"));%>

<!DOCTYPE html>
<html>
	<head>
	
	</head>
<body>
	<h2>Student List: </h2>

 <table border="2" id="tableID">
    <tr>
        <td rowspan="2" align="center">NAME</td>
        <td colspan="4" align="center">GRADE</td>
    </tr>
    <tr>
        <td align="center">1st Quarter</td>
        <td align="center">2nd Quarter</td>
        <td align="center">3rd Quarter</td>
        <td align="center">4rd Quarter</td>
    </tr>
    <tr>
          <%
    UserDAO userDAO = new UserDAO();
            int num_of_students=userDAO.studentList().size();
            String student_name="";
    ArrayList<UserBean> jeje = userDAO.studentList();
    for(int i=0;i<num_of_students;i++)
    {
        student_name=jeje.get(i).getName();
        %>
            <tr>
        <td align="center"><%=student_name%></td>
        <td align="center"></td>
        <td align="center"><input type="text" value="87" contenteditable="false"/></td>
        <td align="center"><input type="text" value="87" contenteditable="false"/></td>
        <td align="center"><input type="text" value="89" contenteditable="false"/></td>
           </tr>
   <% }%>
    </tr>
 </table>
              
   <INPUT align="right" Type="Submit" class="btn btn-primary" VALUE="Apply Changes" onclick="getTableData()" >    

</body>
</html>