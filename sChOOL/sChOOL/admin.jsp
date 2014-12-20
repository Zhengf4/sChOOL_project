<%-- 
    Document   : admin
    Created on : Nov 3, 2014, 7:05:38 PM
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
			$('#addId').click(assignNewUser);
			$('#assignFaculty').click();
			$('#editClearance').click(showStudentClearance);
			$('#editAnnouncement').click(showAnnouncement);
		});
		
		function showStudentClearance(){
			$('#bodyPane').html("please wait...");
			$.ajax({
				type : "get",
				url : "ShowStudentClearance",
				success : function(result){
					$('#bodyPane').html(result);
				}
			});
		}
		
		var clearanceToggle = 0;
		function editClearance(clearanceId){
			if(clearanceToggle == 1){
				clearanceToggle = 0;
				element.getElementsByTagName("textArea").item(0).disabled = true;
				element.getElementsByTagName("input").item(0).value = "Edit";
				
				if(confirm("Are you sure on announcement change?")){
					var param = {
						announcementId : announcementId,
						message: element.getElementsByTagName("textArea").item(0).value
					};
					
					$.ajax({
						type : "get",
						data: param,
						url : "EditAnnouncement",
						success : function(){
							showAnnouncement();
							alert("Saved");
						}
					});
				}
			} else {
				clearanceToggle = 1;
				
			}
		}
		
		function assignNewUser(){
			$.ajax({
				type : "get",
				url : "AssignNewUser",
				success : function(result){
					$('#bodyPane').html(result);
					$('#addNewUser').click(addNewUser);
				}
			});
		}
		
		function addNewUser(){
			var param = {
				name : $('[name=userName]').val(),
				userId : $('[name=userId]').val(),
				password : $('[name=password]').val(),
				profession : $('[name=profession]').val()
			};
			
			$.ajax({
				type : "get",
				url : "AddUser",
				data : param,
				success : function(result){
					$('#bodyPane').html(result);
					alert("User added!");
				}
			});
		}
		
		function submitAnnounce(){
			if($('#announcementBox').val() == ''){
				alert("Please put an announcement");
			} else {
				var param = {
					adminId : $('#adminId').val(),
					announcement : $('#announcementBox').val()
				};
				$('#announcementBox').val('');
				$.ajax({
					type : "get",
					url : "AddNewAnnouncement",
					data : param,
					success : function(){
						alert("New announcement added!");
						showAnnouncement();
					}
				});
			}
		}
		
		function showAnnouncement(){
			$('#bodyPane').html("please wait...");
			var param = {
				adminId: $("#adminId").val()
			};
				
			$.ajax({
				type : "get",
				url : "ShowAnnouncement",
				data : param,
				success : function(result){
					$('#bodyPane').html(result);
					$('#submitAnnounceBtn').click(submitAnnounce);
				}
			});
		}
		
		var toggle = 0;
		function editAnnouncement(announcementId){
			var element = document.getElementById(announcementId);
			
			if(toggle == 1){
				toggle = 0;
				element.getElementsByTagName("textArea").item(0).disabled = true;
				element.getElementsByTagName("input").item(0).value = "Edit";
				
				if(confirm("Are you sure on announcement change?")){
					var param = {
						announcementId : announcementId,
						message: element.getElementsByTagName("textArea").item(0).value
					};
					
					$.ajax({
						type : "get",
						data: param,
						url : "EditAnnouncement",
						success : function(){
							showAnnouncement();
							alert("Saved");
						}
					});
				}
				
			} else {
				toggle = 1;
				element.getElementsByTagName("textArea").item(0).disabled = false;
				element.getElementsByTagName("input").item(0).value = "Save";
			}
		}
		
		function deleteAnnouncement(announcementId){
			if(confirm("are you sure you want to delete this announcement?")){
				var param = {
						announcementId : announcementId
				};
				
				$.ajax({
					type : "get",
					data : param,
					url : "DeleteAnnouncement",
					success : function(){
						alert("Deleted!");
						showAnnouncement();
					}
				});
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
    
    <h2>Hi, Admininstrator ${user.getName()}!</h2>
    <input type="hidden" value="${user.getUserId()}" id="adminId"/>
    <div id="sidePane">
    	<input class="btn btn-success" type="button" id="addId" value="Add ID"/><br><br>
		<input class="btn btn-success" type="button" id="assignFaculty" value="Assign Faculty"/><br><br>
		<input class="btn btn-success" type="button" id="editClearance" value="Student Clearances"/><br><br>
		<input class="btn btn-success" type="button" id="editAnnouncement" value="Announcements"/>
    </div>
    
    <div id="bodyPane">
    	<p align="center">Click one of the buttons to start</p>
    </div>
	
</body>
</html>