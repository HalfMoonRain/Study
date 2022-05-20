package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemoryMemberReopsitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    //새로 MemoryMemberRepository를 만들 경우 기존의 MemberService.java에서 만들어진 거랑 다른 인스턴스이다.
    //이를 해결 하기 위해서 Member Service에서 Repository를 바꿔준다. --> 외부에서 repository를 넣어주게 변경 원래 파일과 Test파일 모두다
    //DI Dependency Injections
    //
    MemoryMemberReopsitory memberReopsitory ;

    //외부에서 넣어주게 바꿔주기
    @BeforeEach
    public void beforeEaach(){
        memberReopsitory = new MemoryMemberReopsitory();
        memberService = new MemberService(memberReopsitory);
    }

    @AfterEach
    public void afterEach() {
        memberReopsitory.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findmember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findmember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try catch문으로 오류가 생기는지 검증할 수 도 있지만 assertThrows로 터지는지 확인 할 수도 있다.
        이때 들어오는 문구를 확인하기 위해서 assertThat을 사용하면 된다.
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        //then
    }

}