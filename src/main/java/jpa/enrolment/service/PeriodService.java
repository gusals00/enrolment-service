package jpa.enrolment.service;

import jpa.enrolment.domain.Period;
import jpa.enrolment.domain.PeriodName;
import jpa.enrolment.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeriodService {

    private final PeriodRepository periodRepository;

    @Transactional
    public Long savePeriod(PeriodName periodName, LocalDateTime openTime, LocalDateTime closeTime) {
        checkRightTime(openTime,closeTime);// openTime<closeTime 확인
        checkDuplicatePeriod(periodName); //동일 기간 이름으로 이미 등록되어 있는지 확인

        Period period = Period.builder()
                .periodName(periodName)
                .openTime(openTime)
                .closeTime(closeTime)
                .build();

        periodRepository.savePeriod(period);
        return period.getId();
    }

    @Transactional
    public Long updatePeriod(PeriodName periodName, LocalDateTime openTime, LocalDateTime closeTime){
        checkRightTime(openTime,closeTime);// openTime<closeTime 확인
        Period findPeriod = periodRepository.findByPeriodName(periodName).stream().findAny().orElseThrow(() -> new IllegalStateException(periodName.name() + "이 존재하지 않습니다.")); // 기간 이름이 등록되어 있지 않을 경우 예외

        findPeriod.changePeriod(openTime,closeTime);
        return findPeriod.getId();
    }

    private void checkRightTime(LocalDateTime openTime, LocalDateTime closeTime) {
        if(!openTime.isBefore(closeTime))
            throw new IllegalStateException("시작시간이 종료시간과 같거나 초과할 수 없습니다.");
    }

    private void checkDuplicatePeriod(PeriodName periodName){
        List<Period> findPeriod = periodRepository.findByPeriodName(periodName);
        if (!findPeriod.isEmpty()){
            throw new IllegalStateException("이미"+periodName.name()+" 기간이 등록되어 있습니다.");
        }
    }
}
