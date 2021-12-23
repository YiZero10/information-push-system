package com.njupt.system.config;

import com.njupt.system.util.CustomJsonHttpContentConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:47 AM
 * @signature Do it while you can!
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private CustomJsonHttpContentConvert customJsonHttpContentConvert;
    private TokenInterceptor tokenInterceptor;
    private InfoArgumentResolver infoArgumentResolver;

    @Autowired
    public WebMvcConfig(CustomJsonHttpContentConvert customJsonHttpContentConvert, TokenInterceptor tokenInterceptor,
        InfoArgumentResolver InfoArgumentResolver) {
        this.customJsonHttpContentConvert = customJsonHttpContentConvert;
        this.tokenInterceptor = tokenInterceptor;
        this.infoArgumentResolver = InfoArgumentResolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJsonHttpContentConvert);
        super.configureMessageConverters(converters);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/register")
                .excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(infoArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8081");
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
