package jpa.enrolment.web.controller.admin;

import jpa.enrolment.domain.person.Student;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentDetail {

    private Long id;
    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;

    private String departmentName;

    private int studentLevel;

    public StudentDetail(Student student) {
        this.id = student.getId();
        this.ssn = student.getSsn();
        this.name = student.getName();
        this.email = student.getEmail();
        this.loginId = student.getLoginId();
        this.loginPw = student.getLoginPw();
        this.departmentName = student.getDepartment().getName();
        this.studentLevel = student.getStudentLevel();
    }
}
