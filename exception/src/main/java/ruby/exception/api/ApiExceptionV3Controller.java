package ruby.exception.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ruby.exception.exception.UserException;

@Slf4j
@RestController
public class ApiExceptionV3Controller {

    @GetMapping("/api3/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            // 별다른 후속 조치가 없다면 서버에서 예외가 발생한 것이기 때문에 500 Error 로 처리가 된다.
            throw new IllegalArgumentException("잘못된 입력값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }
}
