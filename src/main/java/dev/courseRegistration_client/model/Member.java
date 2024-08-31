package dev.courseRegistration_client.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
	private int id;
    private String email;
    private String name;
    private String password;
    @Column(name = "admin")
    private boolean isAdmin;
    private int hackJum;
    
}