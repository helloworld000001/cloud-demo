package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther 陈彤琳
 * @Description $ 法二：创建专门的配置类：定义在配置文件中前缀是Pattern属性
 * 2023/11/23 15:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    /* 会自动拼接pattern.dateformat 字段*/
    private String dateformat;
}
