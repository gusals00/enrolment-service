package jpa.enrolment.domain.lecture;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class LectureTime {

    @Id @GeneratedValue
    @Column(name = "lecture_time_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private LectureDay lectureDay;

    private int period;
}
