package foodorder.model.dto;

public class OrdersDTO {
	
	private int order_no;
	private String id;
	private String storeName;
	private String menuName;
	private String payMethod;
	
	public OrdersDTO() {}

	public OrdersDTO(int order_no, String id, String storeName, String menuName, String payMethod) {
		this.order_no = order_no;
		this.id = id;
		this.storeName = storeName;
		this.menuName = menuName;
		this.payMethod = payMethod;
	}
	
	public OrdersDTO(String id, String storeName, String menuName, String payMethod) {
		this.id = id;
		this.storeName = storeName;
		this.menuName = menuName;
		this.payMethod = payMethod;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[주문 번호] ");
		builder.append(order_no);
		builder.append("\t[회원ID] ");
		builder.append(id);
		builder.append("\t[가게 이름] ");
		builder.append(storeName);
		builder.append("\t[메뉴 이름] ");
		builder.append(menuName);
		return builder.toString();
	}

}
