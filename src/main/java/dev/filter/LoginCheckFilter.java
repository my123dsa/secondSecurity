package dev.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/page/login","/page/loginpage"})
public class LoginCheckFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    PrintWriter out= httpResponse.getWriter();
	    out.println("<a href='/demo_war_exploded/index.html'>홈으로</a>");
		HttpSession session = httpRequest.getSession(false);
		if(session !=null && session.getAttribute("email") !=null) {
			out.print("현재 로그인 상태입니다");
			return;
		}
		chain.doFilter(request, response);
	}
	
}
