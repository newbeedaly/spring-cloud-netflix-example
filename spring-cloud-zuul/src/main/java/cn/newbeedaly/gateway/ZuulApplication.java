package cn.newbeedaly.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringCloudApplication
@EnableCircuitBreaker
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.newbeedaly")
@SpringBootApplication(scanBasePackages = "cn.newbeedaly")
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
