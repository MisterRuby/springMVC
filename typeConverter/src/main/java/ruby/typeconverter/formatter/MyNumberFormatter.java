package ruby.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

    /**
     * 문자열을 반환타입으로 변환
     */
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text={}, locale={}", text, locale);
        return NumberFormat.getInstance(locale).parse(text);
    }

    /**
     * 지정한 타입을 문자열로 변환
     */
    @Override
    public String print(Number object, Locale locale) {
        log.info("number={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
