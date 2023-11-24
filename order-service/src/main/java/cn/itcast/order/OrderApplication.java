package cn.itcast.order;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.config.DefaultFeignConfiguration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
/* 自动装配Feign客户端: DefaultFeignConfiguration对全局生效 */
/* 由于生成UserClient的@FeignClient注解在cn.itcast.feign.client下，当前注解在cn.itcast.order下
* 是扫描不到的@FeignClient注解的。所以需要使用clients参数指明需要扫描的类 */
@EnableFeignClients(clients = UserClient.class, defaultConfiguration = DefaultFeignConfiguration.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 创建RestTemplate并注入Spring容器
     * @return
     */
    // 负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /** 法一：
     * 全局设置为随机负载均衡，使用代码方式：
     * 将负载均衡的模式设置为随机
     * @return
     */
    /*@Bean
    public IRule randomRule() {
        return new RandomRule();
    }*/

}