package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    /*고정할인 DiscountPolicy가 FixDiscountPolicy를 참조(의존) 에서
    * 퍼센트할인로변경하려면 ReateDiscountPolicy를 참조(의존)하게 코드를 바꿔야해서 OCP위반*/
//    public final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    public final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /*기존 객체참조를 하지않고(의존하지않고) 필드를 생성자로 만들고 AppConfig에서 관리 -> 추상화에만 의존 DIP , 생성자 주입*/
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*APP Config보면 memberRepository에는 MemoryMemberRe~~ , discountPolicy에는 Fixdisco~~ 가 생성되있으므로
    * 아래 생성자 매게변수에 메모리 ,픽스가 들어가 this.memberRepository와 this.discountPolicy에는 각 메모리멤버 , 픽스가 들어가 필드에 생성자가 주입됨.*/
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
