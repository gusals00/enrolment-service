package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor extends Person {

    private String phoneNumber;
    private String labNumber;

    protected Professor(String ssn, String name, String email, String loginId, String loginPw, Department department, String phoneNumber, String labNumber) {
        super(ssn, name, email, loginId, loginPw, department);
        this.phoneNumber = phoneNumber;
        this.labNumber = labNumber;
    }

    public static Professor createProfessor(String ssn, String name, String email, String loginId, String loginPw, Department department, String phoneNumber, String labNumber){
        return new Professor(ssn,  name,  email,  loginId,  loginPw,  department,  phoneNumber,  labNumber);
    }
}
