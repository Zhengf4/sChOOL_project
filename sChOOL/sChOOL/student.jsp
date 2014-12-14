<%-- 
    Document   : student
    Created on : Nov 3, 2014, 3:57:01 PM
    Author     : Operio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<style>
			#sidePane{
				position:absolute;
				width: 300px;
				height: 500px;
			}
			
			#bodyPane{
				position: absolute;
				left: 300px;
				width: 800px;
				height: 500px;
			}
			
			table {
				font-family: verdana,arial,sans-serif;
				font-size:11px;
				color:#333333;
				border-width: 1px;
				border-color: #666666;
				border-collapse: collapse;
			}
			table th {
				border-width: 1px;
				padding: 5px;
				border-style: solid;
				border-color: #666666;
				background-color: #dedede;
			}
			table td {
				border-width: 1px;
				padding: 5px;
				border-style: solid;
				border-color: #666666;
				background-color: #ffffff;
			}
		</style>
		
		<script src="js/jquery-2.1.1.js"></script>
		<script>
			$(document).ready(function(){
				$('#clearance').click(showClearance);
				$('#grade').click(showGrades);
			});
			
			function showClearance(){
				$('#bodyPane').html("Please wait...");
				var param = {
					studentId: $("#studentId").val()
				};
				
				$.ajax({
					type : "get",
					url : "ShowClearance",
					data : param,
					success : function(result){
						$('#bodyPane').html(result);
					}
				});
			}
			
			function showGrades(){
				$('#bodyPane').html("Please wait...");
				var param = {
					studentId: $("#studentId").val()
				};
				
				$.ajax({
					type : "get",
					url : "ShowGrades",
					data : param,
					success : function(result){
						$('#bodyPane').html(result);
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
  
	<h2>Hi, Student ${user.getName()}!</h2>
	<input type="hidden" value="${user.getUserId()}" id="studentId"/>
	<div id="sidePane">
		<input class="btn btn-success" type="button"  id="clearance" value="View Clearance"/><br><br>
		<input class="btn btn-success" type="button"  id="grade" value="View Grades"/>
	</div>
	
	<div id="bodyPane">
		<p align="center">Click on one of the buttons to start</p>
	</div>
        
      
	</body>
</html>
