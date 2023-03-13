package shop.mtcoding.aopstudy.handler.interceptor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.aopstudy.handler.LoginInterceptor;
import shop.mtcoding.aopstudy.handler.aop.SessionUser;
import shop.mtcoding.aopstudy.model.User;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final HttpSession session;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/auth/**");
    }

    // addArgumentResolvers : 특정 매개변수에 값을 주입할 때 사용 (AOP가 두 구현되어 있음)
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerMethodArgumentResolver() {

            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                // excution해서 모든 메서드의 어노테이션을 확인
                boolean check1 = parameter.getParameterAnnotation(SessionUser.class) != null;
                boolean check2 = User.class.equals(parameter.getParameterType());

                return check1 && check2;
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

                return session.getAttribute("principal");
            }

        });
    }

}
