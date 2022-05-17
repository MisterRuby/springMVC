package ruby.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;
import ruby.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // JSON 결과를 파싱해서 자바 객체로 변환
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        // 스프링 mvc 를 적용한다면 ObjectMapper 처리를 자동으로 해준다.
        HelloData helloData = mapper.readValue(messageBody, HelloData.class);
        System.out.println(helloData);
    }
}
