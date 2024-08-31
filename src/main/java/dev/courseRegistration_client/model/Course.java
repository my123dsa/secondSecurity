package dev.courseRegistration_client.model;

import java.util.List;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int id;

	@Column(name = "course_name")
	private String name;
	@Column(name = "PROFESSOR_NAME")
	private String professorName;

	private int credit;

	private int capacity;
	@Column(name = "currentcount")
	private int currentCount;
}
