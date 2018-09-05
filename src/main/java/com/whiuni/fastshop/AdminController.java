package com.whiuni.fastshop;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whiuni.fastshop.dao.UserDAO;
import com.whiuni.fastshop.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"sessionUsername", "sessionEmail"})
public class AdminController {
		
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	////////////////////////////////////�� �Ʒ��κ� ���ľߵ�~~~~~~~~~~~~~~~~~
	@RequestMapping(value = "/admin/users/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
			@ModelAttribute("sessionUsername") String sessionUsername
			, @ModelAttribute("sessionUsername") String sessionEmail
			, @RequestParam(required=false, value="username") String username
			, Locale locale
			, Model model) {
	
				//�α��� �����޴°��� ���� �ٸ��� ������
				
				//userDAO�� list�� �����ͼ� userlist�� ����
		List<UserVO> userList = userDAO.selectList();
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		model.addAttribute("userList", userList);
		
		
		return "/admin/users/list";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	@RequestMapping(value = "/admin/users/info", method = RequestMethod.GET)
	public String info(
			@ModelAttribute("sessionUsername") String sessionUsername
			, @RequestParam(value="id") int id
			, @RequestParam(required=false, value="username") String username
			, Locale locale
			, Model model) {
		
		UserVO user = userDAO.select(id);
		
		model.addAttribute("userVO", user);
		
		System.out.println(sessionUsername);
		
		//userDAO�� list�� �����ͼ� userlist�� ����
		//List<UserVO> userList = userDAO.selectList();
		
		//�ϳ��� �������� �Ÿ�
		//UserVO user = userDAO.select(id);
		//model.addAttribute("id", id);
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		//model.addAttribute("userList", userList);
		//model.addAttribute("id", id);
		//model.addAttribute("username",username);
		
		
		return "/admin/users/info";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}

	@RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
	public String add(Locale locale, Model model) {
		
		//�ȿ� �ڵ� ���� ȭ�鿡 jsp������ �ѷ��ִ� ������ ��
		return "admin/users/add";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	@RequestMapping(value = "/admin/users/doAdd", method = RequestMethod.POST)
	public String doAdd(
		//username�� �����ðǵ� String�� username���� �����Ұž�
		@RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		, @RequestParam(value="point") int point
		, @RequestParam(value="coupon") String coupon
		, @RequestParam(value="email") String email
		, @ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		model.addAttribute("username", username);
		model.addAttribute("passwd", passwd);
		model.addAttribute("point", point);
		model.addAttribute("coupon", coupon);
		model.addAttribute("email", email);
		
		UserVO userVO = new UserVO();
		userVO.setUsername(username);
		userVO.setPasswd(passwd);
		userVO.setPoint(point);
		userVO.setCoupon(coupon);
		userVO.setEmail(email);
		userDAO.insert(userVO);
		
		return "/admin/users/doAdd";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	@RequestMapping(value = "/admin/users/edit", method = RequestMethod.GET)
	public String edit(
			@ModelAttribute("sessionUsername") String sessionUsername
			, @RequestParam(value="id") int id
			, @RequestParam(required=false, value="username") String username
			, Locale locale
			, Model model) {
		//userDAO�� list�� �����ͼ� userlist�� ����
		List<UserVO> userList = userDAO.selectList();
		
		//�ϳ��� �������� �Ÿ�
		UserVO user = userDAO.select(id);
		
		model.addAttribute("userVO", user);
		
		System.out.println(sessionUsername);
		//model.addAttribute("id", id);
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		//model.addAttribute("userList", userList);
		//model.addAttribute("id", id);
		//model.addAttribute("username",username);
		
		
		return "/admin/users/edit";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	@RequestMapping(value = "/admin/users/doEdit", method = RequestMethod.POST)
	public String doAdd(
		//username�� �����ðǵ� String�� username���� �����Ұž�
			@RequestParam(value="id") int id
		, @RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		, @RequestParam(value="point") int point
		, @RequestParam(value="coupon") String coupon
		, @RequestParam(value="email") String email
		, @ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		//jsp������ �Ѱ��شٴ� �ǹ�
		model.addAttribute("username", username);
		model.addAttribute("passwd", passwd);
		model.addAttribute("point", point);
		model.addAttribute("coupon", coupon);
		model.addAttribute("email", email);
		
		UserVO userVO = new UserVO();
		userVO.setUsername(username);
		userVO.setPasswd(passwd);
		userVO.setPoint(point);
		userVO.setCoupon(coupon);
		userVO.setEmail(email);
	
		userDAO.update(userVO);
		
		return "/admin/users/doAdd";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	

	@RequestMapping(value = "/admin/users/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		//���� ����̶� �ȸ����� �α��ν���~~~ �����̷�Ʈ�ѤѤѤѤ�
		
		//�ȿ� �ڵ� ���� ȭ�鿡 jsp������ �ѷ��ִ� ������ ��
		return "admin/users/login";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	/////////////////���Ƥ����������������������������������������������������������������������������������������������������Ƥ�����������������������
	@RequestMapping(value = "/admin/users/doLogin", method = RequestMethod.POST)
	public String doLogin(
		//username�� �����ðǵ� String�� username���� �����Ұž�
		, @RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		
		, @ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		
		UserVO userVO = userDAO.selectByUsername(username);
		if (userVO.getPasswd().equals(passwd)) {
			model.addAttribute("sessionUsername", userVO.getUsername());
			model.addAttribute("sessionUsername", userVO.getUsername());
			//DB���� ������ userVO.getEmail ���� ���� ������ �����ϰ� ��
		}else {
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionUsername", "");
		}
		
		
		return "/admin/users/doLogin";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	@RequestMapping(value = "/admin/users/doLogout", method = RequestMethod.GET)
	public String doLogout(
		//username�� �����ðǵ� String�� username���� �����Ұž�
		@ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionEmail", "");
		
		//���⵵ �����̷�����������������������Ʈ
		
		return "redirect:/admin/users/doLogout";
		//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
}





