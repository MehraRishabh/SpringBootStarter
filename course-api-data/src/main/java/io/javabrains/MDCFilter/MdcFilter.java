package io.javabrains.MDCFilter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.javabrains.MDCconfig.MDCconfiguration;

import org.springframework.stereotype.Component;

@Component
public class MdcFilter extends OncePerRequestFilter {
	
	private final String responseHeader;
    private final String mdcTokenKey;
    private final String mdcClientIpKey;
    private final String requestHeader;

    public MdcFilter() {
        responseHeader = MDCconfiguration.DEFAULT_RESPONSE_TOKEN_HEADER;
        mdcTokenKey = MDCconfiguration.DEFAULT_MDC_UUID_TOKEN_KEY;
        mdcClientIpKey = MDCconfiguration.DEFAULT_MDC_CLIENT_IP_KEY;
        requestHeader = null;
    }
    
	public MdcFilter(final String responseHeader, final String mdcTokenKey, final String mdcClientIpKey, final String requestHeader) {
		super();
		this.responseHeader = responseHeader;
		this.mdcTokenKey = mdcTokenKey;
		this.mdcClientIpKey = mdcClientIpKey;
		this.requestHeader = requestHeader;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            final String token = extractToken(request);
            final String clientIP = extractClientIP(request);
            MDC.put(mdcClientIpKey, clientIP);
            MDC.put(mdcTokenKey, token);
            if (!StringUtils.isEmpty(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
            MDC.remove(mdcClientIpKey);
        }
	}

	private String extractClientIP(HttpServletRequest request) {
		final String clientIP;
        if (request.getHeader("X-Forwarded-For") != null) {
            clientIP = request.getHeader("X-Forwarded-For").split(",")[0];
        } else {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
	}

	private String extractToken(HttpServletRequest request) {
		
		final String token;
        if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
            token = request.getHeader(requestHeader);
        } else {
            token = UUID.randomUUID().toString();
        }
        return token;
	}

}
