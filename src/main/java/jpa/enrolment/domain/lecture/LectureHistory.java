package jpa.enrolment.domain.lecture;

import jpa.enrolment.domain.Syllabus;
import jpa.enrolment.domain.person.Student;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class LectureHistory {

    @Id @GeneratedValue
    @Column(name = "lecture_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "open_lecture_id")
    private OpenLecture openLecture;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;
}
