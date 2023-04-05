<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24/3/2023
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Delete Customer</title>
  <style>
    div > label{
      width: 100px;
      display: inline-block;
    }
  </style>
</head>
<body>
<c:if test="${requestScope.message!=null}">
  <h4>${requestScope.message}</h4>
</c:if>
<form  method="post">
  <div>
    <label>Name</label>
    <input name="txtName" disabled value="${requestScope.customer.getName()}" />
  </div>
  <div>
    <label>Email</label>
    <input name="txtEmail"  disabled value="${requestScope.customer.getEmail()}"/>
  </div>
  <div>
    <label>Address</label>
    <input name="txtAddress" disabled value="${requestScope.customer.getAddress()}" />
  </div>
  <div>
    <label>Image</label>
    <input name="txtImage" disabled value="${requestScope.customer.getImage()}" />
  </div>
  <div>
    <label></label>
    <button>Confirm</button>
  </div>
</form>
</body>
</html>
