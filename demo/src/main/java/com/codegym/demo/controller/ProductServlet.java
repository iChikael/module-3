package com.codegym.demo.controller;

import com.codegym.demo.model.Product;
import com.codegym.demo.model.ProductType;
import com.codegym.demo.service.ProductService;
import com.codegym.demo.service.ProductServiceMySQL;
import com.codegym.demo.service.ProductTypeMySQL;
import com.codegym.demo.service.ProductTypeService;
import com.codegym.demo.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private ProductTypeService productTypeService;

    public void init() {
        productService = new ProductServiceMySQL();
        productTypeService = new ProductTypeMySQL();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int limit = 4;
        int page = 1;
        String kw = "";
        int category = -1;
        if (req.getParameter("limit") != null) {
            limit = Integer.parseInt(req.getParameter("limit"));
        }
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getParameter("kw") != null) {
            kw = req.getParameter("kw");
        }
        if (req.getParameter("producttype") != null) {
            category = Integer.parseInt(req.getParameter("producttype"));
        }
        List<Product> products = productService.findByKwAndFilter_Pagging((page - 1) * limit, limit, kw, category);
        int noOfRecords = productService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / limit);
        req.setAttribute("kw", kw);
        req.setAttribute("limit", limit);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        List<ProductType> productTypes = productTypeService.findAll();
        req.setAttribute("product", products);
        req.setAttribute("producttype", category);
        req.setAttribute("producttypes", productTypes);
        String message = null;
        if (req.getParameter("message") != null) {
            message = req.getParameter("message");
        }
        req.setAttribute("message", message);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idProduct = Long.parseLong(req.getParameter("id"));
        List<ProductType> productTypes = productTypeService.findAll();
        req.setAttribute("producttypes", productTypes);
        Product p = productService.findbyId(idProduct);
        req.setAttribute("product", p);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idProduct = Long.parseLong(req.getParameter("id"));
        Product p = productService.findbyId(idProduct);
        List<ProductType> productTypes = productTypeService.findAll();
        req.setAttribute("product", p);
        req.setAttribute("producttypes", productTypes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductType> productTypes = productTypeService.findAll();
        req.setAttribute("producttypes", productTypes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String temp = req.getParameter("id");
        long idProduct = Long.parseLong(req.getParameter("id"));
        productService.remove(idProduct);
        resp.sendRedirect("/product");
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Long idProduct = Long.valueOf(req.getParameter("id"));
        Product product = productService.findbyId(idProduct);
        validateName(product, errors, req);
        try {
            int idProductType = Integer.parseInt(req.getParameter("type"));
            if (idProductType < 1 || idProductType > 5) {
                errors.add("Loại không hợp lệ");
            } else {
                product.setType(idProductType);
            }
        } catch (NumberFormatException e) {
            errors.add("Loại không hợp lệ");
        }
        validateQuantity(product, errors, req);
        validatePrice(product, errors, req);
        validateDescription(product, errors, req);
        if (!errors.isEmpty()) {
            req.setAttribute("errorMessages", errors);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
        } else {
            productService.update(idProduct, product);
            req.setAttribute("successMsg", "Update thành công");
            resp.sendRedirect("/product?message=successMsg");
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Product product = new Product();
        validateName(product, errors, req);
        int idProductType = Integer.parseInt(req.getParameter("productType"));
        product.setType(idProductType);
        validateQuantity(product, errors, req);
        validatePrice(product, errors, req);
        validateDescription(product, errors, req);

        if (!errors.isEmpty()) {
            req.setAttribute("errorMessages", errors);
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
        } else {
            productService.save(product);
            req.getSession().setAttribute("successMsg", "Thêm thành công");
            resp.sendRedirect("/product?message=success");
        }
    }

    private void validateDescription(Product product, List<String> errors, HttpServletRequest req) {
        String description = req.getParameter("description");
        if (!ValidateUtils.isDersciptionValid(description)) {
            errors.add("Mô tả chưa phù hợp");
        }
        product.setDescription(description);
    }


    public void validatePrice(Product product, List<String> errors, HttpServletRequest req) {
        try {
            int price = Integer.parseInt(req.getParameter("price"));
            if (!ValidateUtils.isPriceValid(String.valueOf(price))) {
                errors.add("Giá không phù hợp");
            }
            product.setPrice(price);
        } catch (NumberFormatException e) {
            errors.add("Giá không hợp lệ");
        }
    }


    public void validateQuantity(Product product, List<String> errors, HttpServletRequest req) {
        try {
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            if (!ValidateUtils.isQuantityValid(String.valueOf(quantity))) {
                errors.add("Số lượng không phù hợp");
            }
            product.setQuantity(quantity);
        } catch (NumberFormatException e) {
            errors.add("Số lượng không hợp lệ");
        }
    }

    private void validateName(Product product, List<String> errors, HttpServletRequest req) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên sản phẩm chưa phù hợp");
        }
        product.setName(name);
    }


}
