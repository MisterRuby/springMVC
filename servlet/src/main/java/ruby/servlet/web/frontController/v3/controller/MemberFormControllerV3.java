package ruby.servlet.web.frontController.v3.controller;

import ruby.servlet.web.frontController.ModelView;
import ruby.servlet.web.frontController.MyView;
import ruby.servlet.web.frontController.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
