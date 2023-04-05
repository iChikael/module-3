<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24/3/2023
  Time: 2:09 PM
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
                <th>Email</th>
                <th>Address</th>
                <th>Image</th>
                <th>Customer type</th>
                <th>Create At</th>
                <td>Action</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.customers}" var="c">
                <tr>
                    <td>${c.getId()}</td>
                    <td>${c.getName()}</td>
                    <td>${c.getEmail()}</td>
                    <td>${c.getAddress()}</td>
                    <td>${c.getImage()}</td>
                    <td>
                        <c:forEach items="${requestScope.customertypes}" var="ct">
                            <c:if test="${ct.getId() == c.getCustomerType()}">
                                ${ct.getName()}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${c.getCreateAt()}</td>
                    <td>
                        <a href="/customers?action=create">Add</a>
                        <a href="/customers?action=edit&id=${c.getId()}">Edit</a>
                        <a href="/customers?action=delete&id=${c.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
