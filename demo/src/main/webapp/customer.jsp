<%@ page import="com.codegym.displaycustomer.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23/3/2023
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Hình ảnh</th>
    </tr>
    </thead>
    <tbody>

    <%--    <%--%>
    <%--        List<Customer> customers = (List<Customer>) request.getAttribute("customers");--%>
    <%--        for(int i=0;i<customers.size();i++){--%>
    <%--            String name = customers.get(i).getName();--%>
    <%--            String address = customers.get(i).getAddress();--%>
    <%--            Date date = customers.get(i).getCreateAt();--%>
    <%--            String image = customers.get(i).getImage();--%>
    <%--            String str = String.format("<tr>\n" +--%>
    <%--                    "            <td>%s</td>\n" +--%>
    <%--                    "            <td>%s</td>\n" +--%>
    <%--                    "            <td>%s</td>\n" +--%>
    <%--                    "            <td>%s</td>\n" +--%>
    <%--                    "        </tr>", name, address, date, image);--%>
    <%--            out.println(str);--%>
    <%--        }--%>
    <%--    %>--%>

    <c:forEach items="${requestScope.customers}" var="c">

        <c:choose>
            <c:when test="${c.getId()%2==0}">
                <tr style="color: red">
                    <td>${c.getName()}</td>
                    <td>${c.getCreateAt()}</td>
                    <td>${c.getAddress()}</td>
                    <td>${c.getImage()}</td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>${c.getName()}</td>
                    <td>${c.getCreateAt()}</td>
                    <td>${c.getAddress()}</td>
                    <td>${c.getImage()}</td>
                </tr>
            </c:otherwise>
        </c:choose>

    </c:forEach>
    </tbody>

</table>
</body>
</html>
