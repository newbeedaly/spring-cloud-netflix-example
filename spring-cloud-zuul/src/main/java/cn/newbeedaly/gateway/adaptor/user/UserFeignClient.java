package cn.newbeedaly.gateway.adaptor.user;

import cn.newbeedaly.gateway.adaptor.user.fallback.UserServiceFallback;
import cn.newbeedaly.gateway.adaptor.user.vo.UserValidTokenResVO;
import cn.newbeedaly.gateway.configuration.DefaultFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "user", contextId = "UserFeignClient", fallback = UserServiceFallback.class, configuration = DefaultFeignClientConfiguration.class)
public interface UserFeignClient {

    /**
     * 验证token是否有效
     *
     * @param token 用户token
     * @return token验证结果
     */
    @PostMapping("/user/validToken")
    UserValidTokenResVO validToken(String token);

}
