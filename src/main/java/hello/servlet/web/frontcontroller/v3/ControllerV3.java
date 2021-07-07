package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    //V2와 비교해보면 서블릿에 종속적이지 않은 간결해진 코드를 볼 수 있다!
    //MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

    ModelView process(Map<String,String> paramMap);
}
