package com.boat.mapper;

import com.boat.entity.Product;

import java.util.List;

public interface ProductMapper {
    void addProduct(Product product);
    void deleteProduct(int id);
    void updateProduct(Product product);
    Product checkProduct(String name);
    List<Product> list();
}
