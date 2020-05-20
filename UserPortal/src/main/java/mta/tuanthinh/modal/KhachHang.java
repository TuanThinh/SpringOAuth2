package mta.tuanthinh.modal;

public class KhachHang {
	private String id;

	private String firstName;

	private String lastName;

	private String diaChi;

	private String dienThoai;

	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public KhachHang(String id, String firstName, String lastName, String diaChi, String dienThoai, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
	}
	
	public KhachHang(String firstName, String lastName, String diaChi, String dienThoai, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
	}

	public KhachHang() {
		
	}
}
