package com.codegym.displaycustomer;

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

@WebServlet(name = "Servlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private List<Customer> customers;
    @Override
    public void init() throws ServletException {
        customers = new ArrayList<>();
        //long id, String name, Date createAt, String address, String image
        customers.add(new Customer(1L, "Quang Dang", new Date(), "28 NTP", null));
        customers.add(new Customer(2L, "Đình Hâu", new Date(), "28 NTP", null));
        customers.add(new Customer(3L, "Tiến Toàn", new Date(), "28 NTP", null));
        customers.add(new Customer(4L, "Khoa Hâu", new Date(), "28 NTP", null));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", customers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/customer.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

