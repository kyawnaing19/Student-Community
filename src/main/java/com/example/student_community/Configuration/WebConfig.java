package com.example.student_community.Configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8083", "http://192.168.12.2:8083", "http://192.168.12.250", "http://192.168.12.178","http://192.168.12.46","http://192.168.12.") // replace with your allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        if (request.getScheme().equals("http")) {
                            String httpsUrl = "https://" + request.getServerName() + request.getRequestURI();
                            if (request.getQueryString() != null) {
                                httpsUrl += "?" + request.getQueryString();
                            }
                            response.sendRedirect(httpsUrl);
                            return false;
                        }
                        return true;
                    }
                });
            }
        };
    }
}

