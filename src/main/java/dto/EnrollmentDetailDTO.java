package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnrollmentDetailDTO {
    private String courseName;
    private String professorName;
    private int credit;
}
