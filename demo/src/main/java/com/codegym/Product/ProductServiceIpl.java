package com.codegym.Product;

import java.util.*;

public class ProductServiceIpl implements ProductService{

    private Map<Long, Product> products;

//    public void ProductServiceImpl() {
//        products = new HashMap<>();
//        products.put(1L, new Product(1L, "Product 1", new Date(), "10", "20.0"));
//        products.put(2L, new Product(2L, "Product 2", new Date(), "20", "30.0"));
//        products.put(3L, new Product(3L, "Product 3", new Date(), "30", "40.0"));
//        products.put(4L, new Product(4L, "Product 4", new Date(), "40", "50.0"));
//    }


    @Override
    public List<Product> findAll() {
        return  new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(long id) {
        return products.get(id);
    }

    @Override
    public void update(long id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(long id) {
        products.remove(id);
    }

    @Override
    public long getMaxId() {
        return 0;
    }

    @Override
    public long maxId() {
        List<Product> products = findAll();
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() == o2.getId()) {
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        Product lastProduct = products.get(products.size() - 1);
        return lastProduct.getId();
    }

}
