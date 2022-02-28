package jpa.enrolment.service;

import jpa.enrolment.domain.Period;
import jpa.enrolment.domain.PeriodName;
import jpa.enrolment.repository.PeriodRepository;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PeriodServiceTest {

    @Autowired PeriodRepository periodRepository;
    @Autowired PeriodService periodService;

    @Test
    void 기간저장성공(){
        PeriodName periodName=PeriodName.SYLLABUS;
        LocalDateTime openTime = LocalDateTime.now().minusDays(1);
        LocalDateTime closeTime = LocalDateTime.now();

        Long savedId = periodService.savePeriod(periodName, openTime, closeTime);
        Period findPeriod = periodRepository.findById(savedId).get();

        assertThat(findPeriod.getPeriodName()).isEqualTo(periodName);
        assertThat(findPeriod.getOpenTime()).isEqualTo(openTime);
        assertThat(findPeriod.getCloseTime()).isEqualTo(closeTime);

    }

    @Test
    void 기간저장실패(){
        PeriodName periodName=PeriodName.SYLLABUS;
        LocalDateTime openTime = LocalDateTime.now().minusDays(1);
        LocalDateTime closeTime = LocalDateTime.now();
        periodService.savePeriod(periodName, openTime, closeTime);

        IllegalStateException checkRightTimeException = assertThrows(IllegalStateException.class, () -> periodService.savePeriod(periodName, openTime.plusDays(3), closeTime));
        assertThat(checkRightTimeException.getMessage()).isEqualTo("시작시간이 종료시간과 같거나 초과할 수 없습니다.");

        IllegalStateException checkDuplicatePeriodException = assertThrows(IllegalStateException.class, () -> periodService.savePeriod(periodName, openTime, closeTime));
        assertThat(checkDuplicatePeriodException.getMessage()).isEqualTo("이미"+periodName.name()+" 기간이 등록되어 있습니다.");
        System.out.println("이미"+periodName.name()+" 기간이 등록되어 있습니다.");

    }

    @Test
    void 기간update성공(){
        PeriodName periodName=PeriodName.JUNIOR;
        LocalDateTime openTime = LocalDateTime.now().minusDays(1);
        LocalDateTime closeTime = LocalDateTime.now();
        LocalDateTime changeOpenTime = LocalDateTime.now().minusDays(3);
        LocalDateTime changeCloseTime = LocalDateTime.now();
        periodService.savePeriod(periodName, openTime, closeTime);

        Long updateId = periodService.updatePeriod(periodName, changeOpenTime, changeCloseTime);
        Period findPeriod = periodRepository.findById(updateId).get();

        Assertions.assertThat(findPeriod.getOpenTime()).isEqualTo(changeOpenTime);
        Assertions.assertThat(findPeriod.getCloseTime()).isEqualTo(changeCloseTime);

    }

    @Test
    void 기간update실패(){
        PeriodName periodName=PeriodName.JUNIOR;
        LocalDateTime openTime = LocalDateTime.now().minusDays(1);
        LocalDateTime closeTime = LocalDateTime.now();
        periodService.savePeriod(periodName, openTime, closeTime);

        IllegalStateException checkRightTimeException = assertThrows(IllegalStateException.class, () -> periodService.updatePeriod(periodName, openTime.plusDays(1), closeTime));
        Assertions.assertThat(checkRightTimeException.getMessage()).isEqualTo("시작시간이 종료시간과 같거나 초과할 수 없습니다.");

        IllegalStateException existPeriodException = assertThrows(IllegalStateException.class, () -> periodService.updatePeriod(PeriodName.SENIOR, openTime, closeTime));
        Assertions.assertThat(existPeriodException.getMessage()).isEqualTo(PeriodName.SENIOR + "이 존재하지 않습니다.");

    }
}
