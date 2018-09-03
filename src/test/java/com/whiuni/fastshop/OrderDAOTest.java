package com.whiuni.fastshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whiuni.fastshop.dao.OrderDAO;
import com.whiuni.fastshop.vo.OrderVO;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class OrderDAOTest {
	@Autowired
	protected OrderDAO orderDAO;
	
	@Test
	public void insert() {
		OrderVO orderVO = new OrderVO();
		orderVO.setUserId(1);
		orderVO.setProductId("1");
		orderVO.setAddress("address");
		orderVO.setProductName("name");
		orderVO.setPrice("000");
		orderVO.setImageUrl("000");
		
		System.out.println();
		System.out.println("this is log");
		
		System.out.println(orderVO);
		orderDAO.insert(orderVO);
	}
	
	@Test
	public void selectList() {
		List<OrderVO> orderList = orderDAO.selectList();
		
		for(OrderVO orderVO : orderList) {
			System.out.println(orderVO.getAddress());
		}
	}

	@Test
	public void update() {
		OrderVO orderVO = new OrderVO();
		orderVO.setProductId("111");
		orderVO.setAddress("bbb");
		orderVO.setPrice("111");
		orderVO.setImageUrl("111");
	
		orderVO.setId(1);
		orderDAO.update(orderVO);
	}
	
	@Test
	public void delete() {
		orderDAO.delete(1);
	}
}