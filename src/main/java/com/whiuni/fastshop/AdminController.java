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
	
			//로그인 성공햇는가에 따라 다르게 나누기
			if(sessionUsername.equals("")) {
				return "redirect:/admin/login/login";
			}
			
			List<UserVO> userList = userDAO.selectList();
	
			model.addAttribute("userList", userList);
		
		
			return "/admin/users/list";
			//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
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
		//안에 코드 없이 화면에 jsp파일을 뿌려주는 역할을 함
		return "admin/users/add";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
	
	@RequestMapping(value = "/admin/users/doAdd", method = RequestMethod.POST)
	public String doAdd(
		//username을 가져올건데 String의 username으로 선언할거야
		@RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		, @RequestParam(value="point") int point
		, @RequestParam(value="coupon") String coupon
		, @RequestParam(value="email") String email
		, @ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		//jsp쪽으로 넘겨준다는 의미
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
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
	
	@RequestMapping(value = "/admin/users/edit", method = RequestMethod.GET)
	public String edit(
			@ModelAttribute("sessionUsername") String sessionUsername
			, @RequestParam(value="id") int id
			, @RequestParam(required=false, value="username") String username
			, Locale locale
			, Model model) {
		//userDAO의 list를 가져와서 userlist로 저장
		
		//하나만 가져오는 거면
		UserVO user = userDAO.select(id);
		
		model.addAttribute("userVO", user);
		
		System.out.println(sessionUsername);
		
		return "/admin/users/edit";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
	
	@RequestMapping(value = "/admin/users/doEdit", method = RequestMethod.POST)
	public String doAdd(
		//username을 가져올건데 String의 username으로 선언할거야
		@RequestParam(value="id") int id
		, @RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		, @RequestParam(value="point") int point
		, @RequestParam(value="coupon") String coupon
		, @RequestParam(value="email") String email
		, @ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		//jsp쪽으로 넘겨준다는 의미
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
			//DB에서 가져온 userVO.getEmail 나에 대한 정보를 저장하게 됨
			return "redirect:/admin/users/list";
			
		}else {
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionEmail", "");
		}
		
		return "redirect:/admin/login/login";
		}
	
	@RequestMapping(value = "/admin/login/logout", method = RequestMethod.GET)
	public String doLogout(
		//username을 가져올건데 String의 username으로 선언할거야
		@ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionEmail", "");
		
		return "redirect:/admin/login/logout";
	}
}
