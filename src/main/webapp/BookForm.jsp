<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Books Store Application</title>
</head>
<meta charset="UTF-8">
<body>
	<h1 align="center">Books Management</h1>
	<h2 align="center">
		<a href="/new">Add New book</a> &nbsp;&nbsp;&nbsp; <a href="/list">List
			All Books</a>
	</h2>
	<div align="center">
		<c:if test="${book != null }">
			<form action="update" method="post">
		</c:if>
		<c:if test="${book == null }">
			<form action="insert" method="post">
		</c:if>
		<c:if test="${book != null }">
			<h2>Edit Book</h2>
		</c:if>
		<c:if test="${book == null }">
			<h2>Add New Book</h2>
		</c:if>
		<c:if test="${book != null }">
			<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
		</c:if>
		<label for="title">Title: </label><input id="title" type="text" value="<c:out value='${book.title}' />" ><br>
		<label for="author">Author: </label><input id="author" type="text" value="<c:out value='${book.author}' />" ><br>
		<label for="price">Price: </label><input id="" type="number" value="<c:out value='${book.price}' />" ><br>
		<input type="submit" value="Save">
		</form>
	</div>
</body>
</html>