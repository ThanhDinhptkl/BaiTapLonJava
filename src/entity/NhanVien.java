package entity;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private String sdt;
	private boolean phai;
	private int tuoi;
	private float luong;

	public NhanVien(String maNV, String hoTen, String sdt, boolean phai, int tuoi, float luong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.phai = phai;
		this.tuoi = tuoi;
		this.luong = luong;
	}

	public NhanVien(String maNV) {
		this.maNV = maNV;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public float getLuong() {
		return luong;
	}

	public void setLuong(float luong) {
		this.luong = luong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", sdt=" + sdt + ", phai=" + phai + ", tuoi=" + tuoi
				+ ", luong=" + luong + "]";
	}

}
