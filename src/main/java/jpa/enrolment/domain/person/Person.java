package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Person {

    @Id @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

}
