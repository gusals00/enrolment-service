package jpa.enrolment.domain.person.dto.update;

import jpa.enrolment.domain.Department;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminUpdateDTO {

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;

    private Department department;

    public AdminUpdateDTO( String ssn, String name, String email, String loginId, String loginPw, Department department) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.department = department;
    }
}
