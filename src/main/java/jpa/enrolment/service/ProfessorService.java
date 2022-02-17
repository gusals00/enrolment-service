package jpa.enrolment.service;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Transactional
    public void update(ProfessorUpdateDTO professorUpdateDTO) {
        Professor professor = professorRepository.findOne(professorUpdateDTO.getId());
        professor.change(professorUpdateDTO);
    }

    public void saveProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public List<Professor> findProfessors() {
        return professorRepository.findAll();
    }

    public Professor findOne(Long professorId) {
        return professorRepository.findOne(professorId);
    }
}
