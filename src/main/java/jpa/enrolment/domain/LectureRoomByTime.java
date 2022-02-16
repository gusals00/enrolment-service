package jpa.enrolment.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class LectureRoomByTime {

    @Id @GeneratedValue
    @Column(name = "using_lecture_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_room_id")
    LectureRoom lectureRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_time_id")
    LectureTime lectureTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "open_lecture_id")
    OpenLecture openLecture;


}
