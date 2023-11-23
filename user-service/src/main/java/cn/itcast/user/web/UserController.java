package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
// 法一： 在@Data要注入的值所在的类中，直接加上@RefreshScope
// @RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取配置文件中的pattern.dateformat，如果能够成功获取到说明读取配置成功

    @Value("${pattern.dateformat}")
    private String dateformat;*/

    // 注入 patternProperties,获取 dateformat 对象
    @Autowired
    private PatternProperties patternProperties;

    @GetMapping("/now")
    public String now(){
        // return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
        // 法二： 使用patternProperties 获取 dateformat 的值
         return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }
}
