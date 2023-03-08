package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 구현체에다가 적용해야 스프링 빈에 등록됨.
public class MemberServiceImpl implements MemberService {

    /*기존 객체참조를 하지않고( 의존하지 않고 ) 필드를 생성자로 만들고 AppConfig에서 관리 -> 추상화에만 의존 DIP, 생성자 주입*/
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // AppConfig보면 memberRepository 매개변수에 new MemoryMemberRepository()가 들어가있으므로 this.memberRepository에는 Memory가 들어가고 위 필드에 메모리 생성자가 주입됨.
    @Autowired /*Component로 등록하고 Autowired로 MemberRepository를 자동주입해줌 ac.getBean(MemberRepositroy.class)가 들어가는거임*/
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
