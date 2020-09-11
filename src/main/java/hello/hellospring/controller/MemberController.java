package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

//    @Autowired memberService를 누구나 접근 가능하게 된다... 별로 좋지 않은 방법...
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @Autowired
    public MemberController(MemberService memberService) {
        //오류가 뜨는 이유는 memberService 클래스는 단순 자바 클래스이므로 인식을 못함
        //member
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //->getmapping한것의 함수에서 리턴하면 그 리턴한 값을 template에서 찾음
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")    //->HTTP 메소드 GET말고 POST
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
