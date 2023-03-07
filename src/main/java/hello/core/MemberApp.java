package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*의존관계를 인터페이스=구현클래스 가 아닌 AppConfig에서 다 관리하며 의존시킨다.*/
//        MemberService memberService = new MemberServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // memberService = return new MemberServiceImpl(new MemoryMemberRepository())

        /*스프링 컨테이너 ( Bean을 관리해줌 )*/
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);

        /*파라미터는 AppConfig에 있는 memberService메서드 이름임*/
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMeber = "+findMember.getName());
    }
}
