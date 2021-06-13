package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class singletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
        // 싱글톤 패턴이 적용되지 않았기에 다르다.
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);
        // 싱글톤 로직을 직접 구현
        // 스프링 컨테이너가 객체를 싱글톤으로 관리해주니 너무 걱정하지 말자.

    }

    @Test
    @DisplayName("스프링 컨테이너를 통해 싱글톤 패턴 적용")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService2);

        assertThat(memberService1).isSameAs(memberService2);

    }
}
