package foodorder.model.dto;

public class StoreDTO {
	
	private String storeName;
	private String storeAddress;
	private String storePhone;
	
	public StoreDTO() {}

	public StoreDTO(String storeName, String storeAddress, String storePhone) {
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[가게 이름] ");
		builder.append(storeName);
		builder.append("\t[주소] ");
		builder.append(storeAddress);
		builder.append("\t[전화번호] ");
		builder.append(storePhone);
		return builder.toString();
	}

}
