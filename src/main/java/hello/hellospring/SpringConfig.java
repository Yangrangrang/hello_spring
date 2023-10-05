package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration  // 어노테이션을 사용하지 않고 자바에서 수동으로 bean 등록 해주는 방법
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }

    // 자바코드로 직접 스프링빈을 등록하는 방법
    // 어노테이션과 직접빈등록의 장단점이 있다.
    // DI의 3가지 방법이 있다. 생성자주입, 필드주입(별로 안좋다.why?바꿀수 있는 방법이 없다.), setter주입(단점은 public으로 열려 있어야 함.누군가가 잘못바꿀 가능성이 있다)
    // 상황에 따라

}
