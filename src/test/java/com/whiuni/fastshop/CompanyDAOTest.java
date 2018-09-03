package com.whiuni.fastshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whiuni.fastshop.dao.CompanyDAO;
import com.whiuni.fastshop.vo.CompanyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CompanyDAOTest {
	@Autowired
	protected CompanyDAO companyDAO;
	
	@Test
	public void insert() {
		CompanyVO companyVO = new CompanyVO();
		companyVO.setName("whiuni");
		companyVO.setPhone("12345");
		companyVO.setFax("00001");
		companyVO.setEmail("hello@gmail.com");
		
		companyDAO.insert(companyVO);
	}
	
	@Test
	public void selectList() {
		List<CompanyVO> companyList = companyDAO.selectList();
		
		for(CompanyVO companyVO : companyList) {
			System.out.println(companyVO.getName());
		}
	}

	@Test
	public void update() {
		CompanyVO companyVO = new CompanyVO();
		companyVO.setName("white");
		companyVO.setPhone("11111");
		companyVO.setFax("77777");
		companyVO.setEmail("updated");
	
		companyVO.setId(1);
		companyDAO.update(companyVO);
	}
	
	@Test
	public void delete() {
		companyDAO.delete(1);
	}
}