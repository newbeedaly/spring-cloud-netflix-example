package cn.newbeedaly.user.client;

import cn.newbeedaly.user.vo.UserValidTokenResVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserClient {

    /**
     * 验证token是否有效
     *
     * @param token 用户token
     * @return token验证结果
     */
    @PostMapping("/user/validToken")
    UserValidTokenResVO validToken(String token) throws JsonProcessingException;
}
