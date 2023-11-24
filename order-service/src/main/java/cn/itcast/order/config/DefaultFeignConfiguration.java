package cn.itcast.order.config;
import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/24 12:52
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
