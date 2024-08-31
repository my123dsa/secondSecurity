package secret;

import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;

public class Secret {
	SecretGenerator secretGenerator = new DefaultSecretGenerator();
	String secret = secretGenerator.generate();

}
