package hello.servlet.web.frontcontroller.v32;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v32.controller.MemberFormControllerV32;
import hello.servlet.web.frontcontroller.v32.controller.MemberListControllerV32;
import hello.servlet.web.frontcontroller.v32.controller.MemberSaveControllerV32;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV32",urlPatterns = "/front-controller/v32/*")
public class FrontControllerServletV32 extends HttpServlet {
    //맵핑
    private Map<String, ControllerV32> controllerMap = new HashMap<>();

    public FrontControllerServletV32() {
        controllerMap.put("/front-controller/v32/members/new-form",new MemberFormControllerV32());
        controllerMap.put("/front-controller/v32/members/save",new MemberSaveControllerV32());
        controllerMap.put("/front-controller/v32/members",new MemberListControllerV32());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = req.getRequestURI();// 이것은 /front-controller/v2/members/new-form와 같다
        ControllerV32 controller = controllerMap.get(requestURI);
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //2.Model 처리
        Map<String, String> paramMap = createParamMap(req);
        //3.View 처리
        ModelView mv = controller.process(paramMap);//MyView가 리턴된다!
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(),req,resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        //컨트롤러의 process에 Map<String,String> paramMap을 넘겨줘야한다!!!!!!
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
