package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // excludeFilters기능은 Configuration.class 즉 @Configuration 붙어있는건 다 빼는거임 테스트파일에도 몇개있었음 AppConfig에도 있고
// 빼는 이유는 @Component를 사용하여 스프링 빈에등록하는데 AppConfig에도 Bean을 등록함으로써 겹침 테스트용으로 다빼버리는거임

public class AutoAppConfig {

}
