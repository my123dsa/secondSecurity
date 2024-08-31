package dev.courseRegistration_client.Repository;

import dev.courseRegistration_client.model.Course;
import dev.courseRegistration_client.model.Member;
import dev.courseRegistration_client.util.EntityManagerUtil;

import javax.persistence.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberRepository {
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo");
//    EntityManager manager = factory.createEntityManager();
//    EntityTransaction transaction = manager.getTransaction();
    EntityManager manager = EntityManagerUtil.getEntityManager();
    public Member findByEmailAndPassword(String email, String password){
        TypedQuery<Member> query= manager.createQuery("select m from Member m where m.email =:email and m.password = :password", Member.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        return query.getSingleResult();
        //아니면 null
    }
    public Member findByEmail(String email){
        TypedQuery<Member> query= manager.createQuery("select m from Member m where m.email =:email", Member.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

}

