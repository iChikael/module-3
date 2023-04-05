<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/26/2023
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Description</th>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.products}" var="c">
        <tr>
            <td>${c.getId()}</td>
            <td>${c.getName()}</td>
            <td>${c.getQuantity()}</td>
            <td>${c.getPrice()}</td>
            <td>${c.getDescription()}</td>

            <td>
                <a href="/product?action=create">Add</a>
                <a href="/product?action=edit&id=${c.getId()}">Edit</a>
                <a href="/product?action=delete&id=${c.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
