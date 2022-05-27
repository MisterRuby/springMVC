package ruby.exception.exHandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ruby.exception.api.ApiExceptionV2Controller;
import ruby.exception.exHandler.ErrorResult;
import ruby.exception.exception.UserException;

/**
 * @ControllerAdvice, @RestControllerAdvice (@ControllerAdvice + @ResponseBody)
 * 대상으로 지정한 컨트롤러에 @ExceptionHandler, @InitBinder 기능을 부여해주는 역할
 *  - 컨트롤러를 대상으로 지정할 수 있으며 대상을 별도로 지정하지 않으면 모든 컨트롤러에 적용
 */
@Slf4j
//@RestControllerAdvice("ruby.exception.api")
@RestControllerAdvice(assignableTypes = {ApiExceptionV2Controller.class})
public class ExControllerAdvice {

    /**
     * 컨트롤러 내의 핸들러 메소드 내에서 예외 발생시 ExceptionHandlerExceptionResolver 에서 해당 메소드를 호출하여 처리
     *  - WAS 로 정상으로 응답 처리되어 BasicErrorController 등의 처리가 발생하지 않는다.
     *  - 예외 상태 코드를 바꿔주지 않으면 정상 처리되어 200 응답이 되어버리므로 예외에 맞는 상태 코드를 @ResponseStatus 또는
     *      ResponseEntity 등을 통해 응답 코드를 변경해주어야 한다.
     *  - @ExceptionHandler 에서 지정한 예외와 파라미터로 받을 예외 타입이 동일하다면 예외 지정을 생략가능
     *  - 리턴 값 처리는 핸들러 메소드와 거의 비슷
     *      - @Controller 내부의 핸들러 메소드의 반환타입이 String 인 경우 해당 뷰 템플릿으로 렌더링하여 처리
     *      - @RestController 내부의 핸들러 메소드의 경우에는 반환타입을 메시지 컨버터를 통해 HTTP 메시지 바디에 담아서 처리
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[illegalExHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[userExHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER_EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    /**
     * 자식 예외들이 처리하지 못한 예외를 처리하는 공통 예외처리 핸들러
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exHandler] ex", e);
        return new ErrorResult("EX", "내부 오류!!!");
    }

}
