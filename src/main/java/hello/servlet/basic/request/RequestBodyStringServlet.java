package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //메시지 바디의 내용을 바이트 코드로 바로 얻을 수 있다
        ServletInputStream inputStream = request.getInputStream();
        //위의 바이트코드를 스트링으로 바꾼다!
        //여러가지 방법이 있지만 여기서는 스트링이 제공하는 유틸리티를 사용한다.
        //바이트를 문자로 변환할 땐 어떤 인코딩인지 알려줘야한다!(현재 대부분 UTF-8 사용)
        //문자를 바이트로 바꿀 때도  어떤 인코딩인지 알려줘야함.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");
    }
}
