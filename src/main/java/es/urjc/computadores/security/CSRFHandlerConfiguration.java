package es.urjc.computadores.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class CSRFHandlerConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}
}

class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public void postHandle(final HttpServletRequest request,
            final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {

		System.out.println("111111 "+(CsrfToken) request.getAttribute("_csrf")); 
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
		System.out.println("2222 "+token.getToken()); 
    	modelAndView.addObject("token", token.getToken());    
    	
    }
}
