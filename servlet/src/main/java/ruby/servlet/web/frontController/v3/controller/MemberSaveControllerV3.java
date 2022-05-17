package ruby.servlet.web.frontController.v3.controller;

import ruby.servlet.domain.member.Member;
import ruby.servlet.domain.member.MemberRepository;
import ruby.servlet.web.frontController.ModelView;
import ruby.servlet.web.frontController.MyView;
import ruby.servlet.web.frontController.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);

        return modelView;
    }
}
