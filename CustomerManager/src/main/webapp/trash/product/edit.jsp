<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/26/2023
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Create Product</title>
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
    <label>
      <input name="txtName"/>
    </label>
  </div>
  <div>
    <label>Quantity</label>
    <input name="txtQuantity"  />
  </div>
  <div>
    <label>Price</label>
    <input name="txtPrice"  />
  </div>
  <div>
    <label>Description</label>
    <input name="txtDescription"  />
  </div>
  <div>
    <label></label>
    <button>Submit</button>
  </div>
</form>
</body>
</html>
Footer
© 2023 GitHub, Inc.
Footer navigation
Terms
