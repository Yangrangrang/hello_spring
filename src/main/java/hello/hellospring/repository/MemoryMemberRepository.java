package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이 반환 될 수 있다. Optional.ofNullable로 감싸서 반환해주면 클라이언트에서 처리를 해줄 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식
        return store.values().stream()
                // member에서 member.getName이 파라미터의 name 과 일치하는지 확인 (같은 경우에만 필터링이 됨)
                .filter(member -> member.getName().equals(name))
                // 이중에서 찾으면 하나만 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();  // store를 싹 비움
    }
}
