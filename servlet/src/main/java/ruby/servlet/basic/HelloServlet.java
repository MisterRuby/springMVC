package ruby.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @WebServlet
 *  - 서블릿 애노테이션
 *  - @ServletComponentScan 에 의해 서블릿으로 등록됨
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("HelloServlet.service");

        System.out.println("req = " + req);
        System.out.println("res = " + res);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        res.getWriter().write("hello " + username);
    }
}
