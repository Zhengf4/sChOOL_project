<%-- 
    Document   : faculty
    Created on : Nov 3, 2014, 6:58:47 PM
    Author     : Operio
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/mystyle.css" rel="stylesheet">
<link href="css/carousel.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!DOCTYPE html>
<html>
<head>
	<style>
		#sidePane{
			position:absolute;
			width: 300px;
			height: 500px;
		}
		
		#listPane{
			position: absolute;
			left: 300px;
			width: 600px;
			height: 500px;
		}
		
		#studentList{
			position: absolute;
			right: 0px;
			width: 300px;
			height: 500px;
		}
		
		#classList{
			position: absolute;
			width: 300px;
			height: 500px;
		}
		
		.list:hover{
			background-color: orange;
		}
	</style>
	
	<script src="js/jquery-2.1.1.js"></script>
	<script>
		$(document).ready(function(){
			$("#activateList").click(showClassList);
		});
		
		function showClassList(){
			var param = {
					facultyId: $("#facultyId").val()
			};
			
			$.ajax({
				type : "get",
				url : "ShowClassList",
				data : param,
				success : function(result){
					$('#classList').html(result);
				}
			});
		}
		
		function showStudentList(Id){
			var param = {
				classId : Id
			};
			$.ajax({
				type : "get",
				url : "ShowStudentList",
				data : param,
				success : function(result){
					$('#studentList').html(result);
				}
			});
	
		}
		
	</script>

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
    <div class="navbar navbar-inverse">
       <div class="container">
            <ul class="nav nav-pills pull-right">
                <li>
                    
                    <form action="LogOut">
                   <INPUT Type="submit" class="btn btn-primary" VALUE="Log Out" >    
                    
                    </form>
                   </li>
          </ul>
        <h3>St. Michael's Learning Center</h3>
      </div>
    </div>
    
<h2>Hi, Teacher ${user.getName()}!</h2></br>
<input type="hidden" value="${user.getUserId()}" id="facultyId"/>

<div id="sidePane">
	<input type="button" class="btn btn-success" id="activateList" value="Class List"/>
</div>

<div id="listPane">
	<div id="classList">
 		<p align="center">Click on the Class List button to view classes handled</p>
 	</div>
 
 	<div id="studentList">
 		<p align="center">Click on the class you wish to view students from</p>
 	</div>
</div>

</body>
</html>
