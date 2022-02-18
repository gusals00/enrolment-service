package jpa.enrolment.domain;

import jpa.enrolment.domain.person.Person;
import jpa.enrolment.domain.person.Professor;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenLecture {

    @Id @GeneratedValue
    @Column(name = "open_lecture_id")
    private Long id;

    private int seperatedNumber;
    private int maxStudentNumber;
    private int curStudentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;

    protected OpenLecture() {
    }

    public void addProfessor(Professor professor){
        this.professor = professor;
    }
}