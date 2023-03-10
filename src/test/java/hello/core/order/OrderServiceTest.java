package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;
    @BeforeEach // 메서드 실행전 무조건 beforeEach 실행함으로써 AppConfig를 할당해줌
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder() {
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // VIP면 1000원할인이니까 검증되는지 확인
    }

//    @Test // 필드주입 테스트
//    void filedInjectionTest() {
//        OrderServiceImpl orderService1 = new OrderServiceImpl();
//
//        orderService1.setMemberRepository(new MemoryMemberRepository());
//        orderService1.setDiscountPolicy(new FixDiscountPolicy());
//
//        orderService1.createOrder(1L, "itemAA", 10000);
//    }
}
