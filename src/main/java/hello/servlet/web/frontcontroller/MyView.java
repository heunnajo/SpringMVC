package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;// "WEB-INF/views/new-form.jsp"

    public MyView(String viewPath){
        this.viewPath = viewPath;
    }
    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req,res);
    }
    //jsp는 request.getAttribute()를 하기 때문에
    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        modelToRequestAttribute(model,req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req,res);
    }
    private void modelToRequestAttribute(Map<String,Object> model, HttpServletRequest req){
//        key,value로 루프를 돌리는 Java8 문법
//        model.forEach((key,value)->req.setAttribute(key,value));
        model.forEach((key,value)->req.setAttribute(key,value));
    }
}
