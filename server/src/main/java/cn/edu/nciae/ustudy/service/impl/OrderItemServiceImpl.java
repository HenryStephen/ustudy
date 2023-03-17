package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.po.OrderItem;
import cn.edu.nciae.ustudy.mapper.OrderItemMapper;
import cn.edu.nciae.ustudy.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
