package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class); // 스프링 컨테이너에 주입

        StatefulService statefulService1 = ac.getBean(StatefulService.class); // 싱글톤
        StatefulService statefulService2 = ac.getBean(StatefulService.class); // 싱글톤

        // ThreadA : A사용자 10000원 주문
//        statefulService1.order("userA",10000); // 싱글톤 문제 수정 전
        int userA = statefulService1.order("userA", 10000);
        // ThreadA : B사용자 20000원 주문 // 싱글톤 문제 수정 전
        int userB = statefulService2.order("userB", 20000);

        // ThreadA : A사용자가 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
        System.out.println("price = " + userA);

        /*싱글톤 문제 수정 전 출력결과
        * name = userA pirce = 10000
        name = userB pirce = 20000
        price = 20000*/

        /*수정 후 결과
        * name = userA pirce = 10000
        name = userB pirce = 20000
        price = 10000*/

        // 같은 객체를 참조하니까 후순위의 정보가 저장됨 StatefulService에서 하나의 필드(price)를 사용하기때문에
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}