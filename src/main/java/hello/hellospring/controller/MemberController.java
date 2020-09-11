package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        //오류가 뜨는 이유는 memberService 클래스는 단순 자바 클래스이므로 인식을 못함
        //member
        this.memberService = memberService;
    }
}
