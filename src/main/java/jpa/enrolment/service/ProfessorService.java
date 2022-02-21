package jpa.enrolment.service;

import jpa.enrolment.domain.Department;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.domain.person.dto.update.ProfessorUpdateDTO;
import jpa.enrolment.repository.DepartmentRepository;
import jpa.enrolment.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;
    @Transactional
    public Long update(Long id,ProfessorUpdateParam professorUpdateParam) {
        Optional<Professor> findProfessor = professorRepository.findOne(id);
        Optional<Department> findDepartment = departmentRepository.findById(professorUpdateParam.getDepartmentId());
        // 교수,학과가 없는거면 어카지??

        ProfessorUpdateDTO professorUpdateDTO = new ProfessorUpdateDTO(professorUpdateParam.getSsn(), professorUpdateParam.getName(), professorUpdateParam.getEmail(), professorUpdateParam.getLoginId(), professorUpdateParam.getLoginPw(), professorUpdateParam.getPhoneNumber(), professorUpdateParam.getLabNumber(), findDepartment.get());
        findProfessor.get().change(professorUpdateDTO);
        log.info("update professor id={},loginId={},name={}",findProfessor.get().getId(),findProfessor.get().getLoginId(),findProfessor.get().getName());
        return findProfessor.get().getId();
    }

    public List<Professor> findProfessors() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findOne(Long professorId) {
        return professorRepository.findOne(professorId);
    }

    public Long loginProfessor(String id, String pw) {
        return professorRepository.login(id, pw);
    }

    @Transactional
    public Long joinProfessor(Professor professor){
        validateLoginId(professor.getLoginId());
        professorRepository.save(professor);

        log.info("join professor id={},loginId={},name={}",professor.getId(),professor.getLoginId(),professor.getName());

        return professor.getId();
    }

    public void validateLoginId(String loginId) {
        if (!professorRepository.findByLoginId(loginId).isEmpty()) {
            throw new IllegalStateException("이미 가입된 professor가 있습니다.");
        }
    }

    @Transactional
    public Long delete(Long id){
        return professorRepository.delete(id);
    }
}
