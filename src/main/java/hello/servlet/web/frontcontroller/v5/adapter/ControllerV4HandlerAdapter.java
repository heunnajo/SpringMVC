package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse res, Object handler) throws ServletException, IOException {
        //handler를 V4로 캐스팅한다!(전체 부모 객체 Object로 들어온 애를 그 하위 버전 인터페이스로 캐스팅하는 것이다~!)
        ControllerV4 controller = (ControllerV4) handler;
        
        HashMap<String, Object> model = new HashMap<>();
        Map<String, String> paramMap = createParamMap(req);

        String viewName = controller.process(paramMap, model);//Model과 paramMap 두개 파라미터를 받는다!

        //어댑터는 viewName가지고 ModelView를 생성해준다!!!
        //Model과 View를 셋팅해준다!
        ModelView mv = new ModelView(viewName);//뷰 이름 셋팅
        mv.setModel(model);//모델 셋팅

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
