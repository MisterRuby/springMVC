package ruby.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ruby.springmvc.basic.HelloData;

@Slf4j
@RestController
public class RequestBodyJsonController {

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public HelloData requestBodyJsonV1(HttpEntity<HelloData> httpEntity) {
        log.info("username={}, age={}", httpEntity.getBody().getUsername(), httpEntity.getBody().getAge());

        return httpEntity.getBody();
    }

    @PostMapping("/request-body-json-v2")
    public HelloData requestBodyJsonV2(RequestEntity<HelloData> requestEntity) {
        log.info("username={}, age={}", requestEntity.getBody().getUsername(), requestEntity.getBody().getAge());

        return requestEntity.getBody();
    }
    
    @PostMapping("/request-body-json-v3")
    public HelloData requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
