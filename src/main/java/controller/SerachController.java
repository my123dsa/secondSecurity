package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.courseRegistration_client.model.Course;
import dev.courseRegistration_client.service.CourseService;

public class SerachController implements Controller{
	private final CourseService courseService;
	
	public SerachController(){
		courseService= new CourseService();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	
		String search= request.getParameter("search");
		String courseId= request.getParameter("courseId");
		System.out.println(courseId);
		PrintWriter out= response.getWriter();
//		HttpSession session =request.getSession(false);
//		out.println("<a href='/courseProject/index.html'>홈으로</a>");
//		if(session ==null || session.getAttribute("email")==null) {
//			out.print("권한이 없습니다");
//			return;
//		}
//		else if(session.getAttribute("admin") !=null) {
//			out.print("권한이 없습니다");
//			return;
//		}
//		
		List<Course> courses=courseService.searchCourse(search);
		request.setAttribute("list", courses);
		
		// JSP 페이지로 포워드
		String url = "/WEB-INF/courseList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
