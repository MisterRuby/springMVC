package ruby.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    /**
     * 요청으로 보낸 request 의 파라미터 조회
     *  - get, post 모두 조회 가능
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("[전체 파라미터 조회]");
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " : " + req.getParameter(paramName)));
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = req.getParameter("username");     // 키 값이 중복일 경우에는 첫 번째 값을 반환한다.
        String age = req.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        // ex) http://localhost:8080/request-param?username=ruby&username=eun
        System.out.println("[키 값이 같은 복수 파라미터 조회]");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }
    }
}
