package foodorder.view;

import java.util.ArrayList;

public class EndView {

	// 에러 출력
	public static void showError(String message) {
		System.out.println(message);
	}

	// 메세지 출력
	public static void showMessage(boolean result, Object object, String function) {
		if (result == true) {
			System.out.println("요청하신 " + object + " " + function + " 완료");
		} else {
			System.out.println("요청하신 " + object + " " + function + " 실패, 정보를 재확인하세요.");
		}
	}
	
	// 다중 출력
	public static void printAll(ArrayList all) {
		
		if (all != null) {
			int count = all.size();
			
			if(count != 0) {
				for (int i = 0; i < count; i++) {
					System.out.println(all.get(i));
				}
			} else {
				System.out.println("요청하신 정보가 없습니다.");
			}
		}else {
			System.out.println("요청하신 정보가 없습니다.");
		}
	}
	
	// 단일 출력
	public static void printOne(Object object) {
		if (object != null) {
			System.out.println(object);
		} else {
			System.out.println("요청하신 정보가 없습니다.");
		}
	}

}
