<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31/3/2023
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css"
          integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        .frmAdd{
            margin-top: 30px;
        }
        #btnEdit{
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="frmAdd col-6 offset-2">
            <div class="row mb-1">
                <label class="col-4">Name</label>
                <input class="col-8 form-control" type="text" >
            </div>
            <div class="row mb-1">
                <label class="col-4">Name</label>
                <input class="col-8 form-control" type="text" >
            </div>
            <div class="row mb-1">
                <label class="col-4">Name</label>
                <input class="col-8 form-control" type="text" >
            </div>
            <div class="row mb-1">

                <button class="btn btn-primary offset-4" id="btnEdit">Edit</button>
                <button class="btn btn-dark" id="btnBack">Back</button>
            </div>

        </div>
    </div>
</div>
</body>
</html>
