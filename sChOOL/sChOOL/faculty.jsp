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
			width: 800px;
			height: 500px;
		}
		
		#studentList{
			position: absolute;
			right: 0px;
			width: 450px;
			height: 500px;
			padding: 10px;
		}
		
		#classList{
			position: absolute;
			width: 350px;
			height: 500px;
			padding: 10px;
		}
		
		#gradeTable {
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;
		}
		#gradeTable th {
			border-width: 1px;
			padding: 5px;
			border-style: solid;
			border-color: #666666;
			background-color: #dedede;
		}
		#gradeTable td {
			border-width: 1px;
			padding: 5px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
		.gradeText{
			width: 40px;
		}
		
</style>
	
	<script src="js/jquery-2.1.1.js"></script>
	<script>
		$(document).ready(function(){
			$('#activateList').click(showClassList);
		});
		
		function showClassList(){
			var message = "<p align='center'>Click on a class to view students</p>";
			var param = {
					facultyId: $("#facultyId").val()
			};
			
			$.ajax({
				type : "get",
				url : "ShowClassList",
				data : param,
				success : function(result){
					$('#classList').html(result);
					$('#studentList').html(message);
				}
			});
		}
		
		function showStudentList(classCode, subjectCode){
			var param = {
				classCode : classCode,
				subjectCode	: subjectCode
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
		
		function showStudentGrades(classCode, subjectCode){
			var param = {
					classCode : classCode,
					subjectCode	: subjectCode
				};
			
			$.ajax({
				type : "get",
				url : "ShowStudentGrades",
				data : param,
				success : function(result){
					$('#studentList').html(result);
				}
			});
		}
		
		var toggle = 0;
		function editGrades(studentId, subjectCode){
			var element = document.getElementById(studentId);
			if(toggle == 1){
				toggle = 0;
				element.getElementsByTagName("input").item(0).disabled = true;
				element.getElementsByTagName("input").item(1).disabled = true;
				element.getElementsByTagName("input").item(2).disabled = true;
				element.getElementsByTagName("input").item(3).disabled = true;
				element.getElementsByTagName("input").item(4).value = "Edit";
				
				var firstQG = element.getElementsByTagName("input").item(0).value;
				var secondQG = element.getElementsByTagName("input").item(1).value;
				var thirdQG = element.getElementsByTagName("input").item(2).value;
				var fourthQG = element.getElementsByTagName("input").item(3).value;
				
				var param = {
					studentId : studentId,
					subjectCode : subjectCode,
					firstQG : firstQG,
					secondQG : secondQG,
					thirdQG : thirdQG,
					fourthQG : fourthQG
				};
				
				$.ajax({
					type : "get",
					url : "UpdateStudentGrade",
					data : param,
					success : function(){
						
					}
				});
				
			} else {
				toggle = 1;
				element.getElementsByTagName("input").item(0).disabled = false;
				element.getElementsByTagName("input").item(1).disabled = false;
				element.getElementsByTagName("input").item(2).disabled = false;
				element.getElementsByTagName("input").item(3).disabled = false;
				element.getElementsByTagName("input").item(4).value = "Save";
			}
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
 		<p align="center">Click on a Class List button to view handled classes</p>
 	</div>
 
 	<div id="studentList">
 	</div>
</div>

</body>
</html>
