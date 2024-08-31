package dev.courseRegistration_client.Repository;

import dev.courseRegistration_client.model.Course;
import dev.courseRegistration_client.util.EntityManagerUtil;

import javax.persistence.*;
import java.util.List;

public class CourseRepository {
    EntityManager manager = EntityManagerUtil.getEntityManager();
    public List<Course> findAll(){
        TypedQuery<Course> query= manager.createQuery("select c from Course c", Course.class);
        List<Course> courses = query.getResultList();
        System.out.println(courses.get(0));

        return query.getResultList();
    }
    public List<Course> findByNameOrProfessor(String input){


        TypedQuery<Course> query= manager.createQuery(
                "select c from Course c where c.professorName like :input or c.name like :input", Course.class);
        return query.setParameter("input", "%"+input+"%").getResultList();
    }
    public Course findById(int id){
        return manager.find(Course.class, id);
    }
    public void incrementCourseCurrentCountById(int id){
        Course course= manager.find(Course.class, id);
        course.setCurrentCount(course.getCurrentCount()+1);
    }
    public void decrementCourseCurrentCountById(int id){
        Course course= manager.find(Course.class, id);
        course.setCurrentCount(course.getCurrentCount()-1);
    }

}
