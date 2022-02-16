package jpa.enrolment.domain;

import jpa.enrolment.domain.person.Student;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

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
