package ruby.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ruby.springmvc.basic.HelloData;

import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    /**
     * 쿼리 파라미터의 키 값과 변수 명이 동일 하다면 @RequestParam 어노테이션을 생략할 수 있다.
     *  required
     *      - 기본값 true
     *      - false 일 경우 요청시 해당 쿼리 파라미터 값이 없어도 된다.
     *      - 쿼리 파라미터 값이 없을 경우 null 이기 때문에 primitive 타입 사용에 주의해야한다.
     *          - Wrapper 클래스 사용하거나 defaultValue 값을 설정해줘야 함
     *  defaultValue
     *      - 쿼리 파라미터 값이 없거나 빈 문자열일 경우 지정한 값을 적용
     * @param username
     * @param age
     * @return
     */
    @RequestMapping("/request-param")
    public String requestParam(
            @RequestParam String username,
            @RequestParam(required = false, defaultValue = "0") int age) {
//    public String requestParam(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * Map 타입으로 쿼리 파라미터 조회
     */
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @RequestMapping("/request-param-multiMap")
    public String requestParamMultiMap(
            @RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username0={}, username1={}", paramMap.get("username").get(0), paramMap.get("username").get(1));
        return "ok";
    }

    /**
     * 쿼리 파라미터의 이름으로 @ModelAttribute 으로 지정한 객체의 프로퍼티를 찾은 다음 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 바인딩한다.
     * @ModelAttribute 생략 가능
     */
    @RequestMapping("/request-modelAttribute")
    public String modelAttribute(@ModelAttribute HelloData helloData) {
//        public String modelAttribute(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
