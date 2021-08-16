package foodorder.view;

import java.util.ArrayList;

public class EndView {

	// ���� ���
	public static void showError(String message) {
		System.out.println(message);
	}

	// �޼��� ���
	public static void showMessage(boolean result, Object object, String function) {
		if (result == true) {
			System.out.println("��û�Ͻ� " + object + " " + function + " �Ϸ�");
		} else {
			System.out.println("��û�Ͻ� " + object + " " + function + " ����, ������ ��Ȯ���ϼ���.");
		}
	}
	
	// ���� ���
	public static void printAll(ArrayList all) {
		
		if (all != null) {
			int count = all.size();
			
			if(count != 0) {
				for (int i = 0; i < count; i++) {
					System.out.println(all.get(i));
				}
			} else {
				System.out.println("��û�Ͻ� ������ �����ϴ�.");
			}
		}else {
			System.out.println("��û�Ͻ� ������ �����ϴ�.");
		}
	}
	
	// ���� ���
	public static void printOne(Object object) {
		if (object != null) {
			System.out.println(object);
		} else {
			System.out.println("��û�Ͻ� ������ �����ϴ�.");
		}
	}

}
