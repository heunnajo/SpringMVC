package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v32.ControllerV32;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV32HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV32);
    }
    //프론트 컨트롤러에서 supports로 ControllerV32만 걸러서 아래의 handle 메서드를 실행할 것이기 때문에
    //handle 메서드는 handler가 ControllerV32라고 확정. 생각해도 된다.
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse res, Object handler) throws ServletException, IOException {
        ControllerV32 controller = (ControllerV32) handler;
        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);

        return mv;
    }
    private Map<String, String> createParamMap(HttpServletRequest req) {
        //컨트롤러의 process에 Map<String,String> paramMap을 넘겨줘야한다!!!!!!
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

}
