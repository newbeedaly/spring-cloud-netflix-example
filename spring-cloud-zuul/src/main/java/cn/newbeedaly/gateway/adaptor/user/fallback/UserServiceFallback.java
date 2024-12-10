package cn.newbeedaly.gateway.adaptor.user.fallback;

import cn.newbeedaly.gateway.adaptor.user.UserFeignClient;
import cn.newbeedaly.gateway.adaptor.user.vo.UserValidTokenResVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallback implements UserFeignClient {
    @Override
    public UserValidTokenResVO validToken(String token) {
        log.error("调用userService的validToken接口，触发fallback");
        // 保证调用getUserId不报NullPointsException
        return new UserValidTokenResVO();
    }
}
