package jpa.enrolment.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    private int id;

    private int number;
    private String name;
}


