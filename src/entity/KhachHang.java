package entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String hoTen;
	private String sDT;
	private String cccd;
	private String email;
	private boolean phai;
	private String diaChi;

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}

	public KhachHang(String maKH, String hoTen, String sDT, String cccd, String email, boolean phai, String diaChi) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sDT = sDT;
		this.cccd = cccd;
		this.email = email;
		this.phai = phai;
		this.diaChi = diaChi;
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", sDT=" + sDT + ", cccd=" + cccd + ", email=" + email
				+ ", phai=" + phai + ", diaChi=" + diaChi + "]";
	}

}
