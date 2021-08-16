package foodorder.model.dto;

public class MenuDTO {
	
	private String menuName;
	private int menuPrice;
	private String storeName;
	
	public MenuDTO() {}

	public MenuDTO(String menuName, int menuPrice, String storeName) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.storeName = storeName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[�޴� �̸�] ");
		builder.append(menuName);
		builder.append("\t[����] ");
		builder.append(menuPrice);
		builder.append("\t[���� �̸�] ");
		builder.append(storeName);
		return builder.toString();
	}
	
	

}
