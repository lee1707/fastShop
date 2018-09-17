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
import org.springframework.web.multipart.MultipartFile;

import com.whiuni.fastshop.dao.OrderDAO;
import com.whiuni.fastshop.dao.UserDAO;
import com.whiuni.fastshop.vo.OrderVO;
import com.whiuni.fastshop.vo.ProductVO;
import com.whiuni.fastshop.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "sessionUsername", "sessionEmail" })
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private UserDAO userDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin/order/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("sessionUsername") String sessionUsername,
			@ModelAttribute("sessionEmail") String sessionEmail, Model model) {

		if (sessionUsername.equals("")) {
			return "redirect:/admin/login/login";
		}

		List<OrderVO> orderList = orderDAO.selectList();

		model.addAttribute("orderList", orderList);

		return "admin/order/list";
	}

	@RequestMapping(value = "/admin/order/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("sessionUsername") String sessionUsername, Model model) {

		List<UserVO> userList = userDAO.selectList();

		model.addAttribute("userList", userList);
		return "admin/order/add";
	}

	@RequestMapping(value = "/admin/order/doAdd", method = RequestMethod.POST)
	public String doAdd(@ModelAttribute("sessionUsername") String sessionUsername,
			@RequestParam(value = "userId") int userId
			, @RequestParam(value = "productId") String productId
			, @RequestParam(value = "address") String address
			, @RequestParam(value = "productName") String productName
			, @RequestParam(value = "price") String price
			, @RequestParam(value = "imageUrl") String imageUrl, Model model)
			throws IOException {

		model.addAttribute("userId", userId);
		model.addAttribute("productId", productId);
		model.addAttribute("address", address);
		model.addAttribute("productName", productName);
		model.addAttribute("price", price);
		model.addAttribute("imageUrl", imageUrl);

		OrderVO orderVO = new OrderVO();
		orderVO.setUserId(userId);
		orderVO.setProductId(productId);
		orderVO.setAddress(address);
		orderVO.setProductName(productName);
		orderVO.setPrice(price);
		orderVO.setImageUrl(imageUrl);
		
		orderDAO.insert(orderVO);

		return "admin/order/doAdd";

	}

}
