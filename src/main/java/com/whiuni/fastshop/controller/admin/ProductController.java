package com.whiuni.fastshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.whiuni.fastshop.dao.CompanyDAO;
import com.whiuni.fastshop.dao.ProductDAO;
import com.whiuni.fastshop.vo.CompanyVO;
import com.whiuni.fastshop.vo.ProductVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"sessionUsername","sessionEmail"})
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);	
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin/products/list", method = RequestMethod.GET)
	public String list(
				@ModelAttribute("sessionUsername") String sessionUsername
				, @ModelAttribute("sessionEmail") String sessionEmail
				, Model model) {
		
		if ( sessionUsername.equals("") ) {
			return "redirect:/admin/login/login";
		}
		
		List<ProductVO> productList = productDAO.selectList();
		
		model.addAttribute("productList", productList);
	
		
		return "admin/products/list";
	}
	
	
	@RequestMapping(value = "/admin/products/add", method = RequestMethod.GET)
	public String add(
			@ModelAttribute("sessionUsername") String sessionUsername
			, Model model) {
		
		List<CompanyVO> companyList = companyDAO.selectList();
		
		model.addAttribute("companyList", companyList);
		return "admin/products/add";
	}
	
	@RequestMapping(value = "/admin/products/doAdd", method = RequestMethod.POST)
	public String doAdd  (
			@RequestParam("image_url") MultipartFile file
			, @ModelAttribute("sessionUsername") String sessionUsername
			, @RequestParam(value="name") String name
			, @RequestParam(value="price") int price
			, @RequestParam(value="description") String description
			, @RequestParam(value="company_id") String company_id
			, Model model) throws IOException {
		
		// Save file on system
	      if (!file.getOriginalFilename().isEmpty()) {
	         BufferedOutputStream outputStream = new BufferedOutputStream(
	               new FileOutputStream(
	                     new File("C:\\Users\\gramgram\\Desktop\\java", file.getOriginalFilename())));
	         outputStream.write(file.getBytes());
	         outputStream.flush();
	         outputStream.close();
	      } 
	      
	    //jsp쪽으로 넘겨준다는 의미
	      	model.addAttribute("image_url", file);
	      	model.addAttribute("name", name);
			model.addAttribute("price", price);
			model.addAttribute("description", description);
			model.addAttribute("company_id", company_id);
			
	      ProductVO productVO = new ProductVO();
	      productVO.setImage_url(file.getOriginalFilename());
	      productVO.setName(name);
	      productVO.setPrice(price);
	      productVO.setDescription(description);
	      
	      ////////////////////////여기 company_id는 어떻게?하지?
	      productDAO.insert(productVO);
	      
	      return "admin/products/doAdd";

	}
	
}



