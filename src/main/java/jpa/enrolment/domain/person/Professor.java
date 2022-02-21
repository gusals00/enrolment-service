package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.dto.PersonDTO;
import jpa.enrolment.domain.person.dto.update.ProfessorUpdateDTO;
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

    public void change(ProfessorUpdateDTO professorUpdateDTO) {
        labNumber = professorUpdateDTO.getLabNumber();
        phoneNumber = professorUpdateDTO.getPhoneNumber();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(professorUpdateDTO.getEmail());
        personDTO.setDepartment(professorUpdateDTO.getDepartment());
        personDTO.setLoginId(professorUpdateDTO.getLoginId());
        personDTO.setLoginPw(professorUpdateDTO.getLoginPw());
        personDTO.setSsn(professorUpdateDTO.getSsn());
        personDTO.setName(professorUpdateDTO.getName());
        changePerson(personDTO);
    }
}
