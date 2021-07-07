package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    //ControllerV2는 MyView를 생성하고 반환만 한다!
    MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
