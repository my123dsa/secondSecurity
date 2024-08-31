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

@WebFilter(filterName = "UserCheckFilter", urlPatterns = "/page/user/*")
public class UserCheckFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    PrintWriter out= httpResponse.getWriter();
	    out.println("<a href='/demo_war_exploded/index.html'>홈으로</a>");
		HttpSession session = httpRequest.getSession(false);
		if(session ==null || session.getAttribute("email")==null ||session.getAttribute("check")==null) {
			out.print("권한이 없습니다");
			return;
		}
		else if(session.getAttribute("admin") !=null) {
			out.print("권한이 없습니다");
			return;
		}
		chain.doFilter(request, response);
	}
	
}
