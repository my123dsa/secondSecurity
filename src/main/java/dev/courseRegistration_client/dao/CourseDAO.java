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
//import dev.courseRegistration_client.model.Member;
//import dev.courseRegistration_client.util.DatabaseConnection;
//
//public class CourseDAO {
//	private Connection connection;
//
//    public CourseDAO() {
//        try {
//			this.connection = DatabaseConnection.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//
//	public List<Course> getAllCourses() {
//		List<Course> courses = new ArrayList<>();
//		String sql = "SELECT * FROM Course";
//		try (PreparedStatement statement = connection.prepareStatement(sql)) {
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				Course course = Course.builder()
//						.id(resultSet.getInt("COURSE_ID"))
//						.name(resultSet.getString("COURSE_NAME"))
//						.professorName(resultSet.getString("PROFESSOR_NAME"))
//						.credit(resultSet.getInt("CREDIT"))
//						.currentCount(resultSet.getInt("CURRENTCOUNT"))
//						.capacity(resultSet.getInt("CAPACITY"))
//						.build();
//				courses.add(course);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return courses;
//	}
//
//	public List<Course> getCoursesByNameOrProfessor(String input) {
//		List<Course> courses = new ArrayList<>();
//		String sql = "SELECT * FROM Course WHERE COURSE_NAME LIKE ? OR PROFESSOR_NAME LIKE ?";
//		try (PreparedStatement statement = connection.prepareStatement(sql)) {
//			String searchTerm = "%" + input + "%";
//			statement.setString(1, searchTerm);
//			statement.setString(2, searchTerm);
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				Course course = Course.builder()
//						.course_id(resultSet.getInt("COURSE_ID"))
//						.name(resultSet.getString("COURSE_NAME"))
//						.processer_name(resultSet.getString("PROFESSOR_NAME"))
//						.credit(resultSet.getInt("CREDIT"))
//						.currentcount(resultSet.getInt("CURRENTCOUNT"))
//						.capacity(resultSet.getInt("CAPACITY"))
//						.build();
//				courses.add(course);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return courses;
//	}
//
//    public Course getCheck(int id) {
//    	String sql = "SELECT * FROM course WHERE course.course_id = ?";
//    	try {
//    		PreparedStatement pstm= connection.prepareStatement(sql);
//    		pstm.setInt(1, id);
//    		ResultSet resultSet= pstm.executeQuery();
//    		if (resultSet.next()) {
//
//    			return Course.builder()
//    			.course_id(resultSet.getInt("COURSE_ID"))
//    			.capacity(resultSet.getInt("CAPACITY"))
//    			.currentcount(resultSet.getInt("CURRENTCOUNT"))
//    			.build();
//    		}
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void saveIncrease(int courseId) {
//    	 String sql = "UPDATE Course SET CURRENTCOUNT = CURRENTCOUNT + 1 WHERE COURSE_ID = ?";
//    	    try {
//    	        PreparedStatement pstm = connection.prepareStatement(sql);
//    	        pstm.setInt(1, courseId);
//    	        int rowsUpdated = pstm.executeUpdate();
//    	}catch(Exception e) {
//    		e.printStackTrace();
//    	}
//
//	}
//    public void saveDecrease(int courseId) {
//   	 String sql = "UPDATE Course SET CURRENTCOUNT = CURRENTCOUNT - 1 WHERE COURSE_ID = ?";
//   	    try {
//   	        PreparedStatement pstm = connection.prepareStatement(sql);
//   	        pstm.setInt(1, courseId);
//   	        int rowsUpdated = pstm.executeUpdate();
//   	}catch(Exception e) {
//   		e.printStackTrace();
//   	}
//
//	}
//
//}
//
//
//
////
////**수강 신청**
////- **입력 형태(값):**
////  - 강좌 ID: Integer
////- **출력 형태(값):**
////  - 성공한 경우
////      - "수강 신청이 완료되었습니다."
////  - 실패한 경우
////      - "이미 신청한 강좌입니다."
////      - "수강 정원이 초과되었습니다."
////      - "최대 학점을 초과하였습니다"