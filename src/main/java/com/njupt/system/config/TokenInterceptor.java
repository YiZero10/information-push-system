package com.njupt.system.config;

import com.alibaba.fastjson.JSONObject;
import com.njupt.system.enums.Permission;
import com.njupt.system.exception.CustomError;
import com.njupt.system.exception.LocalRuntimeException;
import com.njupt.system.model.Admin;
import com.njupt.system.model.User;
import com.njupt.system.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Elaine Huang
 * @date 2021/12/21 9:01 AM
 * @signature Do it while you can!
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static final String ACCESS_TOKEN = "Authorization";
    public static ThreadLocal<User> userHolder = new ThreadLocal<>();
    public static ThreadLocal<Admin> adminHolder = new ThreadLocal<>();

    @Autowired
    private JwtAuthService jwtAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String token = request.getHeader(ACCESS_TOKEN);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        CheckAdmin checkAdmin = method.getAnnotation(CheckAdmin.class);

        if (token != null ){
            Integer permission = checkAdmin == null ? Permission.STUDENT.getCode() : Permission.COMMON.getCode();

            if (request.getParameter("pms") != null) permission = Integer.valueOf(request.getParameter("pms"));
            JSONObject info = jwtAuthService.ParseToken(token, permission);

            if (checkAdmin != null && info == null)
                throw new LocalRuntimeException(CustomError.NOT_PERMISSION);

            if (permission <= Permission.COMMON.getCode()) {
                adminHolder.set(JSONObject.toJavaObject(info, Admin.class));
            } else {
                userHolder.set(JSONObject.toJavaObject(info, User.class));
            }
            return true;
        }else {
            throw new LocalRuntimeException(CustomError.SIGN_IN_REQUIRED);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userHolder.remove();
        adminHolder.remove();
    }
}
