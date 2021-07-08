package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Controller 붙이는 이유
//컨트롤러를 등록해서 컴포넌트 스캔을 통해 RequestMappingHandlerMapping의 매핑 정보로 인식한다!!
//스프링 MVC에서 애노테이션 기반의 컨트롤러로 인식한다!!!!!⭐️
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form", method= RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        //파라미터로 바로 필요한 데이터들 받고 비즈니스 로직 실행!
        Member member = new Member(username,age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping()
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members",members);
        return "members";
    }
}
