import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class SimpleJWT {
    private static final ObjectMapper om = new ObjectMapper();

    // base64 인코딩 (바이트 배열 받아서 인코딩 -> 합쳐서 리턴)
    private static String base64UrlEncode(byte[] b){
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
    }

    // base64 디코딩 (디코딩 후 byte[] 배열로 리턴)
    private static byte[] base64UrlDecode(String s){
        return Base64.getUrlDecoder().decode(s);
    }

    // HmacSha256 해쉬 알고리즘
    public static String signHmacSha256(String message, byte[] secret) throws Exception{
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret, "HmacSHA256"));
        byte[] sig = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return base64UrlEncode(sig);
    }

    // data를 받아서 JWT 발행
    public static String createToken(Map<String,Object> header, Map<String,Object> payload, byte[] secret) throws Exception{
        String headerJson = om.writeValueAsString(header); // Header를 String으로 Mapping
        String payloadJson = om.writeValueAsString(payload); // Payload String으로 Mapping

        // base64로 변환
        String encodedHeader = base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
        String encodedPayload = base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));

        // 변환된 base64(Header.payload)를 String 저장
        String signingInput = encodedHeader + "." + encodedPayload;

        // base64 처리된 Header.payload를 HMAC-SHA256 처리
        // Token 발행 메소드 호출 HMAC-SHA256 해쉬 알고리즘으로 암호화
        String signature = signHmacSha256(signingInput, secret);

        // 최종적으로 Header.Payload.Signature 문자열 생성 즉
        // JWT 발급
        return signingInput + "." + signature;
    }

    // JWT 토큰 들어오면 인증/인가
    public static boolean verifyToken(String token, byte[] secret) throws Exception {
        // 들어온 토큰을 . 을 기준으로 split
        // Header, Payload, Signature
        String[] parts = token.split("\\.");
        // 예외 처리
        if(parts.length != 3) return false;

        // parts 3개중 앞의 두개
        // 즉 Header.Payload를 합침
        String signingInput = parts[0] + "." + parts[1];
        // String을 HMAC-SHA256 수행 후 리턴(String)
        String expectedSig = signHmacSha256(signingInput, secret);
        // 시간차 공격 방지: constant-time 비교 권장 (여기선 간단히 equals)
        return constantTimeEquals(expectedSig, parts[2]);
    }

    // 비교 결과 후 접근 권한 true | false
    private static boolean constantTimeEquals(String a, String b){
        byte[] A = a.getBytes(StandardCharsets.UTF_8);
        byte[] B = b.getBytes(StandardCharsets.UTF_8);
        // 예외 처리
        if(A.length != B.length) return false;
        int result = 0;

        // 비교 연산
        for(int i=0;i<A.length;i++){
            result |= A[i] ^ B[i];
        }
        return result == 0; // return true
    }
}