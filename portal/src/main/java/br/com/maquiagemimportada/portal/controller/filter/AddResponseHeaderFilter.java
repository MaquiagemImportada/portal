package br.com.maquiagemimportada.portal.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AddResponseHeaderFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		((HttpServletResponse) response).setHeader("Pragma", "no-cache");
        ((HttpServletResponse) response).setHeader("Cache-Control", "no-cache");
        ((HttpServletResponse) response).setDateHeader("Expires", 0);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}