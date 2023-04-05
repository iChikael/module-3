<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/admin/assets\js\vendor.min.js"></script>

<c:if test="${param.page == 'index'}">
    <script src="/admin/assets\libs\morris-js\morris.min.js"></script>
    <script src="/admin/assets\libs\raphael\raphael.min.js"></script>

    <script src="/admin/assets\js\pages\dashboard.init.js"></script>
</c:if>

<!-- App js -->
<script src="/admin/assets\js\app.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.min.js" integrity="sha512-eN8dd/MGUx/RgM4HS5vCfebsBxvQB2yI0OS5rfmqfTo8NIseU+FenpNoa64REdgFftTY4tm0w8VMj5oJ8t+ncQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>