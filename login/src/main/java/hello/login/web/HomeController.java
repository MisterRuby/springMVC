package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
    public String homeLoginV1(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) return "home";

        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) return "home";

        model.addAttribute("member", loginMember);
        return "loginHome";
    }


//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {
        // 세션 관리자에 저장된 회원 정보 조회
        Member member = (Member) sessionManager.getSession(request);

        if (member == null) return "home";

        model.addAttribute("member", member);

        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {
        // 기존 세션이 있는지 확인
        HttpSession session = request.getSession(false);

        // 기존 세션이 없으면 home 으로 이동
        if (session == null) return "home";

        // 세션이 있다면 확인
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        // 세션에 member 데이터가 없다면 home 으로 이동
        if (member == null) return "home";

        // 세션에 member 가 있다면 loginHome 으로 이동동
       model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {

        // 세션에 member 데이터가 없다면 home 으로 이동
        if (member == null) return "home";

        // 세션에 member 가 있다면 loginHome 으로 이동동
        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV5(@Login Member member, Model model) {

        // 세션에 member 데이터가 없다면 home 으로 이동
        if (member == null) return "home";

        // 세션에 member 가 있다면 loginHome 으로 이동동
        model.addAttribute("member", member);
        return "loginHome";
    }
}