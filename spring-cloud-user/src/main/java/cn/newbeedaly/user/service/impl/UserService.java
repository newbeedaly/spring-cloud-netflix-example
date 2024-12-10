package cn.newbeedaly.user.service.impl;

import cn.newbeedaly.user.dao.mapper.UserMapper;
import cn.newbeedaly.user.dao.entity.User;
import cn.newbeedaly.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
