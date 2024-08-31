import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

public class Main {
    public static void main(String[] args) {

        //반드시 세트여야함
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
        String key = googleAuthenticatorKey.getKey();
        String QRUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("adduci", "userId", googleAuthenticatorKey);
        //

        boolean verify = googleAuthenticator.authorize(key, googleAuthenticator.getTotpPassword(key));
        System.out.println(verify);
        System.out.println(QRUrl);
        System.out.println(googleAuthenticator.getTotpPassword(key));
    }
}
