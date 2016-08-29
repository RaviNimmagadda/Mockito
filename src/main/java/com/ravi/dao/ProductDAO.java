package com.ravi.dao;

import java.sql.SQLException;

import com.ravi.entity.ProductEntity;

public interface ProductDAO {

	ProductEntity createProduct(ProductEntity entity) throws SQLException;

	ProductEntity updateProduct(ProductEntity entity) throws SQLException;

	ProductEntity find(Long id) throws SQLException;

	void remove(ProductEntity entity) throws SQLException;
}
