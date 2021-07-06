package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
1. 파라미터 전송 기능
http://localhost:8080/request-param?username=hello&age=20
다음 전달 데이터를 클라이언트에서 서버로 전송해보자.
전달 데이터
username=hello age=20
 */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //일단 되는지부터 확인!
        //System.out.println("RequestParamServlet.service");
        System.out.println("전체 파라미터 조회 start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> System.out.println(paramName+"paramName = " + request.getParameter(paramName)));//key와 value를 출력!
        System.out.println("전체 파라미터 조회 end");
        //Enumeration<String> parameterNames = request.getParameterNames();
        System.out.println();

        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username"+username);
        }
        response.getWriter().write("ok");
    }
}
