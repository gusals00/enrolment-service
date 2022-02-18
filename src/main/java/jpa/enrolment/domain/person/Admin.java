package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import jpa.enrolment.dto.AdminUpdateDTO;
import jpa.enrolment.dto.PersonDTO;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import lombok.AccessLevel;
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

    public static Admin createAdmin(String ssn, String name, String email, String loginId, String loginPw, Department department){
        return new Admin( ssn,  name,  email,  loginId,  loginPw,  department);
    }

    public void change(AdminUpdateDTO adminUpdateDTO) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(adminUpdateDTO.getEmail());
        personDTO.setDepartment(adminUpdateDTO.getDepartment());
        personDTO.setLoginId(adminUpdateDTO.getLoginId());
        personDTO.setLoginPw(adminUpdateDTO.getLoginPw());
        personDTO.setSsn(adminUpdateDTO.getSsn());
        personDTO.setName(adminUpdateDTO.getName());
        changePerson(personDTO);
    }
}

