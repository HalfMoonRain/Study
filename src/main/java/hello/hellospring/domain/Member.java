package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {
    //Generation.Identity 는 DB가 알아서 Id값을 생성 해준다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
