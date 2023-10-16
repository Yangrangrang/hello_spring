package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    // jpa는 EntityManager라는 걸로 모든게 동작
    // jpa를 쓰려면 EntityManager를 주입받아야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist > 영속하다, 영구저장하다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = : name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return  result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
        List<Member> result = em.createQuery("select * from Member m", Member.class)
                .getResultList();
        return result;
        */

        // command + option + N (inline) & control + t 해서 검색할수도 있음
        return em.createQuery("select m from Member m", Member.class)   // jpql쿼리 언어 (객체를 대상으로 쿼리를 날리면 sql로 번역이 됨)
                .getResultList();
    }
}
