package dev.courseRegistration_client.service;

import dev.courseRegistration_client.Repository.CourseRepository;
import dev.courseRegistration_client.model.Course;

import java.util.List;

//@RequiredArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService() {
        courseRepository = new CourseRepository();
    }

    public  List<Course> displayAllCourses() {
//        List<Course> courses = courseDAO.getAllCourses();
        return courseRepository.findAll();
    }

    public List<Course> searchCourse(String input) {
//        List<Course> courses = courseDAO.getCoursesByNameOrProfessor(input);
        return courseRepository.findByNameOrProfessor(input);

    }
}



//
//**수강 신청**
//- **입력 형태(값):**
//  - 강좌 ID: Integer
//- **출력 형태(값):**
//  - 성공한 경우
//      - "수강 신청이 완료되었습니다."
//  - 실패한 경우
//      - "이미 신청한 강좌입니다."
//      - "수강 정원이 초과되었습니다."
//      - "최대 학점을 초과하였습니다"