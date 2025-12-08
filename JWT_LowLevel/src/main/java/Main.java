import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", "donghyun");
        payload.put("role", "USER");
        payload.put("exp", System.currentTimeMillis()/1000 + 3600); // +1h

        byte[] secret = "띠리리리리릴".getBytes();

        String token = SimpleJWT.createToken(header, payload, secret);
        System.out.println("JWT = " + token);

        boolean ok = SimpleJWT.verifyToken(token, secret);
        System.out.println("검증 결과 = " + ok);
    }
}
