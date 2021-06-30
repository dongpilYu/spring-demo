package hello.core;

import hello.core.member.*;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // 3단계 스프링 컨테이너에 등록된 빈들을 조회
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 2단계 구성 객체를 생성한 후, 객체를 전달받음
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // 1단계 의존관계 역전을 하지 않고, 객체를 직접 만듦
        // MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
