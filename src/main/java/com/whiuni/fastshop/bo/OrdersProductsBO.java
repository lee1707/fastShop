package com.whiuni.fastshop.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.whiuni.fastshop.dao.OrderDAO;
import com.whiuni.fastshop.dao.OrdersProductsDAO;
import com.whiuni.fastshop.dao.ProductDAO;
import com.whiuni.fastshop.vo.OrderVO;
import com.whiuni.fastshop.vo.OrdersProductsVO;
import com.whiuni.fastshop.vo.ProductVO;
import com.whiuni.fastshop.vo.UserVO;

public class OrdersProductsBO {
	@Autowired
	protected OrdersProductsDAO ordersProductsDAO;
	
	@Autowired
	protected OrderDAO orderDAO;
	
	@Autowired
	protected ProductDAO productDAO;
	
	public void insert(OrderVO orderVO) {
		orderDAO.insert(orderVO);
	}
	
	public void insert(ProductVO productVO) {
		productDAO.insert(productVO);
	}
	
	/**
	 * 이 메소드는 product, order, ordersProducts 모두에 입력, 삭제합니다.
	 */
	
	public void insertWithProductAndOrder(OrderVO orderVO, ProductVO productVO, OrdersProductsVO ordersProductsVO) {
		orderDAO.insert(orderVO);
		productDAO.insert(productVO);
		ordersProductsDAO.insert(ordersProductsVO);
	}
	
	public void updateWithProductAndOrder(OrderVO orderVO, ProductVO productVO, OrdersProductsVO ordersProductsVO) {
		orderDAO.update(orderVO);
		productDAO.update(productVO);
		ordersProductsDAO.update(ordersProductsVO);
	}
	
	public void deleteWithProductAndOrder(int id) {
		ordersProductsDAO.delete(id);
		productDAO.delete(id);
		orderDAO.delete(id);
	}
	
	public List<OrdersProductsVO> selectList() {
		return ordersProductsDAO.selectList();
	}
	
	/**
	public OrdersProductsVO select(int id) {
		return ordersProductsDAO.select(id);
	}
	
	
	public UserDetailVO selectDetail(int id) {
		return userDetailDAO.select(id);
	}
	**/
}

//userBO.insert();
