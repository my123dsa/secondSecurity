package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller{
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out= response.getWriter();
		 out.println("<a href='/demo_war_exploded/index.html'>홈으로</a>");
		HttpSession session= request.getSession(false);
		if(session !=null && session.getAttribute("email") !=null) {
			session.invalidate();
			out.print("로그아웃됨");
		}
		else {
			out.print("로그인 상태 아님");
		}
	}

}
