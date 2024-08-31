package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.courseRegistration_client.model.Member;
import dev.courseRegistration_client.service.EnrollmentService;
import dev.courseRegistration_client.service.MemberService;


//courseId
//post
public class CourseSelectController implements Controller{
	private final EnrollmentService enrollmentService;
	private final MemberService memberService;
	
	public CourseSelectController() {
		this.enrollmentService = new EnrollmentService();
		this.memberService=new MemberService();
	}
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	
		String courseId= request.getParameter("courseId");
		PrintWriter out= response.getWriter();
		HttpSession session =request.getSession(false);
//		 out.println("<a href='/courseProject/index.html'>홈으로</a>");
//		
//		if(session ==null || session.getAttribute("email")==null ) {
//			out.print("권한이 없습니다");
//			return;
//		}
		Member member = memberService.get(session.getAttribute("email").toString());

		out.print(enrollmentService.check(member, Integer.parseInt(courseId)));
		 out.println("<a href='/demo_war_exploded/page/user/list'>이전으로</a>");
	}
}
