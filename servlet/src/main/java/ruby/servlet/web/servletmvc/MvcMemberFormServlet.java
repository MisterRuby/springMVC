package ruby.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/view/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);

        // 다른 서블릿이나 JSP 로 이동. 클라이언트로 응답했다가 재요청하는 Redirect와는 다름.
        //  - 스프링의 "forword:이동경로" 와 같은 역할
        dispatcher.forward(req, resp);
    }
}
