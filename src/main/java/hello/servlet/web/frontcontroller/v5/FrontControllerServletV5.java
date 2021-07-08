package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v32.ControllerV32;
import hello.servlet.web.frontcontroller.v32.controller.MemberFormControllerV32;
import hello.servlet.web.frontcontroller.v32.controller.MemberListControllerV32;
import hello.servlet.web.frontcontroller.v32.controller.MemberSaveControllerV32;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV32HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    //기존코드는 ControllerV4 인터페이스만 들어갔지만 이제는 Object로받아서 모든 인터페이스들을 다 받을 수있도록 설계.
    //private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final Map<String,Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    //생성자
    public FrontControllerServletV5(){
        initHandlerMappingMap();//맵핑 정보 넣는다!Object기 때문에 인터페이스 종료 상관없이 다 들어감.
        initHandlerAdapters();
    }
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v32/members/new-form",new MemberFormControllerV32());
        handlerMappingMap.put("/front-controller/v5/v32/members/save",new MemberSaveControllerV32());
        handlerMappingMap.put("/front-controller/v5/v32/members",new MemberListControllerV32());
    }
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV32HandlerAdapter());//MyHandlerAdapter 구현체
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //URI 맵핑하고 그에 따른 객체 리턴하는 함수로 구현!
        //코드가 깔끔해진다! 아 handler를 찾는구나!
        Object handler = getHandler(req);//핸들러 찾아와.MemberFormControllerV32반환.
        if(handler == null){
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getHandlerAdapter(handler);//핸들러 어댑터 찾아와.
        ModelView mv = adapter.handle(req, res, handler);//실제 컨트롤러 호출해서 ModelView 반환.

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(),req,res);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {//핸들러로 V32가 들어오면
        //handler = MemberFormControllerV32
        for (MyHandlerAdapter adapter : handlerAdapters) {//리스트 다 뒤진다.
            if(adapter.supports(handler)){//V32가 있으면 support는 지원할 수 있으므로 true리턴
                return adapter;//MyHandlerAdapter adapter를 리턴한다!
            }
        }
        throw new IllegalArgumentException("Handler Adapter를 찾을 수 없습니다.handler"+handler);
    }

    private Object getHandler(HttpServletRequest req) {
        //URI가 들어오면 handlerMappingMap에서 찾는다!
        //다양한 URL이 들어올 수  있고, 이에 따라 다양한 인터페이스가 실행되야하기 때문에 Object로 받는다!
        String requestURI = req.getRequestURI();// 이것은 /front-controller/v2/members/new-form와 같다
        return handlerMappingMap.get(requestURI);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
