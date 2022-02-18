package jpa.enrolment.dto;

import jpa.enrolment.domain.Department;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentUpdateDTO {

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;
    private Department department;

    private int studentLevel;

    public StudentUpdateDTO(String ssn, String name, String email, String loginId, String loginPw, Department department, int studentLevel) {
        this.ssn = ssn;
        this.name = name;
        this.email = email;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.department = department;
        this.studentLevel = studentLevel;
    }
}
