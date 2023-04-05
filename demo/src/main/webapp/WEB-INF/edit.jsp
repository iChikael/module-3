<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/30/2023
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/bootstrap.min.css">
<html>

<style>

    div > input {
        width: 300px;
        display: inline-block;
    }

    div form {
        margin-top: 20%;
    }
</style>
<head>
    <title>Edit Product</title>
</head>
<body>
<div class="row">
    <c:if test="${requestScope.message !=null}">
        <script>
            window.onload = function () {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: '${requestScope.successMsg}',
                    showConfirmButton: false,
                    timer: 1500
                })
            };
        </script>
    </c:if>
</div>
<div class="container-fluid col-6">
    <div class="col-12">
        <c:if test="${not empty requestScope.errorMessages}">
            <div class="alert alert-danger">
                <c:forEach items="${requestScope.errorMessages}" var="error">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <form class="form-horizontal d-inline" method="post">
        <div class="mt-5 mb-3">
            <h1>Edit product</h1>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Name</label>
            <div class="col-md-10">
                <input type="text" class="form-control" value="${requestScope.product.getName()}" name="name">
            </div>
        </div>


        <div class="form-group row">
            <label class="col-md-2 control-label">Quantity</label>
            <div class="col-md-10">
                <input type="number" class="form-control" value="${requestScope.product.getQuantity()}" name="quantity">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-md-2 control-label">Price</label>
            <div class="col-md-10">
                <input type="number" class="form-control" value="${requestScope.product.getPrice()}" name="price">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Description</label>
            <div class="col-md-10">
                <textarea class="form-control" rows="5" name="description" value="${requestScope.product.getDescription()}">${requestScope.product.getDescription()}</textarea>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Type</label>
            <select name="type" class="ml-3 col-md-3">
                <c:forEach items="${requestScope.producttypes}" var="c">
                    <option value="${c.getId()}" <c:if test="${c.getId() == productType}">selected</c:if>>${c.getName()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label ml-3"></label>
            <button class="btn btn-danger">Save</button>
        </div>
    </form>
</div>
</body>
</html>
