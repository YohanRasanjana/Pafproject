<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Payment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Payment Management </h1>
				<form id="formItem" name="formItem">
					Customer ID: <input id="customerID" name="customerID" type="text"
						class="form-control form-control-sm"> <br> 
						Customer Name:
					<input id="CustomerName" name="CustomerName" type="text"
						class="form-control form-control-sm"> <br> 
						Amount: <input id="Amount" name="Amount" type="text"
						class="form-control form-control-sm"> <br> 
						Card Number: <input id="cardNo" name="cardNo" type="text"
						class="form-control form-control-sm"> <br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
						<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
					payment itemObj = new payment();
					out.print(itemObj.readItems());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>