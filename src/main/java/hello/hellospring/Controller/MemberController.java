package hello.hellospring.Controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // Member Service와 연결시킬 때 사용해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
