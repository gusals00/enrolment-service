package jpa.enrolment.service;

import jpa.enrolment.domain.lecture.Lecture;
import jpa.enrolment.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class LectureServiceTest {

    @Autowired LectureService lectureService;
    @Autowired
    LectureRepository lectureRepository;

    @Test
    void 저장성공(){
        Lecture lecture = createLecture("CS00101", "자바", 3, 2);

        lectureService.saveLecture(lecture);
        Lecture findLecture = lectureRepository.findOne(lecture.getId()).get();

        Assertions.assertThat(lecture).isEqualTo(findLecture);


    }

    @Test
    void 저장실패(){

        Lecture lecture = createLecture("CS00101", "자바", 3, 2);
        lectureService.saveLecture(lecture);

        RuntimeException e1 = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> lectureService.saveLecture(createLecture("CS00101", "자바2", 1, 2)));
        Assertions.assertThat(e1.getMessage()).isEqualTo("교과목 코드 중복");

        IllegalStateException e2 = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> lectureService.saveLecture(createLecture("CS00102", "자바2", 0, 2)));
        Assertions.assertThat(e2.getMessage()).isEqualTo("올바르지 않은 학년");

        IllegalStateException e3 = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> lectureService.saveLecture(createLecture("CS02", "자바3", 1, -2)));
        Assertions.assertThat(e3.getMessage()).isEqualTo("올바르지 않은 학점");

    }

    private Lecture createLecture(String code,String name, int level,int credit) {
        return Lecture.builder()
                .code(code)
                .credit(credit)
                .name(name)
                .level(level)
                .build();
    }


}