package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // http 요청당 하나씩 생성되며 http 요청 끝나는 시점에 소멸 , 가짜 프록시를 만듦
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid +"]"+" ["+requestURL+"] "+message);
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct // 스코프 지정시 초기화메서드를 사용하자.
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid +"] request scope been create: "+this);
    }

    @PreDestroy // 스코프 지정시 종료메서드를 사용하자.
    public void close() {
        System.out.println("["+uuid +"] request scope been close: "+this);
    }
}
