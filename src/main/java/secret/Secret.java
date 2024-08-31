package secret;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;

public class Secret {
	SecretGenerator secretGenerator = new DefaultSecretGenerator();
	String secret = secretGenerator.generate();
	GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
	GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
	String key = googleAuthenticatorKey.getKey();
}
