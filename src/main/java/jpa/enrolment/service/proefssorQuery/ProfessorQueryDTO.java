package jpa.enrolment.service.proefssorQuery;

import jpa.enrolment.domain.person.Professor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfessorQueryDTO {

    private Long id;
    private String name;
    private String loginId;
    private String email;

    public ProfessorQueryDTO(Professor p) {
        id = p.getId();
        name = p.getName();
        loginId = p.getLoginId();
        email = p.getEmail();
    }
}
