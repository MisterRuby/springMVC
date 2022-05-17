package ruby.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view")
    public String responseView(Model model) {
        model.addAttribute("data", "ruby");
        return "response/hello";
    }
}
