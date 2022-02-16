package jpa.enrolment.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Syllabus {

    @Id @GeneratedValue
    @Column(name = "syllabus_id")
    private Long id;

    private String bookName;
    private String lecture_goal;
}
