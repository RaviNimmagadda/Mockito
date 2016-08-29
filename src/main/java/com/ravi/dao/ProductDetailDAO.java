package com.ravi.dao;

import java.sql.SQLException;

import com.ravi.entity.ProductEntity;

public interface ProductDetailDAO {
	ProductEntity saveProductDetail(ProductEntity entity) throws SQLException;
}
