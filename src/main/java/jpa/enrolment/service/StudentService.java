package jpa.enrolment.service;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.Student;
import jpa.enrolment.dto.StudentUpdateDTO;
import jpa.enrolment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public void update(StudentUpdateDTO studentUpdateDTO){
        Student student = studentRepository.findOne(studentUpdateDTO.getId());
        student.change(studentUpdateDTO);
    }

    @Transactional
    public void saveStudent(Student student){studentRepository.save(student);}

    public List<Student> findStudent() {
        return studentRepository.findAll();
    }

    public Student findOne(Long studentId) {
        return studentRepository.findOne(studentId);
    }

    public Long loginStudent(String id, String pw) {
        return studentRepository.login(id, pw);
    }
}
