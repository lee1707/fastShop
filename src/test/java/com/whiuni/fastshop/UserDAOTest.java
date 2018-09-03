package com.whiuni.fastshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.whiuni.fastshop.dao.UserDAO;
import com.whiuni.fastshop.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserDAOTest {
	@Autowired
	protected UserDAO userDAO;
	
	@Test
	public void insert() {
		UserVO userVO = new UserVO();
		userVO.setUsername("whiuni");
		userVO.setPasswd("PASSWORD");
		userVO.setPoint(100);
		userVO.setCoupon("NONE");
		userVO.setEmail("test@fastshop.com");
		
		userDAO.insert(userVO);
	}
	
	@Test
	public void selectList() {
		List<UserVO> userList = userDAO.selectList();
		
		for(UserVO userVO : userList) {
			System.out.println(userVO.getUsername());
		}
	}

	@Test
	public void update() {
		UserVO userVO = new UserVO();
		userVO.setUsername("whiuni");
		userVO.setPasswd("12345");
		userVO.setPoint(200);
		userVO.setCoupon("TWO");
		userVO.setEmail("hello.naver.com");
		userVO.setId(1);
		userDAO.update(userVO);
	}
	
	@Test
	public void delete() {
		userDAO.delete(1);
	}
}