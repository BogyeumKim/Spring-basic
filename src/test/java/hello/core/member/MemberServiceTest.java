package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
    @Test
    void join() {
        //given
        Member member = new Member(1L,"memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        /*
        System.out.println("new member = " + member.getName());
        System.out.println("findMeber = "+findMember.getName());
        위의 코드 2줄을 콘솔로 확인하는 반면 Test에서는 아래 Assertions로 바로 확인결과 가능
        */
        Assertions.assertThat(member).isEqualTo(findMember); // join시킨 member와 findMember 의 member가 같은지
    }
}
