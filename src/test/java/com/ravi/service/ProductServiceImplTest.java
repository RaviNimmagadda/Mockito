package com.ravi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;

import org.mockito.runners.MockitoJUnitRunner;

import com.ravi.dao.ProductDAO;
import com.ravi.dao.ProductDetailDAO;
import com.ravi.entity.ProductEntity;
import com.ravi.service.exception.ProductException;
import com.ravi.vo.ProductVO;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	@Mock
	private ProductDAO dao;

	@Mock
	private ProductDetailDAO detailDao;

	@Spy
	private ProductServiceImpl impl;

	private ProductVO input;

	@Before
	public void setUp() {
		when(impl.getDao()).thenReturn(dao);
		when(impl.getDetailDao()).thenReturn(detailDao);
		input = ProductData.createProduct();
	}

	private void verifyResult(ProductVO result) {
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(input.getProductName(), result.getProductName());
		assertEquals(input.getDescription(), result.getDescription());
	}

	@Test
	public void testCreateProduct() throws SQLException {
		// ProductDAO dao = mock(ProductDAO.class);
		// ProductDetailDAO detailDao = mock(ProductDetailDAO.class);

		when(dao.createProduct(any(ProductEntity.class))).thenReturn(ProductData.createProductEntity());

		// ProductVO input = ProductData.createProduct();
		// ProductServiceImpl impl = new ProductServiceImpl();
		// impl.setDao(dao);
		// impl.setDetailDao(detailDao);

		ProductVO result = impl.saveProduct(input);
		verifyResult(result);
		verify(dao).createProduct(any(ProductEntity.class));
		verify(dao, never()).find(any(Long.class));
		verify(detailDao).saveProductDetail(any(ProductEntity.class));

	}

	@Test
	public void testUpdateProduct() throws SQLException {
		// ProductDAO dao = mock(ProductDAO.class);
		// ProductDetailDAO detailDao = mock(ProductDetailDAO.class);

		when(dao.find(anyLong())).thenReturn(ProductData.createProductEntity());

		// ProductVO input = ProductData.createProduct();

		input.setId(new Long(1000));
		input.setProductName("My Product Name");
		// ProductServiceImpl impl = new ProductServiceImpl();
		// impl.setDao(dao);
		// impl.setDetailDao(detailDao);

		ProductVO result = impl.saveProduct(input);
		verifyResult(result);
		verify(dao).find(any(Long.class));
		verify(dao).updateProduct(any(ProductEntity.class));
		verify(detailDao).saveProductDetail(any(ProductEntity.class));
	}

	@Test
	public void testUpdateProductInOrder() throws SQLException {
		// ProductDAO dao = mock(ProductDAO.class);
		// ProductDetailDAO detailDao = mock(ProductDetailDAO.class);

		when(dao.find(anyLong())).thenReturn(ProductData.createProductEntity());
		InOrder order = inOrder(dao, detailDao);

		// ProductVO input = ProductData.createProduct();

		input.setId(new Long(1000));
		input.setProductName("My Product Name");
		// ProductServiceImpl impl = new ProductServiceImpl();
		// impl.setDao(dao);
		// impl.setDetailDao(detailDao);

		ProductVO result = impl.saveProduct(input);
		verifyResult(result);
		order.verify(dao).find(any(Long.class));
		order.verify(dao).updateProduct(any(ProductEntity.class));
		order.verify(detailDao).saveProductDetail(any(ProductEntity.class));
	}

	private InOrder inOrder(ProductDAO dao2, ProductDetailDAO detailDao2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test(expected = ProductException.class)
	public void testUpdateProductWithException() throws SQLException {

		when(dao.find(anyLong())).thenThrow(new SQLException("No Record Found"));

		input.setId(new Long(2000));
		input.setProductName("My Product Name");
		impl.saveProduct(input);

	}

	@Test(expected = IllegalArgumentException.class)
	public void tetSaveProductNullProductName() throws SQLException {

		ProductVO input = ProductData.createProduct();
		input.setProductName(null);
		ProductServiceImpl impl = new ProductServiceImpl();
		impl.saveProduct(input);

	}

}
