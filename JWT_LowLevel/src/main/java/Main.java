import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256"); // HMAC-SHA256 해쉬 알고리즘
        header.put("typ", "JWT"); // Token 방식

        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", "donghyun");
        payload.put("role", "USER");
        payload.put("exp", System.currentTimeMillis()/1000 + 3600); // +1h // 토큰 만료 시간

        byte[] secret = "띠리리리리릴".getBytes(); // 비밀키 서버는 한개의 비밀키를 가지고 있음

        String token = SimpleJWT.createToken(header, payload, secret); //
        System.out.println("JWT = " + token);

        boolean ok = SimpleJWT.verifyToken(token, secret);
        System.out.println("검증 결과 = " + ok);
    }
}
