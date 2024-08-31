//package dev.courseRegistration_client.util;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class DatabaseConnection {
//    private static Connection connection = null;
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3307/Woori_Course_Registration";
//	private static final String USER = "user";
//	private static final String PASSWORD = "1234";
//	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//		Class.forName(DRIVER_NAME);
//		Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//
//		return connection;
//	}
//}