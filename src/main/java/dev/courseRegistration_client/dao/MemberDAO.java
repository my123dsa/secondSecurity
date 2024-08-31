//package dev.courseRegistration_client.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import dev.courseRegistration_client.model.Member;
//import dev.courseRegistration_client.util.DatabaseConnection;
//
//public class MemberDAO {
//    private Connection connection;
//
//    public MemberDAO() {
//        try {
//			this.connection = DatabaseConnection.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//
//    public Member getMemberByEmailAndPassword(String email, String password) {
//        String sql = "SELECT * FROM Member WHERE Email = ? AND PASSWORD = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, email);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//            	return Member.builder()
//                        .member_id(rs.getInt("MEMBER_ID"))
//                        .email(rs.getString("Email"))
//                        .name(rs.getString("MEMBER_NAME"))
//                        .password(rs.getString("PASSWORD"))
//                        .isAdmin(rs.getBoolean("ADMIN"))
//                        .hackJum(rs.getInt("HackJum"))
//                        .build();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public Member findByEmail(String email) {
//        String sql = "SELECT * FROM Member WHERE Email = ? ";
//        try (
//        		PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//            	return Member.builder()
//                        .member_id(rs.getInt("MEMBER_ID"))
//                        .email(rs.getString("Email"))
//                        .name(rs.getString("MEMBER_NAME"))
//                        .password(rs.getString("PASSWORD"))
//                        .isAdmin(rs.getBoolean("ADMIN"))
//                        .hackJum(rs.getInt("HackJum"))
//                        .build();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
