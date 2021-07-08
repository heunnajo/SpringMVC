package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//shift F6 : Rename
public interface MyHandlerAdapter {
    //들어오는 컨트롤러를 지원할 수 있는지 판단
    boolean supports(Object handler);
    ModelView handle(HttpServletRequest req, HttpServletResponse res,Object handler) throws ServletException, IOException;
}
