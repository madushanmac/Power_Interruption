<%@page import="com.PowerInterruption"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	// SAVE
    	if (request.getParameter("interruptionID") != null) {	
    		
    		PowerInterruption item = new PowerInterruption();
    		String statusMsg = "";
    		
    		String code = request.getParameter("interruptionID");
    		String name = request.getParameter("date");
    		String price = request.getParameter("location");
    		String desc = request.getParameter("description");
    		
    		if (request.getParameter("hidItemIDSave") == "") {
    	statusMsg = item.insertInterruptionDetails(interruptionID, date, location, description);
    		} else {
    	statusMsg = item.updateItem(request.getParameter("hidItemIDSave"),interruptionID, date, location, description);
    		}
    		
    		
    		session.setAttribute("statusMsg", statusMsg);
    	}

    	// DELETE
    	if (request.getParameter("hidItemIDDelete") != null) {
    		PowerInterruption item = new PowerInterruption();
    		String statusMsg = item.deleteItem(request.getParameter("hidItemIDDelete"));
    		session.setAttribute("statusMsg", statusMsg);
    	}
    %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
        <script src="Components/items.js"></script>
		<title>Power interruption  Management</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>Items Management</h1>
					<form id="formItem" name="formItem" method="POST" action="items.jsp">
						Interruption ID: 
						<input 
							id="interruptionID" 
							name="id" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Date: 
						<input 
							id="date"
							name="itemName" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						location: 
						<input 
							id="location" 
							name="itemPrice" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						description: 
						<input 
							id="itemDesc" 
							name="itemDesc" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSaved" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidItemIDSave" id="hidItemIDSave" value="">
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success">
						<%
							out.print(session.getAttribute("statusMsg"));
						%>
					</div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<%
						PowerInterruption item = new PowerInterruption(); 
									out.print(item.readdetails());
					%>
				</div>
			</div>
		</div>
	</body>
</html>