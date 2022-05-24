package hello.login.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class Member {

    private Long id;
    private String loginId;
    private String name;
    private String password;
}
