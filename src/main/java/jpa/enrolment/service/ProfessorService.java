package jpa.enrolment.service;

import jpa.enrolment.domain.person.Admin;
import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Transactional
    public Long update(Long id,ProfessorUpdateDTO professorUpdateDTO) {
        Optional<Professor> professor = professorRepository.findOne(id);
        professor.get().change(professorUpdateDTO);
        return professor.get().getId();
    }

    @Transactional
    public void saveProfessor(Professor professor) {
        professorRepository.save(professor);
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
        return professor.getId();
    }

    public void validateLoginId(String loginId) {
        if (!professorRepository.findByLoginId(loginId).isEmpty()) {
            throw new IllegalStateException("이미 가입된 professor가 있습니다.");
        }
    }
}
