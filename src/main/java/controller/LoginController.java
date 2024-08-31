package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import dev.courseRegistration_client.model.Course;
import dev.courseRegistration_client.model.Member;
import dev.courseRegistration_client.service.CourseService;
import dev.courseRegistration_client.service.MemberService;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;

//login
public class LoginController implements Controller{
	private final MemberService memberService;
	private final CourseService courseService;
	
	public LoginController() {
		this.memberService= new MemberService();
		this.courseService= new CourseService();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");	
		String email=request.getParameter("email");
		String password =request.getParameter("password");
		Member member = null;
		try {
			member = memberService.login(email, password);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out= response.getWriter();
		if (email.isEmpty() || password.isEmpty()) {
			out.print("아이디 혹은 비밀번호를 입력하세요");
			return;
		}
		if(member== null) {
			out.print("존재하지 않는 아이디입니다");
			return;
		}
		
		HttpSession session = request.getSession();
		if(session.isNew() || session.getAttribute("email") ==null) {
			session.setAttribute("email", email);
//			 SecretGenerator secretGenerator = new DefaultSecretGenerator();
//		     String secret = secretGenerator.generate();
			GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
			GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
			String secret = googleAuthenticatorKey.getKey();
			String QRUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("adduci", "userId", googleAuthenticatorKey);
			System.out.println(secret);
			session.setAttribute("secret", secret);
			session.setAttribute("QRUrl",QRUrl);
			if(member.isAdmin()) {
				session.setAttribute("admin", true);
			}
			out.print("로그인 완료");
		}
		else {
			out.print("현재 로그인 상태입니다");
		}
		response.sendRedirect("/demo_war_exploded/page/secondcheck");
		out.close();
	}
}
