package cn.edu.nciae.ustudy.mapper;

import cn.edu.nciae.ustudy.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
