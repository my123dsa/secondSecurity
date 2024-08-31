package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.exceptions.TimeProviderException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

public class SecondCheckController implements Controller {

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
		String secret= session.getAttribute("secret").toString();

//	     QrData data = new QrData.Builder()
//	    		   .label("example@example.com")
//	    		   .secret(secret)
//	    		   .issuer("AppName")
//	    		   .algorithm(HashingAlgorithm.SHA1) // More on this below
//	    		   .digits(6)
//	    		   .period(30)
//	    		   .build();
//	     QrGenerator generator = new ZxingPngQrGenerator();
//	     byte[] imageData=null;
//		try {
//			imageData = generator.generate(data);
//		} catch (QrGenerationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	     String mimeType = generator.getImageMimeType();
//	  // mimeType = "image/png"
//
//	     String dataUri = getDataUriForImage(imageData, mimeType);
	     // dataUri = data:image/png;base64,iVBORw0KGgoAAAANSU...
//	     out.print("<img src="+ dataUri+" />");
	        CodeGenerator codeGenerator = new DefaultCodeGenerator();

	        TimeProvider timeProvider = new SystemTimeProvider();
	        String codeToCheck =null;
	        try {
	            codeToCheck = codeGenerator.generate(secret, Math.floorDiv(timeProvider.getTime(), 60));	// what should come here instead of the "1"
	            // codeToCheck is never the ok - I guess because of the second parameter which I don't know what to send.
	            response.getWriter().append("\r\nCode generated: " + codeToCheck);//.append(request.getContextPath());
	        } catch (CodeGenerationException ex){
	            ex.printStackTrace();
	        }
	        String s=null;
	        try {
				s = codeGenerator.generate(secret, timeProvider.getTime());
			} catch (TimeProviderException | CodeGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        String code = codeToCheck;



		String QRUrl = session.getAttribute("QRUrl").toString();
		out.print("<img src="+ QRUrl+" />");

			out.println("<form action='/demo_war_exploded/page/otp' method='post'>");
			out.println("    <input type='password' name='code' placeholder='Enter your secondcode'>");
			out.println("    <button type='submit'> 2차 인증 제출</button>");
			out.println("</form>");
			
			System.out.println(secret);
//	        System.out.println(codeToCheck);

	        
	        
	    }
	}
