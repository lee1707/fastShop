package com.whiuni.fastshop;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.whiuni.fastshop.dao.UserDAO;
import com.whiuni.fastshop.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
		
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/admin/users/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		
		//userDAO�� list�� �����ͼ� userlist�� ����
		List<UserVO> userList = userDAO.selectList();
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		model.addAttribute("userList", userList);
		
		
		return "/admin/users/list";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	@RequestMapping(value = "/admin/users/info", method = RequestMethod.GET)
	public String info(@RequestParam("id") int id, @RequestParam("username") String username, Locale locale, Model model) {
		
		//userDAO�� list�� �����ͼ� userlist�� ����
		List<UserVO> userList = userDAO.selectList();
		
		//�ϳ��� �������� �Ÿ�
		//UserVO user = userDAO.select(id);
		//model.addAttribute("id", id);
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		model.addAttribute("userList", userList);
		model.addAttribute("id", id);
		model.addAttribute("username",username);
		
		
		return "/admin/users/list";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
}





