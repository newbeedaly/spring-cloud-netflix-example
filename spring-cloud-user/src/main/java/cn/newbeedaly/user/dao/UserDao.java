package cn.newbeedaly.user.dao;

import cn.newbeedaly.user.dao.mapper.UserMapper;
import cn.newbeedaly.user.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends ServiceImpl<UserMapper, User> implements IService<User> {

}
