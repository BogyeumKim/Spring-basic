package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    /*MemberSerivce , MemberRepository , OrderSerivce , DiscountPolicy 는 다 인터페이스로 리턴은 상속받는 구현체가 와야함
    * 그래서 return new 구현체로 생성자를 만든다음 각 구현체 기본생성자는 매개변수가 들어가도록 작성해놨음 ( 각 구현 클래스 확인 )
    * 그 매개변수에 내가 참조(의존)시킬 생성자를 주입해줌.*/
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자 메서드 주입
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy()); // 생성자 메서드 주입
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
