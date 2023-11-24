package cn.itcast.gateway;

import jdk.nashorn.internal.runtime.GlobalConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @auther 陈彤琳
 * @Description $
 * 2023/11/24 20:33
 */
// 会有很多个过滤器，这里表示执行过滤器的优先级：值越小优先级越高
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();

        // 2. 获取参数中的 authorization 参数:获取第一个匹配的值
        String auth = params.getFirst("authorization");

        // 3. 判断参数值是否等于admin
        if(auth.equals("admin")){
            // 4. 是则放行:找到下个过滤器，调用下个过滤器
            return chain.filter(exchange);
        }

        // 5. 否则拦截
        // 5.1 设置状态码:401未登录
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 5.2 拦截请求
        return exchange.getResponse().setComplete();
    }
}
