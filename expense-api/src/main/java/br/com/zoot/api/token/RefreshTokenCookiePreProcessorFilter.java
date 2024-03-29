package br.com.zoot.api.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			             ServletResponse response, 
			             FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(req.getRequestURI().equalsIgnoreCase("/oauth/token") 
				&& req.getParameter("grant_type").equals("refresh_token")
				&& req.getCookies() != null) {
			
			for(Cookie cookie : req.getCookies()) {
				if( cookie.getName().equals("refreshToken")) {
					req = new CustomServletRequestWrapper(req, cookie.getValue());
				}
			}
		}
		
		chain.doFilter(req, response);
	}
	
	static class CustomServletRequestWrapper extends HttpServletRequestWrapper{

		String refreshToken;
		
		public CustomServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
		
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] {this.refreshToken});
			map.setLocked(true);
			
			return map;
		}
	}
}
