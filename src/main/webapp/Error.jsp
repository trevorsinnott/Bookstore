<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
	<div align="center">
		<h1>Error</h1>
		<h2><%=exception.getMessage()%></h2><br />
		<a href="/">Book List</a>
	</div>
</body>
</html>