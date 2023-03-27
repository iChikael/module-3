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

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private List<Product> products;
    public ProductService productService;

//    public void init() throws ServletException {
//        productService = new ProductServiceMySQL() {
//        };
//    }


    public void init() throws ServletException {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bia", "10000", "15000","dadsas"));
        products.add(new Product(2L, "Ruou",  "5000", "10000","dadsas"));
        products.add(new Product(3L, "Banh","1000", "3000","dadsas"));
        products.add(new Product(4L, "Keo",  "5000", "1000","dadsas"));
    }

    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("products", products);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/product.jsp");
//        requestDispatcher.forward(req, resp);
//        String action = req.getParameter("action");
//        if(action == null){
//            action ="";
//        }
//        switch (action){
//            case "create":
//                showFormCreate(req,resp);
//                break;
//            case "edit":
//                showFormEdit(req,resp);
//                break;
//            case "delete":
//                showFormDelete(req,resp);
//                break;
//            default:
//                showList(req,resp);
//        }
//    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create":
                showFormCreate(req,resp);
                break;
            case "edit":
                showFormEdit(req,resp);
                break;
            case "delete":
                showFormDelete(req,resp);
                break;
            default:
                showList(req,resp);
        }
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idProducts = Long.parseLong(req.getParameter("id"));
        Product c = productService.findById(idProducts);

        req.setAttribute("product", c);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(req, resp);
    }


    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idProducts = Long.parseLong(req.getParameter("id"));
        Product c = productService.findById(idProducts);

        req.setAttribute("product", c);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(req, resp);

    }
    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/creat.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("product", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/product.jsp");
        requestDispatcher.forward(req, resp);
    }

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
                editProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                showList(req,resp);
        }
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("txtName");
        String quantity = req.getParameter("txtEmail");
        String price = req.getParameter("txtAddress");
        String description = req.getParameter("txtImage");

        Product product = new Product(
                productService.maxId() + 1, name, quantity, price, description);
        productService.save(product);

        req.setAttribute("message", "Thêm thành công");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/creat.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("txtName");
        String quantity = req.getParameter("txtEmail");
        String price = req.getParameter("txtAddress");
        String description = req.getParameter("txtImage");

        long idProduct = Long.parseLong(req.getParameter("id"));

        Product product = productService.findById(idProduct);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDescription(description);

        productService.update(idProduct, product);

//        showList(req, resp);


        resp.sendRedirect("/product.jsp");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long idProduct = Long.parseLong(req.getParameter("id"));

        productService.remove(idProduct);
        resp.sendRedirect("/product.jsp");
    }
}
