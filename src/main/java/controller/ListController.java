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

public class ListController implements Controller{
	private final CourseService courseService;
	
	public ListController() {
		courseService= new CourseService();
	}
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	
		String courseId= request.getParameter("courseId");
		PrintWriter out= response.getWriter();

		List<Course> courses=  courseService.displayAllCourses();
		System.out.println(courses.get(0).getName());

		request.setAttribute("list", courses);
		
		// JSP 페이지로 포워드
		String url = "/WEB-INF/courseList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
