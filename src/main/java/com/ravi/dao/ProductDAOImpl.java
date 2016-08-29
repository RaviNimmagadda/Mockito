package com.ravi.dao;

import java.sql.SQLException;

import com.ravi.entity.ProductEntity;

public class ProductDAOImpl implements ProductDAO {

	public ProductEntity find(Long id) throws SQLException {
		throw new SQLException("This is an error method");
	}

	public void remove(ProductEntity entity) throws SQLException {
		throw new SQLException("This is an error method");
	}

	public ProductEntity createProduct(ProductEntity entity) throws SQLException {
		throw new SQLException("This is an error method");
	}

	public ProductEntity updateProduct(ProductEntity entity) throws SQLException {
		throw new SQLException("This is an error method");
	}

}
