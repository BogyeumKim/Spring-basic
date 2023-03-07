package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        /* 출력결과 다 동일한 객체 참조중
        memberService -> memberRepository1 = hello.core.member.MemoryMemberRepository@11392934
        orderService -> memberRepository2 = hello.core.member.MemoryMemberRepository@11392934
        memberRepository = hello.core.member.MemoryMemberRepository@11392934*/

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig been = ac.getBean(AppConfig.class);

        System.out.println("been = " + been.getClass());
        /*출력결과 ( 원래는 hello.core.AppConfig가 끝 )
        * been = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$2ad68a45
        * XXX CGLIB가 붙는데 스프링이 라이브러리 사용하여 Bean이 이미 스프링컨테이너에 있는거라면 기존에 있는걸 반환 없으면 Bean에 등록
        * 여기서 주의점은 AppConfig에 @Configration이 있어야한다. */
    }
}
