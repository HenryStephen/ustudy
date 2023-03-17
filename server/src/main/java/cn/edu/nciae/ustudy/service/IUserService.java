package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.dto.UserDTO;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author YeLi
 * @autor zhang
 */
public interface IUserService extends IService<User> {

    User getByPhone(String phone);

    long signup(UserDTO userDto);

}
