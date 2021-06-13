package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepo1 = memberService.getMemberRepository();
        MemberRepository memberRepo2 = orderService.getMemberRepository();
        MemberRepository memberRepo3 = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService -> memberRepo: " + memberRepo1);
        System.out.println("orderService -> memberRepo: "  + memberRepo2);
        System.out.println("memberRepo: " + memberRepo3);

        Assertions.assertThat(memberRepo1).isSameAs(memberRepo2);
        Assertions.assertThat(memberRepo1).isSameAs(memberRepo3);
    }
}
