package ruby.typeconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ruby.typeconverter.converter.IntegerToStringConverter;
import ruby.typeconverter.converter.IpPortToStringConverter;
import ruby.typeconverter.converter.StringToIntegerConverter;
import ruby.typeconverter.converter.StringToIpPortConverter;
import ruby.typeconverter.formatter.MyNumberFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 스프링 내부에서 사용하는 ConverterService 에 Converter 를 추가
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 같은 타입을 변환하는 컨버터와 포멧터가 모두 등록되어 있다면 컨버터가 우선순위를 갖게 된다.
//        registry.addConverter(new IntegerToStringConverter());
//        registry.addConverter(new StringToIntegerConverter());
        // 컨버터 등록
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new StringToIpPortConverter());

        // 포멧터 등록
        registry.addFormatter(new MyNumberFormatter());
    }
}
