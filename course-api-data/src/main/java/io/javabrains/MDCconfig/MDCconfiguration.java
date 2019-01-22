package io.javabrains.MDCconfig;

import org.springframework.stereotype.Component;

import io.javabrains.MDCFilter.MdcFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
public class MDCconfiguration{
	
	public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "clientUUID";
    public static final String DEFAULT_MDC_CLIENT_IP_KEY = "clientIP";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String mdcClientIpKey = DEFAULT_MDC_CLIENT_IP_KEY;
    private String requestHeader = null;

    @Bean
    public FilterRegistrationBean servletRegistrationBean() {
    	
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        
        final MdcFilter log4jMDCFilterFilter = 
        		new MdcFilter(responseHeader, mdcTokenKey, mdcClientIpKey, requestHeader);
        registrationBean.setFilter(log4jMDCFilterFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
    
}
