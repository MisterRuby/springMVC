package hello.itemservice.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    @DisplayName("message.properties")
    void helloMessage() {
        // message.properties 의 hello 파라미터의 값을 조회
        System.out.println("MessageSourceTest.helloMessage");
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("NoSuchMessageException")
    void notFoundMessageCode() {
        // 파라미터에 해당하는 값이 없을 경우 NoSuchMessageException 발생
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("defaultMessage")
    void defaultMessage() {
        // 파라미터에 해당하는 값이 없을 경우 default Message 출력
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    @DisplayName("argumentMessage")
    void argumentMessage() {
        // 파라미터에 해당하는 값이 없을 경우 default Message 출력
        String result = ms.getMessage("hello.name", new Object[]{"ruby"}, null);
        assertThat(result).isEqualTo("안녕 ruby");
    }

    @Test
    @DisplayName("defaultLanguage")
    void defaultLanguage() {
        // Locale 에 해당하는 properties 가 없을 경우 기본 messages.properties 적용
        String result = ms.getMessage("hello", null, Locale.FRANCE);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("appointLanguage")
    void appointLanguage() {
        // Locale 에 해당하는 properties 가 있을 경우 해당 properties 적용
        String result = ms.getMessage("hello", null, Locale.ENGLISH);
        assertThat(result).isEqualTo("hello");
    }
}
