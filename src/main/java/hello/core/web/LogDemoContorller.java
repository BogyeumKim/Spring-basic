package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // 생성자 롬복 주입
public class LogDemoContorller {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        MyLogger myLogger = myLoggerProvider.getObject(); // 스코프를 여기서 넣어줌으로써 에러해결, 이부분에서 init() 실행되며 uuid 만들어짐
        String requestURL = request.getRequestURL().toString();

        /*myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$3b44b1ff 바이트코드 조작하여 가짜 프록시를 생성한거임*/
        System.out.println("myLogger = "+myLogger.getClass());

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
