package ruby.servlet.web.frontController.v3;

import ruby.servlet.web.frontController.ModelView;
import ruby.servlet.web.frontController.MyView;
import ruby.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import ruby.servlet.web.frontController.v3.controller.MemberListControllerV3;
import ruby.servlet.web.frontController.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String uri = req.getRequestURI();
        ControllerV3 controller = controllerV3Map.get(uri);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);

        ModelView modelView = controller.process(paramMap);
        String viewName = modelView.getViewName();

        MyView view = viewResolver(viewName);
        view.render(modelView.getModel(), req, resp);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        String prefix = "/WEB-INF/view/";
        String suffix = ".jsp";
        return new MyView(prefix + viewName + suffix);
    }
}
