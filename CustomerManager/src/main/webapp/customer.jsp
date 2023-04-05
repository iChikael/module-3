<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css" integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        .head-button{
            margin: 20px 0px;
            display: flex;
            justify-content: end;
        }
        .head-button input{
            width: 200px;
        }
        .head-button input{
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="head-button">
            <input type="text" class="form-control" placeholder="search" >
            <button class="btn btn-primary">Add</button>
        </div>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID</th>
                    <th>ID</th>
                    <th>ID</th>
                    <th>ID</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>ID</td>
                    <td>ID</td>
                    <td>ID</td>
                    <td>ID</td>
                    <td>
                        <i class="fa-solid fa-pen-to-square btn btn-success"></i>
                        <i class="fa-solid fa-trash btn btn-warning"></i>
                    </td>
                </tr>
                <tr>
                    <td>ID</td>
                    <td>ID</td>
                    <td>ID</td>
                    <td>ID</td>
                    <td>
                        <i class="fa-solid fa-pen-to-square btn btn-success"></i>
                        <i class="fa-solid fa-trash btn btn-warning"></i>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
