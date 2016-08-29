/**
 * 
 */
package com.ravi.service;

import com.ravi.entity.ProductEntity;
import com.ravi.vo.ProductVO;

public class ProductData {

	public static ProductVO createProduct() {
		ProductVO result = new ProductVO();
		result.setProductName("HP Laptop 1.2");
		result.setDescription("HP Laptop");
		return result;
	}

	public static ProductEntity createProductEntity() {
		ProductEntity result = new ProductEntity();
		result.setProductName("HP Laptop 1.2");
		result.setDescription("HP Laptop");
		result.setId(new Long(1000));
		return result;
	}

}
