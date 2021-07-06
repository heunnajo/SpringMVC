package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //[status-line]
        //응답코드 : 의미확인을 위해 숫자 200을 바로 적기보다 상수명을 적어준다.
        resp.setStatus(HttpServletResponse.SC_OK);

        //content(response);
        //resp.setHeader("Content-Type", "text/plain;charset-utf-8");
        resp.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("my-header","hello");//내가 직접 헤더에 들어가는 정보를 만들 수도 있다!

        //[Header 편의 메서드]
        //content(resp);
        //cookie(resp);
        redirect(resp);

        //[message body]
        PrintWriter writer = resp.getWriter();
        writer.println("ok");
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        //Status Codee 302
        //Location: /basic/hello-form.html

//        resp.setStatus(resp.SC_FOUND);//302
//        resp.setHeader("Location","/basic/hello-form.html");
        resp.sendRedirect("/basic/hello-form.html");
    }

    private void content(HttpServletResponse response){
        //Content-Type : text/plain;charset=utf-8
        //Content-Length : 2
        response.setHeader("Content-Type", "text/plain;charset-utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2);생략 시 자동 생성
    }
    private void cookie(HttpServletResponse response){
        //Set-Cookie : myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie","myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600);//600초
        response.addCookie(cookie);
    }
}
