package jpa.enrolment.domain.person;

import jpa.enrolment.domain.Department;
import jpa.enrolment.dto.PersonDTO;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.dto.StudentUpdateDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends Person{

    private int studentLevel;

    public Student(String ssn, String name, String email, String loginId, String loginPw, Department department, int studentLevel) {
        super(ssn, name, email, loginId, loginPw, department);
        this.studentLevel = studentLevel;
    }

    public static Student createStudent(String ssn, String name, String email, String loginId, String loginPw, Department department, int studentLevel){
        return new Student(ssn, name, email, loginId, loginPw, department, studentLevel);
    }

    public void change(StudentUpdateDTO studentUpdateDTO) {
        studentLevel = studentUpdateDTO.getStudentLevel();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(studentUpdateDTO.getEmail());
        personDTO.setDepartment(studentUpdateDTO.getDepartment());
        personDTO.setLoginId(studentUpdateDTO.getLoginId());
        personDTO.setLoginPw(studentUpdateDTO.getLoginPw());
        personDTO.setSsn(studentUpdateDTO.getSsn());
        personDTO.setName(studentUpdateDTO.getName());
        changePerson(personDTO);
    }
}
