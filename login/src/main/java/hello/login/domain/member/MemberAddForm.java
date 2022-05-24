package hello.login.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberAddForm {

    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public Member saveMember() {
        Member member = new Member();
        member.setLoginId(this.loginId);
        member.setName(this.name);
        member.setPassword(this.password);
        return member;
    }
}
