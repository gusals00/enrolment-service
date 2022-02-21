package jpa.enrolment.service;

import jpa.enrolment.domain.person.Professor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorUpdateParam {

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;

    private String phoneNumber;
    private String labNumber;

    private Long departmentId;

    public ProfessorUpdateParam(Professor professor) {
        this.ssn = professor.getSsn();
        this.name = professor.getName();
        this.email = professor.getEmail();
        this.loginId = professor.getLoginId();
        this.loginPw = professor.getLoginPw();
        this.phoneNumber = professor.getPhoneNumber();
        this.labNumber = professor.getLabNumber();
        this.departmentId = professor.getDepartment().getId();
    }
}