package jpa.enrolment.domain.person;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Professor extends Person {

    private String phoneNumber;
    private String labNumber;
}
