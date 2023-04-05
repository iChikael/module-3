package com.codegym.customermanager.controller;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.model.CustomerType;
import com.codegym.customermanager.service.CustomerService;
import com.codegym.customermanager.service.CustomerServiceMysql;
import com.codegym.customermanager.service.CustomerTypeMySQL;
import com.codegym.customermanager.service.CustomerTypeService;
import com.codegym.customermanager.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;
    private CustomerTypeService customerTypeService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceMysql();
        customerTypeService = new CustomerTypeMySQL();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "delete":
                showFormDelete(req, resp);
                break;
            default:
                showList(req, resp);
        }


    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idCustomer = Long.parseLong(req.getParameter("id"));
        Customer c = customerService.findById(idCustomer);

        req.setAttribute("customer", c);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idCustomer = Long.parseLong(req.getParameter("id"));
        Customer c = customerService.findById(idCustomer);

        req.setAttribute("customer", c);
        req.setAttribute("customertypes", customerTypeService.findAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/edit.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
                editCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:
        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String temp = req.getParameter("txtId");
        long idCustomer = Long.parseLong(req.getParameter("txtId"));

        customerService.remove(idCustomer);
        resp.sendRedirect("/customers");
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        long idCustomer = Long.parseLong(req.getParameter("id"));
        
        Customer customer = customerService.findById(idCustomer);
        List<String> errors = new ArrayList<>();

        validateName(customer, errors, req);
        validateEmail(customer, errors, req);
        validateAddress(customer, errors, req);
        validateImage(customer, errors, req);
        validateCustomerType(customer, errors, req);

        if (!errors.isEmpty()) {
            req.setAttribute("customer", customer);
            req.setAttribute("errors", errors);
            req.setAttribute("customertypes", customerTypeService.findAll());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/edit.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            customerService.update(idCustomer, customer);
            resp.sendRedirect("/customers?message=success");
        }

    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Customer customer = new Customer();

        validateName(customer, errors, req);
        validateEmail(customer, errors, req);
        validateAddress(customer, errors, req);
        validateImage(customer, errors, req);
        validateCustomerType(customer, errors, req);
//        validateCustomerType(customer, e)

        if(!errors.isEmpty()){
            req.setAttribute("customer", customer);
            req.setAttribute("errors", errors);
        }else{
            req.setAttribute("message", "Thêm thành công");
            customerService.save(customer);
        }
        req.setAttribute("customertypes", customerTypeService.findAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void validateCustomerType(Customer customer, List<String> errors, HttpServletRequest req) {
        try {
            long idCustomerType = Long.parseLong(req.getParameter("sCustomerTypes"));
            CustomerType customerType = customerTypeService.findById(idCustomerType);
            if (customerType == null) {
                errors.add("Loại khách hàng không có trong database");
            }else{
                customer.setCustomerType(idCustomerType);
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Loại khách hàng không hợp lệ");
        }

    }

    private void validateImage(Customer customer, List<String> errors, HttpServletRequest req) {
        String image = req.getParameter("txtImage");
        customer.setImage(image);
    }

    private void validateAddress(Customer customer, List<String> errors, HttpServletRequest req) {
        String address = req.getParameter("txtAddress");
        customer.setAddress(address);
    }

    private void validateEmail(Customer customer, List<String> errors, HttpServletRequest req) {
        String email = req.getParameter("txtEmail");
        if(!ValidateUtils.isEmailValid(email)){
            errors.add("Email không hợp lệ");
        }
        customer.setEmail(email);
    }

    private void validateName(Customer customer, List<String> errors,  HttpServletRequest req) {
        String name = req.getParameter("txtName");
        if(!ValidateUtils.isNameValid(name)){
            errors.add("Tên không hợp lệ. Phải từ 8-20 kí tự, bắt đầu phải là chữ");
        }
        customer.setName(name);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = 3;
        int page = 1;
        String kw = "";
        int customertype = -1;
        if (req.getParameter("limit") != null) {
            limit  = Integer.parseInt(req.getParameter("limit"));
        }
        if (req.getParameter("page") != null) {
            page  = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getParameter("kw") != null) {
            kw  = req.getParameter("kw");
        }
        if (req.getParameter("customertype") != null) {
            customertype  = Integer.parseInt(req.getParameter("customertype"));
        }
        String message = req.getParameter("message");
        List<Customer> customers = customerService.findByKwAndFilter_Pagging((page - 1) * limit, limit, kw, customertype);
        int noOfRecords = customerService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / limit);
        req.setAttribute("kw", kw);
        req.setAttribute("limit", limit);
        req.setAttribute("customertype", customertype);
        req.setAttribute("customers", customers);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("customertypes", customerTypeService.findAll());
        req.setAttribute("message", message);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/customers.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("customertypes", customerTypeService.findAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/customer/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
