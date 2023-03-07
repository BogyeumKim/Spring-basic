package hello.core.singleton;

import org.junit.jupiter.api.Test;

public class SingletonService {

    /*static 이라 시작시 메모리에 바로 할당되며 instance = new Singleton 객체를 생성해놓고 getInstance할때마다 처음 만들어진 객체만 참조하게됨.*/
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
