package de.fom.wp.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="*.html")
public class LocaleFilter extends BaseFilter {

	public static final String KEY = "javax.servlet.jsp.jstl.fmt.locale.session";
	
	@Override
	public void httpDoFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		Locale l = request.getLocale();
		if(request.getParameter("locale")!=null){
			l = Locale.forLanguageTag(request.getParameter("locale"));
			request.getSession().setAttribute(KEY, l);
			Cookie cookie = new Cookie("locale", l.getLanguage());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
		if(request.getSession().getAttribute(KEY) == null){
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("locale")){
					l = Locale.forLanguageTag(cookie.getValue());
				}
			}
			request.getSession().setAttribute(KEY, l);
		}
		chain.doFilter(request, response);
		
	}

}
