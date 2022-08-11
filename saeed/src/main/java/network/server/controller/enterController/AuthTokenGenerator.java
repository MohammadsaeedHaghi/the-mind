package network.server.controller.enterController;

import java.security.SecureRandom;

public class AuthTokenGenerator {

    public static String getToken(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }
}
