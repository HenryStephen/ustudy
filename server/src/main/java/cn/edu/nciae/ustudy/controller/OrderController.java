package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.annotation.IgnoreResponseAdvice;
import cn.edu.nciae.ustudy.config.Result;
import cn.edu.nciae.ustudy.dto.OrderDTO;
import cn.edu.nciae.ustudy.po.Order;
import cn.edu.nciae.ustudy.po.User;
import cn.edu.nciae.ustudy.service.IOrderService;
import cn.edu.nciae.ustudy.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@Api
@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IUserService userService;

	@PostMapping("/order")
	@IgnoreResponseAdvice
	@ApiOperation("生成订单")
	public Result<String> generateOrder(Authentication authentication, @RequestParam List<Long> courses) {
		String username = authentication.getName();
		User user = userService.getByPhone(username);
		return Result.success(orderService.generate(user.getId(), courses));
	}

	@PostMapping("/payment")
	@ApiOperation("支付订单")
	public void submitOrder(Authentication authentication, @RequestParam("orderId") String orderId, @RequestParam("paymentMethod") String paymentMethod) {
		String username = authentication.getName();
		User user = userService.getByPhone(username);
		orderService.submit(orderId, user.getId(), paymentMethod);
	}

	@GetMapping("/user/{userId}/order")
	@ApiOperation("根据用户id获取所有订单")
	public List<OrderDTO> getOrderByUserId(@PathVariable Long userId) {
		return orderService.getOrderByUserId(userId);
	}
}

