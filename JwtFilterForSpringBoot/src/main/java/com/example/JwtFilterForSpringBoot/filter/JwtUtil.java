package com.example.JwtFilterForSpringBoot.filter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JwtUtil {
    // 서버의 비밀키 Field
    private static final String secretKey = "mySecreatKey";

    // JWT 검증
    public static boolean validate(String token) {
        try{
            String[] parts = token.split("\\."); // . 을 기준으로 배열로 split

            // split된 JWT는 3개의 파트로 나누어짐
            String header = parts[0];
            String payload = parts[1];
            String signature = parts[2];

            // signature와 비교를 위해
            // header.payload로 붙임
            String data = header + "." + payload;

            // data를 비밀키와 믹스 후 signature로 리턴
            String newSignature = hmacSha256(data, secretKey);

            // newSignature == signature 비교
            // true | false return;
            Boolean result = newSignature.equals(signature);

            return result;

        } catch (Exception e){
            // 예외 상활 발생 시 return false -> HTTP Method 4xx
            return false;
        }
    }

    // header.payload + secreyKey -> signature 해쉬 값 리턴해주는 메소드
    private static String hmacSha256(String data, String key) throws Exception{
        // HMAC-SHA256 해쉬 알고리즘(암호화) 지정
        Mac mac = Mac.getInstance("HmacSHA256");

        mac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        byte[] hash = mac.doFinal(data.getBytes());

        // MD(해쉬값) base64 Encoding return
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }


    public static String createToken(Long userId) throws Exception {
        String header = Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());

        String payload = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(("{\"userId\":" + userId + "}").getBytes());

        String data = header + "." + payload;

        String signature = hmacSha256(data, secretKey);

        return data + "." + signature;
    }
}
