package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
//    MemberRepository repository = new MemoryMemberRepository();

    // MemoryMemberRepository 이거만 테스트 하는거니까 일단 아래처럼 변경
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // @AfterEach 아래 메소드가 동작이 끝날때 마다 실행을 함 (콜백메소드라고 보면 됨)
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

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
        assertThat(member).isEqualTo(result);

        // 위 두 코드는 같이 import가 안되는걸로 확인 된다.
        // error남 > 위 import문을 지우고 다시 작성하면 됨.
        // Assertions는 option+enter누르면 나오는 Add on~ 뜨는 걸 클릭 하면 static으로 들어와서 Assertions 생략 가능
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    // 테스트는 순서의 보장이 안됨 (의존관계 없이 만들어져야함)
    // 그래서 테스트가 하나가 끝나고 나면 데이터를 지워줘야 한다.
}
