/*
    writer : dongpil yu
    date : 2021.06.09

    스프링 컨테이너에 등록된 빈을 모두 확인하는 예제 파일
    동일한 타입이 둘 이상일 때, 상속 관계일 때 빈을 조회하는 방법을 강의에서 다루니,
    필요할 떄, 참고하도록 하자.

    추가적으로 스프링 빈 설정 메타 정보인 beanDefinition
    annotation이 아닌 xml 방식으로 설정 형식을 등록하는 방법도 있으니
    필요할 떄, 참고하자.


 */
package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationConfigApplicationContextTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicatioBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName" + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }
    @Test
    @DisplayName("시스템 빈 출력하기")
    void findInfrastructureBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE){
                System.out.println("beanDefinitionName" + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }
}
