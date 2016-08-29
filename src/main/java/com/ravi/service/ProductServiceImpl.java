package com.ravi.service;

import java.sql.SQLException;

import com.ravi.dao.ProductDAO;
import com.ravi.dao.ProductDAOImpl;
import com.ravi.dao.ProductDetailDAO;
import com.ravi.dao.ProductDetailDAOImpl;
import com.ravi.entity.ProductEntity;
import com.ravi.service.exception.DuplicateProductException;
import com.ravi.service.exception.ProductException;
import com.ravi.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	private ProductMapper mapper = new ProductMapper();

	private ProductDAO dao = new ProductDAOImpl();

	private ProductDetailDAO detailDao = new ProductDetailDAOImpl();

	public ProductVO saveProduct(ProductVO vo) throws DuplicateProductException {
		if (vo == null || vo.getProductName() == null) {
			throw new IllegalArgumentException("Invalid parameters to save product");
		}

		ProductEntity entity = null;

		try {

			if (vo.getId() != null) {
				entity = this.getDao().find(vo.getId());
			}

			if (entity == null) {
				entity = new ProductEntity();
			}
			entity = mapper.mapToProductEntity(entity, vo);

			if (vo.getId() == null) {
				entity = this.getDao().createProduct(entity);
			} else {
				this.getDao().updateProduct(entity);

			}
			this.getDetailDao().saveProductDetail(entity);
			vo = mapper.mapToProductVO(entity, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}

		return vo;
	}

	ProductDAO getDao() {
		return dao;
	}

	ProductDetailDAO getDetailDao() {
		return detailDao;
	}

}
