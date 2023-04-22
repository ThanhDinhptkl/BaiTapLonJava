package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private String tenPhong;
	private String loaiPhong;
	private boolean trangThai;
	private Tang tang;
	private double gia;

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public Tang getTang() {
		return tang;
	}

	public void setTang(Tang tang) {
		this.tang = tang;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	public Phong(String maPhong, String tenPhong, String loaiPhong, boolean trangThai, Tang tang, double gia) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
		this.tang = tang;
		this.gia = gia;
	}

	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", loaiPhong=" + loaiPhong + ", trangThai="
				+ trangThai + ", tang=" + tang + ", gia=" + gia + "]";
	}

}
