package entity;

import java.util.Objects;

public class ChiTietSuDungDichVu {
	private HoaDon hoaDon;
	private DichVu dichVu;
	private int soLuong;

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dichVu, hoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietSuDungDichVu other = (ChiTietSuDungDichVu) obj;
		return Objects.equals(dichVu, other.dichVu) && Objects.equals(hoaDon, other.hoaDon);
	}

	public ChiTietSuDungDichVu(HoaDon hoaDon, DichVu dichVu, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
	}

	public ChiTietSuDungDichVu(HoaDon hoaDon, DichVu dichVu) {
		super();
		this.hoaDon = hoaDon;
		this.dichVu = dichVu;
	}

	public ChiTietSuDungDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChiTietSuDungDichVu [hoaDon=" + hoaDon + ", dichVu=" + dichVu + ", soLuong=" + soLuong + "]";
	}

}
