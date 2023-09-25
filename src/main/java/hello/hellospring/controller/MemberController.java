package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컴포넌트 스캔, 자동의존관계 설정
public class MemberController {

    public final MemberService memberService;

    @Autowired  // 연관관계 ( 연결 해준다)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
