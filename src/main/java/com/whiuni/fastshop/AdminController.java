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
	
	////////////////////////////////////이 아래부분 고쳐야됨~~~~~~~~~~~~~~~~~
	@RequestMapping(value = "/admin/users/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
			@ModelAttribute("sessionUsername") String sessionUsername
			, @ModelAttribute("sessionUsername") String sessionEmail
			, @RequestParam(required=false, value="username") String username
			, Locale locale
			, Model model) {
	
				//로그인 성공햇는가에 따라 다르게 나누기
				
				//userDAO의 list를 가져와서 userlist로 저장
		List<UserVO> userList = userDAO.selectList();
		
		//jsp쪽으로 넘겨준다는 의미
		model.addAttribute("userList", userList);
		
		
		return "/admin/users/list";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
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
		
		//userDAO의 list를 가져와서 userlist로 저장
		//List<UserVO> userList = userDAO.selectList();
		
		//하나만 가져오는 거면
		//UserVO user = userDAO.select(id);
		//model.addAttribute("id", id);
		
		//jsp쪽으로 넘겨준다는 의미
		//model.addAttribute("userList", userList);
		//model.addAttribute("id", id);
		//model.addAttribute("username",username);
		
		
		return "/admin/users/info";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}

	@RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
	public String add(Locale locale, Model model) {
		
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
		List<UserVO> userList = userDAO.selectList();
		
		//하나만 가져오는 거면
		UserVO user = userDAO.select(id);
		
		model.addAttribute("userVO", user);
		
		System.out.println(sessionUsername);
		//model.addAttribute("id", id);
		
		//jsp쪽으로 넘겨준다는 의미
		//model.addAttribute("userList", userList);
		//model.addAttribute("id", id);
		//model.addAttribute("username",username);
		
		
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
	
		userDAO.update(userVO);
		
		return "/admin/users/doAdd";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
	

	@RequestMapping(value = "/admin/users/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		//만약 비번이랑 안맞으면 로그인실패~~~ 리다이렉트ㅡㅡㅡㅡㅡ
		
		//안에 코드 없이 화면에 jsp파일을 뿌려주는 역할을 함
		return "admin/users/login";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
	
	/////////////////으아ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㄱ
	@RequestMapping(value = "/admin/users/doLogin", method = RequestMethod.POST)
	public String doLogin(
		//username을 가져올건데 String의 username으로 선언할거야
		, @RequestParam(value="username") String username
		, @RequestParam(value="passwd") String passwd
		
		, @ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		
		UserVO userVO = userDAO.selectByUsername(username);
		if (userVO.getPasswd().equals(passwd)) {
			model.addAttribute("sessionUsername", userVO.getUsername());
			model.addAttribute("sessionUsername", userVO.getUsername());
			//DB에서 가져온 userVO.getEmail 나에 대한 정보를 저장하게 됨
		}else {
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionUsername", "");
		}
		
		
		return "/admin/users/doLogin";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
	
	@RequestMapping(value = "/admin/users/doLogout", method = RequestMethod.GET)
	public String doLogout(
		//username을 가져올건데 String의 username으로 선언할거야
		@ModelAttribute("sessionUsername") String sessionUsername
		, Model model){
		
		
			model.addAttribute("sessionUsername", "");
			model.addAttribute("sessionEmail", "");
		
		//여기도 리다이렉ㅌㅌㅌㅌㅌㅌㅌㅌㅌㅌㅌ트
		
		return "redirect:/admin/users/doLogout";
		//return이 "home"일경우 home은 view아래에서 home.jsp파일을 찾아라 라는 의미임. home.jsp파일이 출력됨.
	}
}





