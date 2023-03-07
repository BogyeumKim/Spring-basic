package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        /*의존관계를 인터페이스=구현클래스 가 아닌 AppConfig에서 다 관리하며 의존시킨다.*/
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); // return new MemberServiceImpl(new MemoryMemberRepository());
        OrderService orderService = appConfig.orderService(); //  return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = "+order);
        System.out.println("order.calculate = "+order.calculatePrice());
    }
}
