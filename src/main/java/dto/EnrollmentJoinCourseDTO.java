package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnrollmentJoinCourseDTO {

    int member_member_id; 
    int course_course_id; 
    int enrollment_id; 
    boolean course_check; 
    int credit; 
    int currentcount; 
    int capacity;
}