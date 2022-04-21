<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit My Task</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Edit Expense</h1>
		
		<form:form action="/expenses/edit/${gasto.getId()}" method="POST" modelAttribute="gasto">
			<input type="hidden" name="_method" value="put">
			<form:input type="hidden" path="id" value="${gasto.getId()}"/>
			<div class="form-group">
				<form:label path="expense_name">Expense Name</form:label>
				<form:input path="expense_name" cssClass="form-control"/>
				<form:errors path="expense_name"/>
			</div>
			
			<div class="form-group">
				<form:label path="vendor">Vendor</form:label>
				<form:input path="vendor" cssClass="form-control" value="${gasto.getVendor()}"/>
				<form:errors path="vendor"/>
			</div>
			
			<div class="form-group">
				<form:label path="amount">Amount</form:label>
				<form:input path="amount" cssClass="form-control" value="${gasto.getAmount()}"/>
				<form:errors path="amount"/>
			</div>
			
			<div class="form-group">
				<form:label path="description">Description</form:label>
				<form:textarea path="description" cssClass="form-control" value="${gasto.getDescription()}"/>
				<form:errors path="description"/>
			</div>
			
			<input type="submit" value="Submit" class="btn btn-success">
		</form:form>
	</div>
	
</body>
</html>