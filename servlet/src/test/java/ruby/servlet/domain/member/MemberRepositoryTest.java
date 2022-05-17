package ruby.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("save")
    void test_save() {
        // given
        Member member = new Member("ruby", 36);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    @DisplayName("findAll")
    void test_findAll() {
        // given
        Member member1 = new Member("ruby", 36);
        Member member2 = new Member("eun", 29);
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1, member2);

    }
}