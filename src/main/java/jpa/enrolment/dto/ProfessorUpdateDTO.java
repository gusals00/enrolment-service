package jpa.enrolment.dto;

import jpa.enrolment.domain.Department;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProfessorUpdateDTO {

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;
    private String phoneNumber;
    private String labNumber;
    private Department department;

    public ProfessorUpdateDTO(String ssn, String name, String email, String loginId, String loginPw, String phoneNumber, String labNumber, Department department) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.phoneNumber = phoneNumber;
        this.labNumber = labNumber;
        this.department = department;
    }


}
