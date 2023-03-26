package com.codegym.Product;

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

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private List<Product> products;

    public void init() throws ServletException {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bia", new Date(), "10000", "15000"));
        products.add(new Product(2L, "Ruou", new Date(), "5000", "10000"));
        products.add(new Product(3L, "Banh", new Date(), "1000", "3000"));
        products.add(new Product(4L, "Keo", new Date(), "5000", "1000"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceIn = request.getParameter("priceIn");
        String priceOut = request.getParameter("priceOut");
        Date createdDate = new Date();

        Product newProduct = new Product(products.size() + 1, name, createdDate, priceIn, priceOut);
        products.add(newProduct);

        response.sendRedirect("/products");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String priceIn = request.getParameter("priceIn");
        String priceOut = request.getParameter("priceOut");
        Date createdDate = new Date();

        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(name);
                product.setPriceIn(priceIn);
                product.setPriceOut(priceOut);
                product.setCreatedDate(createdDate);
                break;
            }
        }

        response.sendRedirect("/products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                break;
            }
        }

        response.sendRedirect("/products");
    }
}
