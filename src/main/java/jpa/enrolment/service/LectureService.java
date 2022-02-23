package jpa.enrolment.service;

import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public void saveLecture(Lecture lecture) {
       lectureRepository.findByCode(lecture.getCode()).ifPresent(e -> {
            throw new RuntimeException("교과목 코드 중복");
        });

       if(lecture.getLevel() <= 0){
           throw new IllegalStateException("올바르지 않은 학년");
       }

       if(lecture.getCredit() <= 0){
           throw new IllegalStateException("올바르지 않은 학점");
       }

        lectureRepository.save(lecture);
    }
}
