package dev.courseRegistration_client.view;

import java.util.Scanner;

public class InputView {
	private static  Scanner sc = new Scanner(System.in);

	public static String[] login() {
		
        // 예시 이메일 및 비밀번호
        System.out.println("아이디를 입력해주세요: ");
        String email = sc.nextLine();
        System.out.println("비밀번호를 입력해주세요: ");
        String password = sc.nextLine();
        
        return new String[] {email,password};
	}
	public static Integer selectCourseInput() {
	
		System.out.println(" 수강 강좌 id를 입력하세요: 종료하고싶으시다면 0 을 입력하세요");
		String s = sc.nextLine();
		
		try {
			
			return  Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력하세요");
		}
		
		
		return null;
	}
	public static boolean ifDeleteInput() {
		System.out.println("수강 신청을 취소하시겠습니까? yes ");
		if( sc.nextLine().equalsIgnoreCase("yes")) {
			return true;
		}
		return false;
		
	}

}


//- **입력 형태(값):**
//- 강좌 ID: Integer