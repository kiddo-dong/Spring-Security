package com.example.Cookie_Session_ResponseEntity.common.Interceptor;

import com.example.Cookie_Session_ResponseEntity.login.session.SessionStore;
import com.example.Cookie_Session_ResponseEntity.user.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 HandlerInterceptor
 Cookie-Session의 인증/인가
 Cookie로 인증/인가를 다루기위해
 Header의 값을 먼저 검증해야하므로
 Controller의 api경로에 닿기전에 디스패처 서블릿의 요청을 가로채야함.
*/

/*
 인터셉터
 요청이 컨트롤러까지 도달하기 전에 실행됨.
 여기서 인증 실패처럼 “요청 자체를 막아야 하는 상황”이면
 인터셉터에서 직접 응답을 만들어야 함.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Cookie에서 추출한 SessionId 초기화.
        String sessionId = null;

        // 클라이언트 측의 Cookie를 배열로 받아옴
        Cookie[] cookies = request.getCookies();

        // 쿠키가 존재하는지 | 존재하지 않으면 예외처리
        if(cookies != null){
            // 쿠키 배열을 반복하면서 SESSIONID와 동일한 Key를 가진 쿠키를 찾아서
            for(Cookie cookie : cookies){
                // value가 해당하면 sessionId에 넣어줌 (배열기반)
                if ("SESSIONID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        } else {
             throw new RuntimeException("");
        }

        // 쿠키에 세션이 존재하지 않을때
        if(sessionId == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "세션 없음");
            return false;
        }

        // 서버의 Session에서 브라우저 측의 SrssionId검색
        User user = SessionStore.get(sessionId);
        // session에 사용자의 정보가 존재하는지 확인
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
            return false;
        }

        request.setAttribute("loginuser", user);

        return true;
    }
}
