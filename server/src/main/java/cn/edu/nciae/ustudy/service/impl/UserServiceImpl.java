package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.dto.UserDTO;
import cn.edu.nciae.ustudy.exception.ServiceException;
import cn.edu.nciae.ustudy.mapper.UserMapper;
import cn.edu.nciae.ustudy.po.User;
import cn.edu.nciae.ustudy.service.IUserService;
import cn.edu.nciae.ustudy.utils.ConvertUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author YeLi
 * @author zhang
 */
@Primary
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements IUserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = getByPhone(phone);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User does not exits.");
        }

        List<SimpleGrantedAuthority> authorities = Stream.of("ROLE_USER")
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(phone)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }


    @Override
    public User getByPhone(String phone) {
        return lambdaQuery()
                .eq(User::getTelephone, phone)
                .eq(User::getDeleted, false)
                .one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long signup(UserDTO userDTO) {
        userDTO.setId(null);
        User user = ConvertUtils.from(userDTO, User.class);
        if (Objects.nonNull(getByPhone(user.getTelephone()))) {
            throw new ServiceException("User already exits.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return user.getId();
    }
}
