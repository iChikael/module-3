<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/30/2023
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.min.js" integrity="sha512-eN8dd/MGUx/RgM4HS5vCfebsBxvQB2yI0OS5rfmqfTo8NIseU+FenpNoa64REdgFftTY4tm0w8VMj5oJ8t+ncQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.css" integrity="sha512-us/9of/cEp3FrrmLUpCcWUAzm2gE7EOPnfEAWBMwdWR1Lpxw0orMoVvLyyoGSD9iMGAUlEd8XHzt5+SDwmdGLg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<html>
<link rel="stylesheet" href="/bootstrap.min.css">
<style>
    div > input {
        width: 300px;
        display: inline-block;
    }

    form {
        margin-top: 20%;
    }

</style>
<head>
    <title>Create New Product</title>
</head>
<body>
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
            <h1>Add new product</h1>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Name</label>
            <div class="col-md-10">
                <input type="text" class="form-control" value="" name="name">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Quantity</label>
            <div class="col-md-10">
                <input type="text" class="form-control" value="" name="quantity">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-md-2 control-label">Price</label>
            <div class="col-md-10">
                <input type="text" class="form-control" placeholder="" name="price">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Type :</label>
            <select name="productType" class="ml-3 col-md-3">
                <c:forEach items="${requestScope.producttypes}" var="c">
                    <option value="${c.getId()}" >${c.getName()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label">Description</label>
            <div class="col-md-10">
                <textarea class="form-control" rows="5" name="description" type="text"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-2 control-label ml-3"></label>
            <button class="btn btn-danger">Create</button>
        </div>
    </form>
</div>
</body>
</html>
