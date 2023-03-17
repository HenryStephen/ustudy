package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.dto.OrderDTO;
import cn.edu.nciae.ustudy.exception.ServiceException;
import cn.edu.nciae.ustudy.mapper.OrderMapper;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.Order;
import cn.edu.nciae.ustudy.po.OrderItem;
import cn.edu.nciae.ustudy.service.ICourseService;
import cn.edu.nciae.ustudy.service.IOrderItemService;
import cn.edu.nciae.ustudy.service.IOrderService;
import cn.edu.nciae.ustudy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author YeLi
 * @author zhang
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

	private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IUserService userService;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String generate(Long userId, List<Long> courses) {
		if (courses.isEmpty()) {
			throw new ServiceException("Invalid order: the number of courses must not be 0;");
		}

		// Refuse the order when it contains purchased courses.
		List<Order> oldOrders = lambdaQuery()
				.eq(Order::getUserId, userId)
				.eq(Order::getDeleted, false)
				.list();
		if (!oldOrders.isEmpty()) {
			List<OrderItem> oldItems = orderItemService.lambdaQuery()
					.in(OrderItem::getOrderId, oldOrders.stream().map(Order::getId).toArray())
					.list();
			List<Long> ownedCourses = oldItems.stream().map(OrderItem::getCourseId).collect(Collectors.toList());
			if (CollectionUtils.containsAny(courses, ownedCourses)) {
				throw new ServiceException("The order contains purchased items.");
			}
		}

		// Save order and order items.
		Order order = new Order.Builder()
				.id(UUID.randomUUID().toString())
				.userId(userId)
				.submitTime(LocalDateTime.now())
				.total(BigDecimal.ZERO)
				.build();
		List<OrderItem> items = courseService.lambdaQuery()
				.in(Course::getId, courses)
				.list()
				.stream()
				.map(course -> {
					order.setTotal(order.getTotal().add(course.getPresentPrice()));
					return new OrderItem.Builder()
							.courseId(course.getId())
							.coursePrice(course.getPresentPrice())
							.orderId(order.getId())
							.build();
				})
				.collect(Collectors.toList());
		orderItemService.saveBatch(items);
		save(order);
		return order.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void submit(String orderId, Long userId, String paymentMethod) {
		if (orderService.getById(orderId).getPaid()) {
			throw new ServiceException("The order already purchased.Please don't repeat payment.");
		}
		orderService.lambdaUpdate()
				.set(Order::getPaymentTime, LocalDateTime.now())
				.set(Order::getPaid, 1)
				.set(Order::getPaymentMethod, paymentMethod)
				.update();
	}

	@Override
	public List<OrderDTO> getOrderByUserId(Long userId) {
		return orderMapper.getOrderDetailByUserId(userId);
	}

	/**
	 * Check user purchased courses,
	 * and return 1 when user paid, or else 0
	 */
	@Override
	public boolean hasCourse(Long userId, Long courseId) {
		List<Order> orders = lambdaQuery()
				.eq(Order::getUserId, userId)
				.eq(Order::getPaid, true)
				.eq(Order::getDeleted, false)
				.list();
		if (orders.isEmpty()) {
			return false;
		}
		logger.debug("Get user's orders: {}", orders);
		return orderItemService.lambdaQuery()
				.in(OrderItem::getOrderId,
						orders.stream().map(Order::getId).collect(Collectors.toList()))
				.eq(OrderItem::getCourseId, courseId)
				.eq(OrderItem::getDeleted, false)
				.count() >= 1;
	}

	@Override
	public boolean checkOwnerPermission(Authentication authentication, Long courseId) {
		Long userId = userService.getByPhone(authentication.getName()).getId();
		return hasCourse(userId, courseId);
	}
}
