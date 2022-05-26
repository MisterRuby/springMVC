package ruby.exception;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ruby.exception.filter.LogFilter;
import ruby.exception.interceptor.LogInterceptor;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

// Configuration : 해당 클래스 Bean 으로 등록 + @Bean 반환 객체 Bean 등록
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        // FilterRegistrationBean 의 기본 DispatcherType 설정은 REQUEST 이다.
        // 클라이언트 요청 및 오류 페이지 요청에도 필터가 호출되도록 설정
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // interceptor 는 dispatcherType 을 지정할 수 없다.
        // 때문에 excludePathPatterns 를 통해 제외할 경로를 설정
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "*.ico", "/error", "/error/**");
    }
}
