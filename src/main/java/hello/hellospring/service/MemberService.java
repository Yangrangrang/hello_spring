package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// service의 네이밍은 비즈니스에 가까운 용어로 명명
// repository는 조금 단순하게 개발적으로 명명
public class MemberService {

    // private final MemberRepository memeberRepository = new MemoryMemberRepository();
    private final MemberRepository memeberRepository;

    public MemberService(MemberRepository memeberRepository) {
        this.memeberRepository = memeberRepository;
    };

    /*
     * 회원가입
     * */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원은 안된다. > validateDuplicateMember
        /*
        Optional<Member> result = memeberRepository.findByName(member.getName());   // 단축키 command+option+v
        // optional이 기 때문에 가능한 로직
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 위에 코드를 리펙토링 하게 되면
        /*
        memeberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        이런식으로도 가능하다
        findByName으로 로직이 있는경우 메소드로 뽑는게 좋다. 단축키 control+t
        control+t는 리펙토링 관련해서 자주 사용하는 단축키
        */

        // 중복회원 검증
        validateDuplicateMember(member);

        memeberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memeberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memeberRepository.findAll();
    }

    /*
     * id를 이용한 부분 회원 조회
     * */
    public Optional<Member> findOne(Long memberId) {
        return memeberRepository.findById(memberId);
    }
}
