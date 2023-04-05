<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Form Elements | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
        <jsp:include page="/WEB-INF/admin/layout/css_head.jsp"></jsp:include>

    </head>

    <body data-layout="horizontal">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Navigation Bar-->
            <jsp:include page="/WEB-INF/admin/layout/topnav.jsp"></jsp:include>
                <!-- End Navigation Bar-->

            <!-- ============================================================== -->
            <!-- Start Page Content here -->
            <!-- ============================================================== -->

            <div class="content-page">
                <div class="content">

                    <!-- Start Content-->
                    <div class="container-fluid">

                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Zircos</a></li>
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Forms</a></li>
                                            <li class="breadcrumb-item active">Form elements</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Form elements</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <div class="row">
                           <div class="col-6">
                               <c:if test="${requestScope.errors!=null}">
                                   <div class="alert alert-danger" role="alert">
                                       <ul>
                                           <c:forEach items="${requestScope.errors}" var="e">
                                               <li>${e}</li>
                                           </c:forEach>
                                       </ul>
                                   </div>
                               </c:if>
                                <c:if test="${requestScope.message !=null}">
                                    <script>
                                        window.onload = function () {
                                            Swal.fire({
                                                position: 'top-end',
                                                icon: 'success',
                                                title: 'Thêm thành công',
                                                showConfirmButton: false,
                                                timer: 1500
                                            })
                                        };
                                    </script>
                                </c:if>
                               <form class="form-horizontal" method="post">
                                   <div class="form-group row">
                                       <label class="col-md-2 control-label">Name</label>
                                       <div class="col-md-10">
                                           <input name="txtName" value="${requestScope.customer.getName()}" type="text" class="form-control" value="">
                                       </div>
                                   </div>
                                   <div class="form-group row">
                                       <label class="col-md-2 control-label" for="example-email">Email</label>
                                       <div class="col-md-10">
                                           <input type="email" value="${requestScope.customer.getEmail()}" name="txtEmail"  id="example-email" name="example-email" class="form-control" >
                                       </div>
                                   </div>
                                   <div class="form-group row">
                                       <label class="col-md-2 control-label">Address</label>
                                       <div class="col-md-10">
                                           <input type="text" value="${requestScope.customer.getAddress()}" name="txtAddress"  class="form-control">
                                       </div>
                                   </div>

                                   <div class="form-group row">
                                       <label class="col-md-2 control-label">Image</label>
                                       <div class="col-md-10">
                                           <input type="text" value="${requestScope.customer.getImage()}" class="form-control" name="txtImage">
                                       </div>
                                   </div>
                                   <div class="form-group row">
                                       <label class="col-md-2 control-label">Customer Type</label>
                                       <div class="col-md-10">
                                           <select name="sCustomerTypes" class="form-control">
                                               <c:forEach items="${requestScope.customertypes}" var="c">
                                                   <option value="${c.getId()}">${c.getName()}</option>
                                               </c:forEach>
                                           </select>
                                       </div>
                                   </div>
                                   <div class="form-group row">
<%--                                       <label class="col-md-2 control-label"></label>--%>
                                       <div class="col-md-10 offset-2">
                                           <button type="submit" class="btn btn-info waves-effect waves-light">Sign in</button>
                                       </div>
                                   </div>

                               </form>
                           </div>
                        </div>
                        <!-- end row -->

                    </div>
                    <!-- end container-fluid -->

                </div>
                <!-- end content -->

                

                <!-- Footer Start -->
                <jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>
                <!-- end Footer -->

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->

        </div>
        <!-- END wrapper -->

        <!-- Right Sidebar -->
        <jsp:include page="/WEB-INF/admin/layout/right_bar.jsp"></jsp:include>

        <!-- Vendor js -->
        <jsp:include page="/WEB-INF/admin/layout/js_footer.jsp">
            <jsp:param name="page" value="create"/>
        </jsp:include>

    </body>

</html>