package ruby.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        // application.properties 에서 설정한 prefix + 논리이름 + suffix 이 물리 이름이 됨
        return new ModelAndView("new-form");
    }

}
