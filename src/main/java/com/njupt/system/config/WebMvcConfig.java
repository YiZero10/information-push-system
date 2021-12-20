package com.njupt.system.config;

import com.njupt.system.util.CustomJsonHttpContentConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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

    @Autowired
    public WebMvcConfig(CustomJsonHttpContentConvert customJsonHttpContentConvert) {
        this.customJsonHttpContentConvert = customJsonHttpContentConvert;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJsonHttpContentConvert);
        super.configureMessageConverters(converters);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
