package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPageController implements Controller{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out= response.getWriter();
//		out.println("<a href='/courseProject/index.html'>홈으로</a>");
//		HttpSession session = request.getSession(false);
//		if(session !=null && session.getAttribute("email") !=null) {
//			out.print("현재 로그인 상태입니다");
//			return;
//		}
		out.println("<form action='/demo_war_exploded/page/login' method='post'>");
		out.println("    <input type='text' name='email' placeholder='Enter your email'>");
		out.println("    <input type='password' name='password' placeholder='Enter your password'>");
		out.println("    <button type='submit'>제출</button>");
		out.println("</form>");
	}
}
