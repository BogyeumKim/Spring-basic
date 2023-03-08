package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

//        BeanB beanB = ac.getBean("beanB", BeanB.class); // 아래에서 exclude로 제외시킴 그래서 NoSuchBean 에러뜸
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            /*type = FilterType.ANNOTATION 은 기본 디폴트값이라 생략가능 애너테이션을 인식하는거임*/
            includeFilters = @ComponentScan.Filter(classes = MyIncludeComponent.class), // BeanA 스프링 빈 추가
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class) // BeanB 스프링 빈에 추가안함
    )

    static class ComponentFilterAppConfig {

    }
}
