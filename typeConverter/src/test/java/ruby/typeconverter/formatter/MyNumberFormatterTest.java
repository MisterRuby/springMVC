package ruby.typeconverter.formatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class MyNumberFormatterTest {
    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    @DisplayName("쉼표를 포함한 문자열을 숫자로 변환")
    void parse() throws ParseException {
        Number number = formatter.parse("1,000,000", Locale.ENGLISH);
        assertThat(number).isEqualTo(1000000L);
    }

    @Test
    @DisplayName("숫자를 쉼표를 포함한 문자열 형태로 변환")
    void print() {
        String result = formatter.print(1000000, Locale.ENGLISH);
        assertThat(result).isEqualTo("1,000,000");
    }
}