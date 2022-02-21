package jpa.enrolment.web.controller;

import jpa.enrolment.domain.Department;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentChooseDTO {

    private Long departmentId;
    private String departmentName;

    public DepartmentChooseDTO(Department department){
        departmentId =department.getId();
        departmentName = department.getName();
    }

}
