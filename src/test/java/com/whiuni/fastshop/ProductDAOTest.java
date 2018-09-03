package com.whiuni.fastshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whiuni.fastshop.dao.ProductDAO;
import com.whiuni.fastshop.vo.ProductVO;
import com.whiuni.fastshop.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ProductDAOTest {
	@Autowired
	protected ProductDAO productDAO;
	
	@Test
	public void insert() {
		ProductVO productVO = new ProductVO();
		productVO.setImage_url("000");
		productVO.setName("aaa");
		productVO.setPrice(000);
		productVO.setDescription("000");
		//productVO.setCompany_id(000);
		
		productDAO.insert(productVO);
	}
	
	@Test
	public void selectList() {
		List<ProductVO> productList = productDAO.selectList();
		
		for(ProductVO productVO : productList) {
			System.out.println(productVO.getDescription());
		}
	}

	@Test
	public void update() {
		ProductVO productVO = new ProductVO();
		productVO.setImage_url("111");
		productVO.setName("bbb");
		productVO.setPrice(111);
		productVO.setDescription("111");
		productVO.setCompany_id(111);
		
		productDAO.update(productVO);
	}
	
	@Test
	public void delete() {
		productDAO.delete(1);
	}
}