package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", // member패키지 하위만 컴포넌트스캔을 돌림 모든 클래스를 스캔하면 오래걸리니까
//        basePackageClasses = AutoAppConfig.class, // 지정한 클래스 패키지를 스캔함
        /* 위에 아무것도 지정 안하면 @ComponentScan이 붙은 class package가 시작위치가됨. -> hello.core */
        /* 그래서 설정정보 클래스를 프로젝트 최상위에 두고 base~~ 아무것도 안붙임.(관례) */

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) /*excludeFilters기능은 Configuration.class 즉 @Configuration 붙어있는건 다 빼는거임 테스트파일에도 몇개있었음 AppConfig에도 있고
 빼는 이유는 @Component를 사용하여 스프링 빈에등록하는데 AppConfig에도 Bean을 등록함으로써 겹침 테스트용으로 다빼버리는거임*/

public class AutoAppConfig {

}
