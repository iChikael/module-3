<%--
  Created by IntelliJ IDEA.
  User: iChi
  Date: 3/30/2023
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.min.js"
        integrity="sha512-eN8dd/MGUx/RgM4HS5vCfebsBxvQB2yI0OS5rfmqfTo8NIseU+FenpNoa64REdgFftTY4tm0w8VMj5oJ8t+ncQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css"
      integrity="sha512-S/juY8iO/ktz82l/m/5whOz5/NiZ5fOSZPj5G5c5PKjnwTH0YQHPE+v+N04jKhnIiP0mJVbn3qfpZMjD1ggt0Q=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/js/bootstrap.bundle.min.js"
        integrity="sha512-kMzJtXLfayWcl9nk9F63x+5c5K8VddW/V0vAVNKL50BACQJN6+Omc6KZKY7xEM/SMJZB8jTNC+3vLJm1SzzXYA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.css"
      integrity="sha512-us/9of/cEp3FrrmLUpCcWUAzm2gE7EOPnfEAWBMwdWR1Lpxw0orMoVvLyyoGSD9iMGAUlEd8XHzt5+SDwmdGLg=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<html>
<style>
    table {
        margin-top: 1%;
        border: none;
        background-color: #cad8fa;
        padding: 10px;
        border-radius: 5px;
    }

    button {
    }

    table thead {
        margin-bottom: 20px;
    }

    table > button {
        margin-left: 30px;
    }
</style>
<head>
    <title>Product List</title>
</head>
<body>
<div class="row">
    <form id="frmDelete" action="/product?action=delete" method="post">
        <input id="id" name="id" type="hidden"/>
    </form>
    <c:if test="${requestScope.message.equals('success')}">
        <script>
            window.onload = function () {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Success',
                    text: 'Thêm sản phẩm thành công',
                    showConfirmButton: false,
                    timer: 1500
                })
            };
        </script>
    </c:if>
    <c:if test="${requestScope.message.equals('successMsg')}">
        <script>
            window.onload = function () {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Success',
                    text: 'Sản phẩm đã được sửa thành công!',
                    showConfirmButton: false,
                    timer: 1500
                })
            };
        </script>
    </c:if>
</div>

<table class="table col-10 offset-1 ">
    <div class="row justify-content-end">

    </div>

    <thead>
    <tr>
        <th colspan="6"><a href="/product?action=create">
            <button class="btn btn-dark mt-1 mb-1">+Add new Product</button>
        </a></th>
    </tr>
    <tr>
        <th colspan="4" rowspan="">SHOP MANAGER</th>

    </tr>
    <tr>
        <th colspan="6">
            <form class="d-flex justify-content-end" action="/product" method="get">
                <input class="form-control" name="kw" value="${requestScope.kw}" placeholder="Search..."/>
                <select name="producttype" class="form-control ml-2">
                    <option value="-1">All</option>
                    <c:forEach var="c" items="${requestScope.producttypes}">
                        <option value="${c.getId()}"
                                <c:if test="${requestScope.producttype == c.getId()}">selected</c:if>
                        >${c.getName()}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn-dark btn ml-4">Search</button>
            </form>
        </th>
    </tr>
    </thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Description</th>
        <th>Type</th>
        <th>Action</th>
    </tr>
    <tbody>
    <c:forEach items="${requestScope.product}" var="c">
        <tr>
            <td>${c.getId()}</td>
            <td>${c.getName()}</td>
            <td>${c.getQuantity()}</td>
            <td>${c.getPrice()}</td>
            <td>${c.getDescription()}</td>
            <td>
                <c:forEach items="${requestScope.producttypes}" var="ct">
                    <c:if test="${ct.getId() == c.getType()}">
                        ${ct.getName()}
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <a href="/product?action=edit&id=${c.getId()}"><i
                        class="fa-solid fa-pen-ruler btn btn-success w-100 mb-2"></i></a>

                <a href="#" onclick="handleDelete(${c.getId()},'${c.getName()}')"><i
                        class="fa-solid fa-trash-can btn btn-success w-100"></i></a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6">
            <ul class="pagination pagination-split justify-content-end h-100">
                <c:if test="${requestScope.currentPage != 1}">
                    <li class="page-item">
                        <a href="/product?page=${requestScope.currentPage-1}&limit=${requestScope.limit}&kw=${requestScope.kw}&producttypes=${requestScope.producttype}"
                           class="page-link"><i class="fa fa-angle-left">${i} </i></a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="page-item active">
                                <a href="/product?page=${i}&limit=${requestScope.limit}&kw=${requestScope.kw}&producttypes=${requestScope.producttype}"
                                   class="page-link">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="/product?page=${i}&limit=${requestScope.limit}&kw=${requestScope.kw}&producttypes=${requestScope.producttype}"
                                   class="page-link">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <li class="page-item">
                        <a href="/product?page=${requestScope.currentPage+1}&limit=${requestScope.limit}&kw=${requestScope.kw}&producttypes=${requestScope.producttype}"
                           class="page-link"><i class="fa fa-angle-right"></i></a>
                    </li>
                </c:if>
            </ul>

        </td>
    </tr>
    </tbody>

</table>

</body>
<script>
    function handleDelete(id, name) {

        document.getElementById("id").value = id;
        Swal.fire({
            title: 'Bạn có muốn xóa hay không?',
            text: name,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById("frmDelete").submit();
            }
        })
    }
</script>
</html>
