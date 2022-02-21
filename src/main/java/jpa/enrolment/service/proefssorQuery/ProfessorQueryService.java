package jpa.enrolment.service.proefssorQuery;

import jpa.enrolment.domain.person.Professor;
import jpa.enrolment.dto.ProfessorUpdateDTO;
import jpa.enrolment.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProfessorQueryService {

    private final ProfessorRepository professorRepository;

    public List<ProfessorQueryDTO> professorSimpleList(String name){
        return professorRepository.findByName(name).stream()
                .map(p ->new ProfessorQueryDTO(p))
                .collect(Collectors.toList());
    }

    public List<ProfessorQueryDTO> professorSimpleListAll(){
        return professorRepository.findAll().stream()
                .map(p->new ProfessorQueryDTO(p))
                .collect(Collectors.toList());
    }
}


