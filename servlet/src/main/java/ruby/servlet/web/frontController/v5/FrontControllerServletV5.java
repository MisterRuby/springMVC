package ruby.servlet.web.frontController.v5;

import ruby.servlet.web.frontController.ModelView;
import ruby.servlet.web.frontController.MyView;
import ruby.servlet.web.frontController.v3.ControllerV3;
import ruby.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import ruby.servlet.web.frontController.v3.controller.MemberListControllerV3;
import ruby.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import ruby.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import ruby.servlet.web.frontController.v4.controller.MemberListControllerV4;
import ruby.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import ruby.servlet.web.frontController.v5.adapter.ControllerV3HandlerAdapter;
import ruby.servlet.web.frontController.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMapping();
        initHandlerAdapters();
    }

    // @RequestMapping 의 역할이 이 메서드의 역할이라고 볼 수 있다.
    private void initHandlerMapping() {
        // V3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelView modelView = handlerAdapter.handle(req, resp, handler);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);
        view.render(modelView.getModel(), req, resp);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }

        throw new IllegalStateException("handler adapter 를 찾을 수 없습니다. handler= " + handler);
    }

    private Object getHandler(HttpServletRequest req) {
        String uri = req.getRequestURI();
        return handlerMappingMap.get(uri);
    }

    private MyView viewResolver(String viewName) {
        String prefix = "/WEB-INF/view/";
        String suffix = ".jsp";
        return new MyView(prefix + viewName + suffix);
    }
}
