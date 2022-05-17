package ruby.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printRequest(req);
        printRequestHeader(req);
        printHeaderUtils(req);
        printEtc(req);
    }

    private void printRequest(HttpServletRequest req) {
        System.out.println("printRequest start");

        System.out.println("request.getMethod() = " + req.getMethod());
        System.out.println("request.getProtocol() = " + req.getProtocol());
        System.out.println("request.getScheme() = " + req.getScheme());
        System.out.println("request.getRequestURL() = " + req.getRequestURL());
        System.out.println("request.getRequestURI() = " + req.getRequestURI());
        System.out.println("request.getQueryString() = " + req.getQueryString());
        System.out.println("request.isSecure() = " + req.isSecure());               // https 사용유무

        System.out.println("printRequest end");
        System.out.println();
    }

    private void printRequestHeader(HttpServletRequest req) {
        System.out.println("printRequestHeader start");

//        Enumeration<String> headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + headerName);
//        }

        req.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println(headerName + ": " + headerName));

        System.out.println("printRequestHeader end");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest req) {
        System.out.println("printHeaderUtils start");

        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + req.getServerName());
        System.out.println("request.getServerPort() = " + req.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        req.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale: " + locale));
        System.out.println("request.getLocale() = " + req.getLocale());     // Locale 정보들 중에서 가장 순위가 높은 것을 반환
        System.out.println();

        System.out.println("[Cookie 편의 조회]");
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
            System.out.println();
        }

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + req.getContentType());
        System.out.println("request.getContentLength() = " + req.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + req.getCharacterEncoding());
        System.out.println();

        System.out.println("printHeaderUtils end");
        System.out.println();
    }

    private void printEtc(HttpServletRequest req) {
        System.out.println("printEtc start");

        System.out.println("[Remote 정보]");      // 요청을 보낸 곳에 대한 정보
        System.out.println("request.getRemoteHost() = " + req.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + req.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + req.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");       // 요청을 받은 서버에 대한 정보
        System.out.println("request.getLocalName() = " + req.getLocalName());
        System.out.println("request.getLocalAddr() = " + req.getLocalAddr());
        System.out.println("request.getLocalPort() = " + req.getLocalPort());
        System.out.println();

        System.out.println("printEtc end");
        System.out.println();
    }
}
