package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity //jpa가 관리 하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // PK를 맵핑 해줘야함
    private Long id;
//    @Column(name = "username") 이런식으로 매핑을 하는것
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
