<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
<title>Product Management</title>

<style>

table{
border-collapse:collapse;
width:100%;
}

table,th,td{
border:1px solid black;
padding:10px;
}

</style>

</head>
<body>

<h2>Add Product</h2>

<form action="addProduct" method="post">

Name:
<input type="text" name="name" required>

Description:
<input type="text" name="description" required>

Price:
<input type="number" name="price" required>

Stock:
<input type="number" name="stock" required>

<button type="submit">
Add Product
</button>

</form>

<hr>

<h2>All Products</h2>

<table>

<tr>
<th>ID</th>
<th>Name</th>
<th>Description</th>
<th>Price</th>
<th>Stock</th>
<th>Actions</th>
</tr>

<c:forEach items="${products}" var="p">

<tr>

<td>${p.id}</td>
<td>${p.name}</td>
<td>${p.description}</td>
<td>${p.price}</td>
<td>${p.stock}</td>

<td>

<a href="delete/${p.id}">
Delete
</a>

<br><br>

<form action="updateDescription"
      method="post">

<input type="hidden"
       name="id"
       value="${p.id}">

<input type="text"
       name="description"
       placeholder="New Description">

<button type="submit">
Update Description
</button>

</form>

<br>

<form action="updateStock"
      method="post">

<input type="hidden"
       name="id"
       value="${p.id}">

<input type="number"
       name="quantity"
       placeholder="Stock Change">

<button type="submit">
Update Stock
</button>

</form>

</td>

</tr>

</c:forEach>

</table>

</body>
</html>