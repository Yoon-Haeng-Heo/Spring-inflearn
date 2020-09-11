package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {            //service는 비즈니스 로직에 의존적이게 용어를 선택함

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
         this.memberRepository = memberRepository;    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        //만약 같은 이름이 있으면 안된다는 가정이 있다면
        //Optional을 통해서 null일 가능성을 한 번 체크하도록 한다.
        //Optional<Member> result = memberRepository.findByName(member.getName());

        /*result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.")
        });*/
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
       return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
