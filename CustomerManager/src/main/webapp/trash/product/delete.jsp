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
  <title>Delete Product</title>
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
    <input name="txtName" disabled value="${requestScope.product.getName()}" />
  </div>


  <div>
    <label>Quantity</label>
    <input name="txtImage" disabled value="${requestScope.product.getQuantity()}" />
  </div>
  <div>
    <label>Price</label>
    <input name="txtPrice" disabled value="${requestScope.product.getPrice()}" />
  </div>
  <div>
    <label>Description</label>
    <input name="txtDescription"  disabled value="${requestScope.product.getDescription()}"/>
  </div>
  <div>
    <label></label>
    <button>Confirm</button>
  </div>
</form>
</body>
</html>