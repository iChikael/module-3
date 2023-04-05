<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/30/2023
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="bootstrap.min.css">
<html>
<head>
    <title>Delete</title>
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

<div class="container-fluid col-6">

    <form class="form-horizontal d-inline" method="post">
        <div class="mt-5 mb-3">
            <h1>Delete product</h1>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Name</label>
            <div class="col-md-10">
                <input type="text" disabled class="form-control" value="${requestScope.product.getName()}" name="name">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Type :</label>
            <select name="type" class="ml-3 col-md-3" disabled>
                <c:forEach items="${requestScope.producttypes}" var="c">
                    <option value="${c.getId()}"
                            <c:if test="${c.getId() == requestScope.product.getType()}">
                                selected
                            </c:if>
                    >${c.getName()}</option>
                </c:forEach>
            </select>

        </div>
        <div class="form-group row" disabled>
            <label class="col-md-2 control-label">Quantity</label>
            <div class="col-md-10">
                <input type="text" class="form-control" value="${requestScope.product.getQuantity()}" disabled name="quantity">
            </div>
        </div>

        <div class="form-group row" disabled>
            <label class="col-md-2 control-label">Price</label>
            <div class="col-md-10">
                <input type="text" class="form-control" placeholder="${requestScope.product.getPrice()}" disabled name="price">
            </div>
        </div>
        <div class="form-group row" disabled>
            <label class="col-md-2 control-label">Description</label>
            <div class="col-md-10">
                <textarea class="form-control" rows="5" name="description" disabled placeholder="${requestScope.product.getDescription()}"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label ml-3"></label>
            <button class="btn btn-danger">Delete</button>
        </div>
    </form>
</div>
</body>
</html>
