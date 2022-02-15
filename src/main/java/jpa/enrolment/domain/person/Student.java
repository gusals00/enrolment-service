package jpa.enrolment.domain.person;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Student extends Person{

    private int studentLevel;
}
