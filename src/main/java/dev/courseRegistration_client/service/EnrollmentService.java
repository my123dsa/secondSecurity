package dev.courseRegistration_client.service;

import dev.courseRegistration_client.Repository.CourseRepository;
import dev.courseRegistration_client.Repository.EnrollmentRepository;
import dev.courseRegistration_client.model.Course;
import dev.courseRegistration_client.model.Enrollment;
import dev.courseRegistration_client.model.Member;
import dto.EnrollmentDetailDTO;
import dto.EnrollmentJoinCourseDTO;

import java.util.List;
import java.util.Optional;

public class EnrollmentService {

	private EnrollmentRepository enrollmentRepository= new EnrollmentRepository();
	private CourseRepository courseRepository= new CourseRepository();
	

    public String check(Member member,int courseId) {
   	 Course course= courseRepository.findById(courseId);
   	 
//   	 List<EnrollmentJoinCourseDTO> list=enrollmentDAO.getCheckHackJum(member.getId());
	 List<EnrollmentJoinCourseDTO> list=enrollmentRepository.findHackJumById(member.getId());
   	 int i=list.stream().mapToInt(n->n.getCredit()).sum();
   	 
   	 if(course ==null)
   		 return "없는 강좌입니다";
   	 else if(course.getCapacity()-course.getCurrentCount()<=0) {
   		 return "수강 정원이 초과되었습니다.";
   	 }
   	 else if(i+course.getCredit()>member.getHackJum()) {
   		 return "최대 학점을 초과하였습니다";
   	 }
    else {
       
        Optional<EnrollmentJoinCourseDTO> matchingEnrollment = list.stream()
            .filter(n -> n.getCourse_course_id() == courseId)
            .findFirst();

        
        if (matchingEnrollment.isPresent()) {
        	if(matchingEnrollment.get().isCourse_check()) {
        		return "이미 수강 신청 한 강좌 입니다."; 
        	}
        	else {
				courseRepository.incrementCourseCurrentCountById(courseId);
			    enrollmentRepository.updateCheckToTrue(member.getId(),courseId);
        		return "수강 신청이 완료되었습니다.";
        	}
        }
		courseRepository.incrementCourseCurrentCountById(courseId);
        Enrollment enrollment = Enrollment.builder()
            .member(member)
            .course(course)
            .check(true)
            .build();
		enrollmentRepository.save(enrollment);
        return "수강 신청이 완료되었습니다.";
    	}
    }
    
    public void delete(Member member,int courseId) {
    	if(enrollmentRepository.findCheckByMemberIdAndCourseId(member.getId(),courseId)) {
    		courseRepository.decrementCourseCurrentCountById(courseId);
			enrollmentRepository.updateCheckToFalse(member.getId(),courseId);
    		System.out.println("해당 강의 수강신청을 취소했습니다");
    	}
    	else
    		System.out.println("해당 강의는 수강신청하지 않았습니다");
    }

	// 최종 학점
	public List<EnrollmentDetailDTO> getEnrollmentDetails(Member member) {
		return enrollmentRepository.findEnrollmentDetailsByMemberId(member.getId());
	}

	public int getTotalCredits(Member member) {
		return enrollmentRepository.findTotalCreditsByMemberId(member.getId());
	}
}

