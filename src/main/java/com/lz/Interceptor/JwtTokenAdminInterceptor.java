package com.lz.Interceptor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/08/15:49
 * @Description:
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lz.config.AppConfig;
import com.lz.pojo.error.ErrorResponse;
import com.lz.utils.JwtUtil;
import com.lz.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * JWT 令牌管理拦截器
 *
 * @author lz
 * @date 2023/11/08
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private AppConfig appConfig;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        System.out.println("request.getRequestURI():" + request.getRequestURI());
        //1、从请求头中获取令牌
        String token = request.getHeader("jwt");

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Map<String, Object> map = JwtUtil.parseToken(token, appConfig.getJwtKey());
            Integer id = (Integer) map.get("id");
            log.info("当前用户id：{}", id);
            String username = (String) map.get("username");
            log.info("当前用户：{}", username);
            BaseContext.setCurrentId(((long) id));

            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            ex.printStackTrace();
            log.info("校验失败");
            response.setStatus(200);

            // 创建ErrorResponse对象，并设置错误信息
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setStatus(200);
            errorResponse.setCode("1");
            errorResponse.setMessage("令牌校验失败");
            errorResponse.setTimestamp(System.currentTimeMillis());

            // 将错误信息以JSON格式返回给客户端
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            response.getWriter().close();
            
            return false;
        }
    }
}