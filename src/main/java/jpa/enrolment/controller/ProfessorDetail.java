package jpa.enrolment.controller;

import jpa.enrolment.domain.person.Professor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfessorDetail {

    private Long id;
    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;

    private String phoneNumber;
    private String labNumber;

    private String departmentName;

    public ProfessorDetail(Professor professor) {
        id = professor.getId();
        ssn = professor.getSsn();
        name = professor.getName();
        email = professor.getEmail();
        loginId = professor.getLoginId();
        loginPw = professor.getLoginPw();
        phoneNumber = professor.getPhoneNumber();
        labNumber = professor.getLabNumber();
        departmentName = professor.getDepartment().getName();

    }
}
