<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/26/2023
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create Products</title>
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
        <input name="txtName"  />
    </div>
    <div>
        <label>Quantity</label>
        <input name="txtEmail"  />
    </div>
    <div>
        <label>Price</label>
        <input name="txtAddress"  />
    </div>
    <div>
        <label>Description</label>
        <input name="txtImage"  />
    </div>
    <div>
        <label></label>
        <button>Create</button>
    </div>
</form>
</body>
</html>
