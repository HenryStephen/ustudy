package cn.edu.nciae.ustudy.mapper;

import cn.edu.nciae.ustudy.dto.OrderDTO;
import cn.edu.nciae.ustudy.po.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

	List<OrderDTO> getOrderDetailByUserId(@Param("userId") Long userId);
}
