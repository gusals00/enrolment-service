package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.dto.PersonDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    protected Person(String ssn, String name, String email, String loginId, String loginPw, Department department) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.department = department;
    }

    public void changePerson(PersonDTO personDTO) {
        ssn = personDTO.getSsn();

        name = personDTO.getName();
        email = personDTO.getEmail();
        loginId = personDTO.getLoginId();
        loginPw = personDTO.getLoginPw();
        department = personDTO.getDepartment();
    }

}
