package com.njupt.system.config;

import com.njupt.system.model.Admin;
import com.njupt.system.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Elaine Huang
 * @date 2021/12/21 9:25 AM
 * @signature Do it while you can!
 */
@Component
public class InfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(User.class)
                || methodParameter.getParameterType().isAssignableFrom(Admin.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (TokenInterceptor.userHolder.get() == null)
            return TokenInterceptor.adminHolder.get();
        return TokenInterceptor.userHolder.get();
    }
}
