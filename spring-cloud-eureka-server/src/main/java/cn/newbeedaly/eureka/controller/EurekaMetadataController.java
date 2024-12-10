package cn.newbeedaly.eureka.controller;

import com.netflix.eureka.EurekaServerContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EurekaMetadataController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 查询服务的信息（元数据和自定义的元数据）
     * <p>
     * 自定义元数据，客户端yml配置
     * eureka:
     * instance:
     * metadata-map:
     * mydata: newbeedaly
     * <p>
     * <p>
     * 方法1：查询不到，使用下面的方法,因为配置的原因，它作为server，不是client。(client推荐使用)
     * * return this.discoveryClient.getInstances("eureka");
     * * 方法2
     */
    @GetMapping("/metadata")
    public String metadata() {
        EurekaServerContextHolder.getInstance().getServerContext()
                .getRegistry().getSortedApplications();
        return EurekaServerContextHolder.getInstance()
                .getServerContext()
                .getApplicationInfoManager()
                .getEurekaInstanceConfig()
                .getMetadataMap()
                .toString();
    }
}
