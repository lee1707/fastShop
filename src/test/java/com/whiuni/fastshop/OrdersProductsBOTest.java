package com.whiuni.fastshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whiuni.fastshop.bo.OrdersProductsBO;
import com.whiuni.fastshop.dao.OrderDAO;
import com.whiuni.fastshop.dao.OrdersProductsDAO;
import com.whiuni.fastshop.dao.ProductDAO;
import com.whiuni.fastshop.vo.OrderVO;
import com.whiuni.fastshop.vo.OrdersProductsVO;
import com.whiuni.fastshop.vo.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class OrdersProductsBOTest {

	@Autowired
	protected OrderDAO orderDAO;
	
	@Autowired
	protected OrdersProductsDAO ordersProductsDAO;
	
	@Autowired
	protected ProductDAO productDAO;
	
	@Autowired
	protected OrdersProductsBO ordersProductsBO;
	
	@Test
	public void insert() {
		ProductVO productVO = new ProductVO();
		OrdersProductsVO ordersProductsVO = new OrdersProductsVO();
		
		List<OrderVO> orderList = orderDAO.selectList();
		OrderVO orderVO = orderList.get(0);
		
		//¼öÁ¤Áß..
		
		orderVO.setUserId(1);
		orderVO.setProductId("1");
		orderVO.setAddress("address");
		orderVO.setProductName("name");
		orderVO.setPrice("000");
		orderVO.setImageUrl("000");
		orderDAO.insert(orderVO);
		
		productVO.setImage_url("000");
		productVO.setName("aaa");
		productVO.setPrice(000);
		productVO.setDescription("000");
		productDAO.insert(productVO);
		
		ordersProductsDAO.insert(ordersProductsVO);
		
		ordersProductsBO.insertWithProductAndOrder(orderVO, productVO, ordersProductsVO);
	}
	

	@Test
	public void selectList() {
		List<OrdersProductsVO> ordersProductsList = ordersProductsDAO.selectList();
		
		for ( OrdersProductsVO ordersProductsVO : ordersProductsList ) {
			System.out.println(ordersProductsVO.getId());
			
			//assertTrue(ordersProductsVO instanceof OrdersProductsVO );
			
		}
		
	}
	
	@Test
	public void update() {
		OrdersProductsVO ordersProductsVO = new OrdersProductsVO();
		ordersProductsVO.setOrder_id(1);
		ordersProductsVO.setProduct_id(1);
		ordersProductsVO.setId(2);
		
		ordersProductsDAO.update(ordersProductsVO);;
	}
	
	@Test
	public void delete() {
		orderDAO.delete(2);
		productDAO.delete(2);
		ordersProductsDAO.delete(2);
	}
}
