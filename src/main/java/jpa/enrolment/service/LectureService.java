package jpa.enrolment.service;

import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;


    public void saveLecture(String code, String name, int level, int credit) {
       lectureRepository.findByCode(code).ifPresent(e -> {
            throw new RuntimeException("교과목 코드 중복");
        });

       if(level <= 0){
           throw new IllegalStateException("올바르지 않은 학년");
       }

       if(credit <= 0){
           throw new IllegalStateException("올바르지 않은 학점");
       }

        Lecture lecture = Lecture.builder()
                .code(code)
                .name(name)
                .level(level)
                .credit(credit)
                .build();

        lectureRepository.save(lecture);
    }
}
