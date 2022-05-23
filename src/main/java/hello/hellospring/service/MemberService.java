package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //Controller에서 사용할수 있게 해준다.
public class MemberService {

    public final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원가입
         */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try{
            validateDuplicate(member); //같은 이름이 있는 중복 회원 X
            memberRepository.save(member);
            return member.getId();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join" + timeMs + "ms");
        }


    }

    private void validateDuplicate(Member member) {
        /*optional을 이용해서 ifpresent기능을 사용한다.
        그런데 이미 Optional을 사용 했기 때문에 바로 사용이 가능하다.
        또한 join 에서 이 기능을 사용 할 수 있었겠지만 refactorying을 통해서 메소드를 따로 빼준다.
        * */
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){

        long start = System.currentTimeMillis();

        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join" + timeMs + "ms");
        }


    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
