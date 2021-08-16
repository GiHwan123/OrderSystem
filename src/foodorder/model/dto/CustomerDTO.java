package foodorder.model.dto;

public class CustomerDTO {
	
	private String id;
	private String name;
	private String phone;
	private String address;
	
	public CustomerDTO() {}
	
	public CustomerDTO(String id,String name, String phone, String address) {
		this.id=id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[회원ID] ");
		builder.append(id);
		builder.append("\t[이름] ");
		builder.append(name);
		builder.append("\t[전화번호] ");
		builder.append(phone);
		builder.append("\t[주소] ");
		builder.append(address);
		return builder.toString();
	}
	
	
	

}
