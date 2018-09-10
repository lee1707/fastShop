package com.whiuni.fastshop;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whiuni.fastshop.dao.UserDAO;
import com.whiuni.fastshop.vo.UserVO;


@Controller
@SessionAttributes({"sessionUsername","sessionEmail"})
public class AdminController {
		
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/admin/users/list", method = RequestMethod.GET)
	public String list(
			@SessionAttribute(required=false, value="sessionUsername") String sessionUsername
			, @SessionAttribute(required=false, value="sessionEmail") String sessionEmail
			, @RequestParam(value="username", defaultValue="") String username
			, @RequestParam(value="id", defaultValue="0") int id
			, Locale locale
			, Model model) {
	
			//�α��� �����޴°��� ���� �ٸ��� ������
			if(sessionUsername.equals("")) {
				return "redirect:/admin/login/login";
			}
			
			List<UserVO> userList = userDAO.selectList();
	
			model.addAttribute("userList", userList);
		
		
			return "/admin/users/list";
			//return�� "home"�ϰ�� home�� view�Ʒ����� home.jsp������ ã�ƶ� ��� �ǹ���. home.jsp������ ��µ�.
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin/users/info", method = RequestMethod.GET)
	public String info(
			@ModelAttribute("sessionUsername") String sessionUsername
			, @RequestParam(value="id") int id
			, @RequestParam(required=false, value="username") String username
			, Model model) {
		
		UserVO user = userDAO.select(id);
		
		model.addAttribute("userVO", user);

		System.out.println(sessionUsername);
		
		return "admin/users/info";
	}

	@RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
	public String add(
			@ModelAttribute("sessionUsername") String sessionUsername
			, Model model) {
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
		
		//�ϳ��� �������� �Ÿ�
		UserVO user = userDAO.select(id);
		
		model.addAttribute("userVO", user);
		
		System.out.println(sessionUsername);
		
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
	
		userDAO.insert(userVO);
		
		return "/admin/users/doAdd";
	}
	

	@RequestMapping(value = "/admin/login/login", method = RequestMethod.GET)
	public String login(
			@SessionAttribute(required=false, value="sessionUsername") String sessionUsername
			, Model model) {
		
		if (sessionUsername == null || sessionUsername.equals("")) {
			
		}else {
			return "redirect:/admin/users/list";
		}
		
		return "admin/login/login";
	}
	
	@RequestMapping(value = "/admin/login/doLogin", method = RequestMethod.POST)
	public String doLogin(
		@SessionAttribute(required=false, value="sessionUsername") String sessionUsername
		, @RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		, Model model){
		
		UserVO userVO = userDAO.selectByUsername(username);
		if (userVO.getPasswd().equals(passwd)) {
			model.addAttribute("sessionUsername", userVO.getUsername());
			model.addAttribute("sessionEmail", userVO.getEmail());
			//DB���� ������ userVO.getEmail ���� ���� ������ �����ϰ� ��
			return "redirect:/admin/users/list";
			
		}else {
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionEmail", "");
		}
		
		return "redirect:/admin/login/login";
		}
	
	@RequestMapping(value = "/admin/login/logout", method = RequestMethod.GET)
	public String doLogout(
		//username�� �����ðǵ� String�� username���� �����Ұž�
		@ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionEmail", "");
		
		return "redirect:/admin/login/logout";
	}
}
