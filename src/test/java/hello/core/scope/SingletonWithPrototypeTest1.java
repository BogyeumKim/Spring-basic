package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1); // 위에 prototypeBean1과 다른 객체이기 때문에

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); // 싱글톤 프로토타입 같이 스캔

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1); // 같은객체 -> 아래 생성자주입이 아닌 ObjectProvider 타입 기능으로 새로운빈(객체) 생성
    }

    @Scope("singleton")
    static class ClientBean{
/*
        private final PrototypeBean prototypeBean; // 생성시점에 주입되어서 계속 같은 객체를 참조함
        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
*/

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 새로운 프로토타입 빈(객체) 생성
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // javax Provider .get() 메서드
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destory");
        }
    }
}
