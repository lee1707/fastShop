package com.whiuni.fastshop.controller.admin;

import java.io.IOException;
import java.util.List;

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

import com.whiuni.fastshop.dao.CompanyDAO;
import com.whiuni.fastshop.vo.CompanyVO;

@Controller
@SessionAttributes({ "sessionUsername", "sessionEmail" })
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyDAO companyDAO;

	@RequestMapping(value = "/admin/company/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("sessionUsername") String sessionUsername,
			@ModelAttribute("sessionEmail") String sessionEmail, Model model) {

		if (sessionUsername.equals("")) {
			return "redirect:/admin/login/login";
		}

		List<CompanyVO> companyList = companyDAO.selectList();

		model.addAttribute("companyList", companyList);

		return "admin/company/list";
	}

	@RequestMapping(value = "/admin/company/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("sessionUsername") String sessionUsername, Model model) {
		List<CompanyVO> companyList = companyDAO.selectList();

		model.addAttribute("companyList", companyList);
		return "admin/company/add";
	}


	@RequestMapping(value = "/admin/company/doAdd", method = RequestMethod.POST)
	public String doAdd  (
			@ModelAttribute("sessionUsername") String sessionUsername
			, @RequestParam(value="name") String name
			, @RequestParam(value="phone") String phone
			, @RequestParam(value="fax") String fax
			, @RequestParam(value="email") String email
			, Model model) throws IOException {
		
	    //jsp쪽으로 넘겨준다는 의미
	      	model.addAttribute("name", name);
			model.addAttribute("phone", phone);
			model.addAttribute("fax", fax);
			model.addAttribute("email", email);
			
		  CompanyVO companyVO = new CompanyVO();
		  companyVO.setName(name);
		  companyVO.setPhone(phone);
		  companyVO.setFax(fax);
		  companyVO.setEmail(email);
		  
	      companyDAO.insert(companyVO);
	      
	      return "admin/company/doAdd";

	}
	
}
