package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //1.데이터  가져온다.
        List<Member> members = memberRepository.findAll();
        //2.모델에 담는다.
        req.setAttribute("members",members);//(key,value)
        //3.뷰로 간다. - 뷰패쓰 설정하고 RequestDispatcher.forward 로  뷰로 이동!
        String  viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req,res);
    }
}
