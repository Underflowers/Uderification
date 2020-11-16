package io.underflowers.underification.api.util;


import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// https://www.baeldung.com/spring-boot-add-filter

@Component
@Order(1)
public class AuthFilter implements Filter {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Fetch API KEY from the header and fetch the associate applicationEntity
        String apiKey = req.getHeader("X-API-KEY");
        ApplicationEntity applicationEntity = applicationRepository.findByToken(apiKey);
        // Application does not exists => 401 error
        /*if(applicationEntity == null){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        }
        // Application exists => attach it to the request and chain filter
        else{*/
            req.setAttribute("applicationEntity", applicationEntity);
            chain.doFilter(request, response);
        //}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /*
    @Bean
    public FilterRegistrationBean<AuthFilter> urlFilter(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/badges/*");

        return registrationBean;
    }*/

}
