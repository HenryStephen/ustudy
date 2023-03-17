package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.UstudyApplication;
import cn.edu.nciae.ustudy.dto.UserDTO;
import cn.edu.nciae.ustudy.exception.ServiceException;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.Order;
import cn.edu.nciae.ustudy.po.OrderItem;
import cn.edu.nciae.ustudy.service.ICourseService;
import cn.edu.nciae.ustudy.service.IOrderItemService;
import cn.edu.nciae.ustudy.service.IOrderService;
import cn.edu.nciae.ustudy.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 * @date 2020/9/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UstudyApplication.class)
@WebAppConfiguration
public class OrderServiceImplTest {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IOrderItemService orderItemService;

    private Long userId;

    private Long courseId;

    private String orderId;

    private final List<Long> courses = new ArrayList<>();

    @Before
    public void testSave(){
        //创建新用户
        UserDTO userDTO = new UserDTO();
        userDTO.setTelephone("17349868186");
        userDTO.setRealName("张");
        userDTO.setPassword("123456");
        userDTO.setUsername("zhang");
        userDTO.setBirthday(LocalDate.now());
        userId = userService.signup(userDTO);//注册用户
        Assert.assertNotNull(userId);
        //创建新课程
        Course course = new Course();
        course.setName("测试课1");
        course.setPreviewPicture("链接1");
        course.setFree(true);
        course.setOriginalPrice(new BigDecimal("100.0"));
        course.setPresentPrice(BigDecimal.ZERO);
        course.setCategoryId(1L);
        course.setSubjectId(1001L);
        course.setTeachingMethods("1");
        course.setPublishedTime(LocalDate.now());
        course.setStudentsNumber(100);
        course.setValidDays(112);
        course.setClassHour(80);
        Assert.assertTrue(courseService.save(course));
        courseId = course.getId();
    }

    @Test
    public void testGenerateAndSubmit(){
        //将课程添加到list
        courses.add(courseId);
        orderId = orderService.generate(userId, courses);
        Assert.assertNotNull(orderId);
        //测试重复生成订单
        Assert.assertThrows(ServiceException.class, () -> {
            orderService.generate(userId, courses);
        });
        //提交订单
        orderService.submit(orderId, userId, "1");
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("id", orderId);
        Order order = orderService.getOne(orderQueryWrapper);
        Assert.assertEquals(true, order.getPaid());
    }

    @Test
    public void testGetOrderByUserId(){
        //测试新建用户的订单
        Assert.assertTrue(orderService.getOrderByUserId(userId).isEmpty());
    }

    @After
    public void testRemove(){
        Assert.assertTrue(courseService.removeById(courseId));
        Assert.assertTrue(userService.removeById(userId));
        if (StringUtils.isNotBlank(orderId)) {
            Assert.assertTrue(orderService.removeById(orderId));
            QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<OrderItem>();
            orderItemQueryWrapper.eq("order_id", orderId);
            Assert.assertTrue(orderItemService.remove(orderItemQueryWrapper));
        }
    }

}