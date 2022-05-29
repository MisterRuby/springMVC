package ruby.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    @DisplayName("")
    void formattingConversionServiceTest() {
        // 포멧터 등록
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatter(new MyNumberFormatter());

        Number number = conversionService.convert("1,000,000", Number.class);
        assertThat(number).isEqualTo(1000000L);

        String convert = conversionService.convert(1000000, String.class);
        assertThat(convert).isEqualTo("1,000,000");
    }
}
