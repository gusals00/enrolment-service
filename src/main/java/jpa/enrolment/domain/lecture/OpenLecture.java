package jpa.enrolment.domain.lecture;

import jpa.enrolment.domain.Syllabus;
import jpa.enrolment.domain.lecture.Lecture;
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

    public void changeProfessor(Professor professor){
        this.professor = professor;
    }

    public void changeLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public void changeSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public void changeNumber(int seperatedNumber, int maxStudentNumber, int curStudentNumber) {
        this.seperatedNumber = seperatedNumber;
        this.maxStudentNumber = maxStudentNumber;
        this.curStudentNumber = curStudentNumber;
    }
}