package jpa.enrolment.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class LectureRoom {
    @Id @GeneratedValue
    @Column(name = "lecture_room_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BuildingName buildingName;

    private int roomNumber;
}
