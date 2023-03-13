package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject(); // 스코프를 여기서 넣어줌으로써 에러해결 init()이 이미 컨트롤러에서 실행되가지고 uuid값등이 있음
        myLogger.log("service id = "+id);
    }
}
