package jpa.enrolment.service;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentUpdateParam {

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;

    private int studentLevel;
    private Long departmentId;

    public StudentUpdateParam(Student student) {
        this.ssn = student.getSsn();
        this.name = student.getName();
        this.email = student.getEmail();
        this.loginId = student.getLoginId();
        this.loginPw = student.getLoginPw();
        this.studentLevel = student.getStudentLevel();
        this.departmentId = student.getDepartment().getId();
    }
}