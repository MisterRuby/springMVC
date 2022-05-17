package ruby.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // status-line
        resp.setStatus(HttpServletResponse.SC_OK);                                              // 응답 코드 설정

        // response-header
        responseHeader(resp);
        
        // header 편의 메서드
        contentType(resp);      // ContentType 설정
        cookie(resp);           // Cookie 설정
        redirect(resp);         // Redirect 설정
    }

    private void responseHeader(HttpServletResponse resp) {
//        resp.setHeader("Content-Type", "text/plain;charset=utf-8");                           // Content-Type 설정
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");     // Cache-Control 설정
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("user-header", "rubyHeader");                                // 임의의 header를 직접 설정
    }

    private void contentType(HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("rubyCookie", "7");
        cookie.setMaxAge(600);
        resp.addCookie(cookie);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
//        resp.setStatus(HttpServletResponse.SC_FOUND);
//        resp.setHeader("Location", "/basic/hello-form.html");

        resp.sendRedirect("/basic/hello-form.html");
    }
}
