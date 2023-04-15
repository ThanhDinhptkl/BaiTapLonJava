package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTietDatPhong {
	private HoaDon hoaDon;
	private Phong phong;
	private LocalDate ngayDat;
	private LocalDate ngayTra;
	private int soNguoi;
	private String ghiChu;

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public LocalDate getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDate ngayDat) {
		this.ngayDat = ngayDat;
	}

	public LocalDate getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(LocalDate ngayTra) {
		this.ngayTra = ngayTra;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hoaDon, phong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDatPhong other = (ChiTietDatPhong) obj;
		return Objects.equals(hoaDon, other.hoaDon) && Objects.equals(phong, other.phong);
	}

	public ChiTietDatPhong(HoaDon hoaDon, Phong phong, LocalDate ngayDat, LocalDate ngayTra, int soNguoi,
			String ghiChu) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.ngayDat = ngayDat;
		this.ngayTra = ngayTra;
		this.soNguoi = soNguoi;
		this.ghiChu = ghiChu;
	}

	public ChiTietDatPhong(HoaDon hoaDon, Phong phong) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
	}

	public ChiTietDatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChiTietDatPhong [hoaDon=" + hoaDon + ", phong=" + phong + ", ngayDat=" + ngayDat + ", ngayTra="
				+ ngayTra + ", soNguoi=" + soNguoi + ", ghiChu=" + ghiChu + "]";
	}

}
