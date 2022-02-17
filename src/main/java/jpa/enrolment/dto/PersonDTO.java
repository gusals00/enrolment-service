package jpa.enrolment.dto;

import jpa.enrolment.domain.Department;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDTO {
    private Long id;

    public PersonDTO() {
    }

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;
    private Department department;

    public PersonDTO(Long id, String ssn, String name, String email, String loginId, String loginPw, Department department) {
        this.id = id;
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.department = department;
    }
}
