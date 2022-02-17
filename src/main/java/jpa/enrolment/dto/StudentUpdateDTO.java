package jpa.enrolment.dto;

import jpa.enrolment.domain.Department;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentUpdateDTO {
    private Long id;

    private String ssn;
    private String name;
    private String email;
    private String loginId;
    private String loginPw;
    private Department department;

    private int studentLevel;

}
