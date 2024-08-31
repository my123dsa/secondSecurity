package dev.courseRegistration_client.Repository;

import dev.courseRegistration_client.model.Course;
import dev.courseRegistration_client.model.Enrollment;
import dev.courseRegistration_client.util.EntityManagerUtil;
import dto.EnrollmentDetailDTO;
import dto.EnrollmentJoinCourseDTO;

import javax.persistence.*;
import java.util.List;

public class EnrollmentRepository {
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo");
//    EntityManager manager = factory.createEntityManager();
//    EntityTransaction transaction = manager.getTransaction();
    EntityManager manager = EntityManagerUtil.getEntityManager();
    public List<EnrollmentJoinCourseDTO> findHackJumById(int id){

        TypedQuery<EnrollmentJoinCourseDTO> query= manager
                .createQuery("SELECT  new dto.EnrollmentJoinCourseDTO(" +
                        "e.member.id ," +
                        "c.id, " +
                        "e.id, " +
                        "e.check, " +
                        "c.credit, " +
                        "c.currentCount, " +
                        "c.capacity) " +
                        " FROM Enrollment e "
                + "INNER JOIN Course c ON c.id = e.course.id WHERE e.member.id = :id", EnrollmentJoinCourseDTO.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    public void save(Enrollment enrollment){
        manager.persist(enrollment);
    }
    public boolean findCheckByMemberIdAndCourseId(int memberId,int courseId){
        TypedQuery<Enrollment> query= manager.createQuery("select e from Enrollment e where e.member.id = :memberId AND e.course.id = :courseId", Enrollment.class);
        query.setParameter("memberId", memberId);
        query.setParameter("courseId", courseId);
        return query.getSingleResult().getCheck();
    }
    public void updateCheckToFalse(int memberId,int courseId){
        manager.createQuery(
                        "UPDATE Enrollment e SET e.check = false WHERE e.member.id = :memberId AND e.course.id = :courseId")
                .setParameter("memberId", memberId)
                .setParameter("courseId", courseId)
                .executeUpdate();
    }
    public void updateCheckToTrue(int memberId,int courseId){
        manager.createQuery(
                        "UPDATE Enrollment e SET e.check = true WHERE e.member.id = :memberId AND e.course.id = :courseId")
                .setParameter("memberId", memberId)
                .setParameter("courseId", courseId)
                .executeUpdate();
    }
    public List<EnrollmentDetailDTO> findEnrollmentDetailsByMemberId(int memberId){
        TypedQuery<EnrollmentDetailDTO> query= manager.createQuery(
                "SELECT new dto.EnrollmentDetailDTO(c.name, c.professorName, c.credit) " +
            "FROM Enrollment e " +
            "INNER JOIN Course c ON e.course.id = c.id " +
            "WHERE e.member.id = :memberId AND e.check = true", EnrollmentDetailDTO.class);
        query.setParameter("memberId", memberId);

        return query.getResultList();
    }
    public int findTotalCreditsByMemberId(int memberId){
        TypedQuery<Integer> query= manager.createQuery("SELECT SUM(c.credit) AS totalCredits " +
                "FROM Enrollment e " +
                "INNER JOIN Course c ON e.course.id = c.id " +
                "WHERE e.member.id = :memberId AND e.check = true", Integer.class );
        query.setParameter("memberId", memberId);
        return query.getSingleResult();
    }
}
