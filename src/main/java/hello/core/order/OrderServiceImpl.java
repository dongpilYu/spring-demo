package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memoryMemberRepository, DiscountPolicy fixDiscountPolicy) {
        memberRepository = memoryMemberRepository;
        discountPolicy = fixDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, 10000);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
