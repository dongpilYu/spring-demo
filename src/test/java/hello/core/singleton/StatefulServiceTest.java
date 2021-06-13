/*
    스프링 빈은 항상 무상태로 설계해야 함을 알려주는 예제
    멀티 스레드 환경에서 싱글톤으로 생성된 객체는 상태를 공유하므로
    상태를 유지해서는 안된다.
 */
package hello.core.singleton;

import hello.core.singleton.StatefulService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A, 10000원 주문
        statefulService1.order("userA", 10000);
        // Thread B, 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price: " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
