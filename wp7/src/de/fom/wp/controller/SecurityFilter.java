package de.fom.wp.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang3.*;

//In dieser Webanwendung wird f�r alle Links die auf html enden ein authentifizierter Benutzer ben�tigt 
//@WebFilter(urlPatterns="*.html")
public class SecurityFilter extends BaseFilter {

	@Override
	public void httpDoFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request.getSession().getAttribute("user")==null){
			if(request.getMethod().toLowerCase().equals("get")){
				String path = request.getRequestURI();
				if(StringUtils.isNotBlank(request.getQueryString())){
					path += "?"+request.getQueryString();
				}
				request.getSession().setAttribute("path", path);
			}
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}
}
