<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23/3/2023
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    public void jspInit(){
        System.out.println("Init JSP...............");
    }

%>

    <h1>Từ điển dùng JSP ....</h1>
    <form action="/translate" method="post">
        <div>
            <input type="text" name="txtSearch" value="<%=request.getAttribute("search")!=null ? request.getAttribute("search") : ""%>" />
            <button>Dịch</button>
        </div>
        <label>Kết quả: <%=request.getAttribute("result")!=null ? request.getAttribute("result") : ""%></label>
    </form>
</body>
</html>
