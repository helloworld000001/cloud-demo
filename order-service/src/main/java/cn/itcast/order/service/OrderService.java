package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

/*    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        // 2. 利用RestTemplate发送http请求，查询用户
        // 2.1 url路径: 使用服务名称替代固定IP,不使用硬编码
        String url = "http://userservice/user/" + order.getUserId();
        // 2.2 发送http请求，实现远程调用
        User user = restTemplate.getForObject(url, User.class);

        // 3. 封装User对象到Order
        order.setUser(user);

        // 4.返回
        return order;
    }*/

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        // 2. 使用Feign代替RestTemplate发送http请求:用Feign实现远程调用
        User user = userClient.findById(order.getUserId());

        // 3. 封装User对象到Order
        order.setUser(user);

        // 4.返回
        return order;
    }
}
