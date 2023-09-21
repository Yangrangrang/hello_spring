package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("hanna");

        repository.save(member);
        // Optnal에서 값을 꺼낼 때는 get()을 사용 {물론 get으로 바로 꺼내는게 좋은 방식은 아니지만 test에서는 사용해도 무관}
        Member result = repository.findById(member.getId()).get();

        //검증
        System.out.println("result = " + (result == member));   // 단순하게 이런식으로도 가능
//        Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);

        // 위 두 코드는 같이 import가 안되는걸로 확인 된다.
        // error남 > 위 import문을 지우고 다시 작성하면 됨.
    }
}
