package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;

public class OTPController implements Controller {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out= response.getWriter();
		out.println("<a href='/demo_war_exploded/index.html'>홈으로</a>");
		HttpSession session = request.getSession(false);
		if(session==null ||session.getAttribute("secret")==null) {
			
			out.print("2차 권한이 없습니다");
			return ;
		}

//		String secret= session.getAttribute("secret").toString();
//
//		 CodeGenerator codeGenerator = new DefaultCodeGenerator();
//		 TimeProvider timeProvider = new SystemTimeProvider();
//		 DefaultCodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
//	     verifier.setTimePeriod(60);
//	     verifier.setAllowedTimePeriodDiscrepancy(2);
//	     boolean successful = verifier.isValidCode(secret, request.getParameter("code"));

		GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

		String key= session.getAttribute("secret").toString();

		System.out.println(Integer.parseInt(request.getParameter("code")));

		boolean successful = googleAuthenticator.authorize(key,  Integer.parseInt(request.getParameter("code")));
		System.out.println(successful);
	     if (successful) {
	    	 session.setAttribute("check", true);
	     	 out.print("2차 인증이 성공했습니다");
	    	 System.out.println(successful);
	     }
	    else {
	    	out.print("2차 인증에 실패했습니다");
	    	}
	}
}
