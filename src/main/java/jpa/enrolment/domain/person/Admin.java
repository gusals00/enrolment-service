package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends Person {

    protected Admin(String ssn, String name, String email, String loginId, String loginPw, Department department) {
        super(ssn, name, email, loginId, loginPw, department);
    }

    public static Admin createAdmin(){
        return new Admin();
    }
}

