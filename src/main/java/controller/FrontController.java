package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/page/*")
public class FrontController extends HttpServlet{
	private Map<String,Controller> controllerMap= new HashMap<>();
	
	public FrontController()  {
		controllerMap.put("/login",new LoginController());
		controllerMap.put("/logout", new LogoutController());
		controllerMap.put("/user/courseselect", new CourseSelectController());
		controllerMap.put("/user/test", new TestController());
		controllerMap.put("/user/list", new ListController());
		controllerMap.put("/user/search", new SerachController());
		controllerMap.put("/loginpage", new LoginPageController());
		controllerMap.put("/secondcheck", new SecondCheckController());
		controllerMap.put("/otp", new OTPController());
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path=request.getPathInfo();
		System.out.println(path);
		Controller controller= controllerMap.get(path);
		controller.process(request, response);
	}
}
