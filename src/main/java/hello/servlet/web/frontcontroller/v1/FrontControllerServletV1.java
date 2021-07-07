package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    //맵핑
    private Map<String,ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form",new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members",new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = req.getRequestURI();// 이것은 /front-controller/v1/members/new-form와 같다
        //요청 URI가 key가 되고 우리가 정의한 URI라면 맵핑했던 value(웹페이지)를 리턴한다!
        //다형성으로 인해 부모인 ControllerV1으로 받을 수 있다!
        //왜냐하면 아래 Controller변수인 controller는 사실 MemberSaveControllerV1 또는 MemberListControllerV1이기 때문이다!
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //잘 조회됐다면 컨트롤러 인터페이스의 process 메서드 실행한다!
        //다형성에 의해 오버라이드된 메서드가 호출된다! 소름~! 그래서 같은 process 이름으로 메서드를 호출했구나! 대박
        controller.process(req,resp);
    }
}
