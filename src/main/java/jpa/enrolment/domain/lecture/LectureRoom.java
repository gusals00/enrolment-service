package jpa.enrolment.domain.lecture;

import jpa.enrolment.domain.BuildingName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRoom {

    @Id @GeneratedValue
    @Column(name = "lecture_room_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BuildingName buildingName;

    private int roomNumber;

    protected LectureRoom(BuildingName buildingName, int roomNumber) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
    }

    public static LectureRoom createLectureRoom(BuildingName buildingName, int roomNumber){
        return new LectureRoom(buildingName,roomNumber);
    }
}
