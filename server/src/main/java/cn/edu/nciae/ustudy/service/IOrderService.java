package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.dto.OrderDTO;
import cn.edu.nciae.ustudy.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author YeLi
 * @date 2020-09-06
 */
public interface IOrderService extends IService<Order> {

	String generate(Long userId, List<Long> courses);

	void submit(String orderId, Long userId, String paymentMethod);

	List<OrderDTO> getOrderByUserId(Long userId);

	boolean hasCourse(Long userId, Long courseId);

	boolean checkOwnerPermission(Authentication authentication, Long courseId);
}
