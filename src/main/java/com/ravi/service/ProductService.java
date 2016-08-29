package com.ravi.service;

import java.sql.SQLException;

import com.ravi.service.exception.DuplicateProductException;
import com.ravi.vo.ProductVO;

public interface ProductService {
	ProductVO saveProduct(ProductVO vo) throws DuplicateProductException,
			SQLException;
}
