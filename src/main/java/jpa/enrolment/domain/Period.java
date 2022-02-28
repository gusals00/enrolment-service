package jpa.enrolment.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Period {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PeriodName periodName;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    @Builder
    private Period(PeriodName periodName, LocalDateTime openTime, LocalDateTime closeTime) {
        this.periodName = periodName;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public void changePeriod(LocalDateTime openTime,LocalDateTime closeTime){
        this.openTime=openTime;
        this.closeTime=closeTime;
    }
}
