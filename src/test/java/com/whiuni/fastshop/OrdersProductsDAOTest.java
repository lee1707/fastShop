package com.whiuni.fastshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whiuni.fastshop.dao.OrdersProductsDAO;
import com.whiuni.fastshop.vo.OrdersProductsVO;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class OrdersProductsDAOTest {
	@Autowired
	protected OrdersProductsDAO ordersProductsDAO;
	/**
	 * 
	 *orders_products는 foreign key로 구성된 테이블이라 따로 insert, update하지 못함 
	**/
	@Test
	public void insert() {
		OrdersProductsVO ordersProductsVO = new OrdersProductsVO();
		ordersProductsVO.setOrder_id(00);
		ordersProductsVO.setProduct_id(00);
		ordersProductsDAO.insert(ordersProductsVO);
	}
	
	@Test
	public void selectList() {
		List<OrdersProductsVO> ordersProductsList = ordersProductsDAO.selectList();
		
		for(OrdersProductsVO ordersProductsVO : ordersProductsList) {
			System.out.println(ordersProductsVO.getOrder_id());
		}
		
	}
	
	@Test
	public void update() {
		OrdersProductsVO ordersProductsVO = new OrdersProductsVO();
		ordersProductsVO.setOrder_id(11);
		ordersProductsVO.setProduct_id(11);
		ordersProductsVO.setId(1);
		
		ordersProductsDAO.update(ordersProductsVO);
	}
	
	@Test
	public void delete(){
		ordersProductsDAO.delete(1);
	}
}
