package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    /**
     * MessageCodesResolver
     *  - 검증 오류 코드로 메시지 코드들을 생성
     *  - 기본 메시지 생성 규칙
     *      -객체 오류
     *          - 다음 순서로 2가지 코드 생성
     *              1. code + "." objectName
     *              2. code
     *      - 필드 오류
     *          - 다음 순서로 4가지 메시지 코드 생성
     *              1. code + "." + objectName + "." + field
     *              2. code + "." + field
     *              3. code + "." + fieldType
     *              4. code
     *
     */
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();


    @Test
    @DisplayName("messageCodes")
    void messageCodesResolverObject() {
        String errorCode = "required";
        String objectName = "item";

        String[] messageCodes = codesResolver.resolveMessageCodes(errorCode, objectName);

        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        assertThat(messageCodes).containsExactly("required.item", "required");

//        messageCode = required.item
//        messageCode = required
    }

    @Test
    @DisplayName("messageCodes")
    void messageCodesResolverField() {
        String errorCode = "required";
        String objectName = "item";
        String field = "itemName";

        String[] messageCodes = codesResolver.resolveMessageCodes(errorCode, objectName, field, String.class);

        // 가장 자세한
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        assertThat(messageCodes).containsExactly(
                "required.item.itemName", "required.itemName", "required.java.lang.String", "required");

//        messageCode = required.item.itemName
//        messageCode = required.itemName
//        messageCode = required.java.lang.String
//        messageCode = required
    }
}
