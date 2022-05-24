package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionId";

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     * @param value
     * @param response
     */
    public void createSession(Object value, HttpServletResponse response) {
        // sessionId 생성
        String sessionId = UUID.randomUUID().toString();

        // 세션 저장소에 sessionId 와 보관할 값 저장
        sessionStore.put(sessionId, value);

        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회
     * @param request
     * @return
     */
    public Object getSession(HttpServletRequest request) {
        return findCookie(request, SESSION_COOKIE_NAME)
                .map(cookie -> sessionStore.get(cookie.getValue()))
                .orElse(null);
    }

    /**
     * 세션 만료
     * @param request
     */
    public void expire(HttpServletRequest request) {
        findCookie(request, SESSION_COOKIE_NAME)
                .ifPresent(cookie -> sessionStore.remove(cookie.getValue()));
    }
    

    public Optional<Cookie> findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) return Optional.empty();

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny();
    }
}
