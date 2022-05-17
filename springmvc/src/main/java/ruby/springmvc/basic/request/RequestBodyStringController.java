package ruby.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RequestBodyStringController {

    /**
     * HttpEntity
     *  - HTTP header, body 정보를 편리하게 조회하거나 반환
     *      RequestEntity
     *          - HttpEntity 를 상속받은 클래스. 요청에서 사용
     *      ResponseEntity
     *          - HttpEntity 를 상속받은 클래스. 응답에서 사용.
     */
    @PostMapping("/request-body-v1")
    public HttpEntity<String> requestBodyStringV1(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();         // HTTP message body 에 있는 값을 꺼낸다.
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    /**
     * RequestEntity
     *  - HttpEntity 를 상속받은 클래스. 요청에서 사용
     * ResponseEntity
     *  - HttpEntity 를 상속받은 클래스. 응답에서 사용.
     */
    @PostMapping("/request-body-v2")
    public ResponseEntity<String> requestBodyStringV2(RequestEntity<String> requestEntity) {
        String messageBody = requestEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @RequestBody
     *  - 파라미터에 HTTP message body 값을 바인딩
     * @ResponseBody
     *  - 응답 결과를 HTTP message body 에 직접 담아서 전달
     */
    @ResponseBody
    @PostMapping("/request-body-v3")
    public String requestBodyStringV3(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
