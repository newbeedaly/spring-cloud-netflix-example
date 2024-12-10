package cn.newbeedaly.user.controller;

import cn.newbeedaly.user.client.UserClient;
import cn.newbeedaly.user.dao.entity.User;
import cn.newbeedaly.user.service.impl.UserService;
import cn.newbeedaly.user.vo.UserValidTokenResVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class UserController implements UserClient {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserValidTokenResVO validToken(String token) throws JsonProcessingException {
        log.info("valid token:" + token);
        String cacheUser = redisTemplate.opsForValue().get("user:" + 1L);
        UserValidTokenResVO vo = new UserValidTokenResVO();
        User user;
        if (Objects.nonNull(cacheUser)) {
            user = new ObjectMapper().readValue(cacheUser, User.class);
        } else {
            user = userService.lambdaQuery().eq(User::getId, 1L).one();
            redisTemplate.opsForValue().set("user:" + 1L, new ObjectMapper().writeValueAsString(user));
        }
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

}
