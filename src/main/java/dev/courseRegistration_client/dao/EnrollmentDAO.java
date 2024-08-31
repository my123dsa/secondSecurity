//package dev.courseRegistration_client.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import dev.courseRegistration_client.model.Course;
//import dev.courseRegistration_client.model.Enrollment;
//import dev.courseRegistration_client.model.Member;
//import dev.courseRegistration_client.util.DatabaseConnection;
//import dto.EnrollmentDetailDTO;
//import dto.EnrollmentJoinCourseDTO;
//
//public class EnrollmentDAO {
//	private Connection connection;
//    public EnrollmentDAO() {
//        try {
//			this.connection = DatabaseConnection.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//    public List<EnrollmentJoinCourseDTO> getCheckHackJum(int memberId) {
//    	String sql = "SELECT * FROM Enrollment e "
//                + "INNER JOIN course c ON e.Course_COURSE_ID = c.course_id "
//                + "WHERE e.member_member_id = ?";
//    	try {
//    		PreparedStatement pstm= connection.prepareStatement(sql);
//    		pstm.setInt(1, memberId);
//    		ResultSet resultSet= pstm.executeQuery();
//    		List<EnrollmentJoinCourseDTO> list= new ArrayList();
//    		while(resultSet.next()) {
//    			list.add(EnrollmentJoinCourseDTO.builder()
//    			.member_member_id(resultSet.getInt("member_member_id"))
//    			.course_course_id(resultSet.getInt("course_course_id"))
//    			.capacity(resultSet.getInt("capacity"))
//    			.currentcount(resultSet.getInt("currentcount"))
//    			.credit(resultSet.getInt("credit"))
//    			.course_check(resultSet.getBoolean("course_check"))
//    			.build());
//    		}
//    		return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void save(Enrollment enrollment) {
//    	String sql = "INSERT INTO enrollment (Member_MEMBER_ID, Course_COURSE_ID,  COURSE_CHECK) VALUES (?, ?, ?)";
//
//        try {
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setInt(1, enrollment.getMember_member_id().getMember_id());
//            pstm.setInt(2, enrollment.getCourse_course_id().getCourse_id());
//            pstm.setBoolean(3, enrollment.getCourse_check().booleanValue());
//            pstm.executeUpdate();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//    }
//    public boolean getChangeCheck(int memberId,int courseId) {
//    	String sql= "select * from Enrollment where member_member_id= ? and course_course_id= ?";
//    	try {
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setInt(1,memberId);
//            pstm.setInt(2,courseId);
//
//            ResultSet resultSet= pstm.executeQuery();
//            if(resultSet.next()) {
//            	return true;
//            }
//		}
//    	catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    	return false;
//    }
//    public void updateFalse(int memberId,int courseId) {
//    	String sql= "update Enrollment set course_check= false "
//    			+ "where member_member_id= ? and course_course_id= ?";
//    	try {
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setInt(1, memberId); // 첫 번째 매개변수
//            pstm.setInt(2, courseId); // 두 번째 매개변수
//            pstm.executeUpdate();
//
//		}
//    	catch(Exception e) {
//    		e.printStackTrace();
//    	}
//
//    }
//    public void updateTrue(int memberId,int courseId) {
//    	String sql= "update Enrollment set course_check= true "
//    			+ "where member_member_id= ? and course_course_id= ?";
//    	try {
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            pstm.setInt(1, memberId); // 첫 번째 매개변수
//            pstm.setInt(2, courseId); // 두 번째 매개변수
//            pstm.executeUpdate();
//		}
//    	catch(Exception e) {
//    		e.printStackTrace();
//    	}
//
//    }
//
//	public List<EnrollmentDetailDTO> getEnrollmentDetails(int memberId) {
//		String sql = "SELECT c.COURSE_NAME, c.PROFESSOR_NAME, c.CREDIT " +
//				"FROM Enrollment e " +
//				"INNER JOIN Course c ON e.Course_COURSE_ID = c.COURSE_ID " +
//				"WHERE e.Member_MEMBER_ID = ? AND e.COURSE_CHECK = true";
//		List<EnrollmentDetailDTO> details = new ArrayList<>();
//
//		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
//			pstm.setInt(1, memberId);
//			ResultSet rs = pstm.executeQuery();
//
//			while (rs.next()) {
//				EnrollmentDetailDTO dto = EnrollmentDetailDTO.builder()
//						.courseName(rs.getString("COURSE_NAME"))
//						.professorName(rs.getString("PROFESSOR_NAME"))
//						.credit(rs.getInt("CREDIT"))
//						.build();
//
//				details.add(dto);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return details;
//	}
//
//	public int getTotalCredits(int memberId) {
//		String sql = "SELECT SUM(c.CREDIT) AS totalCredits " +
//				"FROM Enrollment e " +
//				"INNER JOIN Course c ON e.Course_COURSE_ID = c.COURSE_ID " +
//				"WHERE e.Member_MEMBER_ID = ? AND e.COURSE_CHECK = true";
//		int totalCredits = 0;
//
//		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
//			pstm.setInt(1, memberId);
//			ResultSet rs = pstm.executeQuery();
//
//			if (rs.next()) {
//				totalCredits = rs.getInt("totalCredits");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return totalCredits;
//	}
//}
//
//
