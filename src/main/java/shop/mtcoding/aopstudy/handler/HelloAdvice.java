package shop.mtcoding.aopstudy.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAdvice {

    // 깃발에 별칭주기
    @Pointcut("@annotation(shop.mtcoding.aopstudy.handler.aop.Hello)")
    public void hello() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping() {
    }

    // ProceedingJoinPoint : 리플렉션 한 이 후의 메서드(핸들러) 정보가 다 들어가 있음
    @Around("hello()")
    public Object helloAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        System.out.println("파라미터 사이즈 : " + args.length);

        for (Object arg : args) {
            if (arg instanceof String) {
                String username = (String) arg;
                System.out.println(username + "님 안녕");
            }
        }
        return jp.proceed(); // 이렇게 하면 해당 메서드로 진입
    }

}
