package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private NhanVien nv;
	private KhachHang kh;
	private LocalDate ngayLapHD;
	private ArrayList<ChiTietDatPhong> chiTietPhong;
	private ArrayList<ChiTietSuDungDichVu> chiTietDV;

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public ArrayList<ChiTietDatPhong> getChiTietPhong() {
		return chiTietPhong;
	}

	public void setChiTietPhong(ArrayList<ChiTietDatPhong> chiTietPhong) {
		this.chiTietPhong = chiTietPhong;
	}

	public ArrayList<ChiTietSuDungDichVu> getChiTietDV() {
		return chiTietDV;
	}

	public void setChiTietDV(ArrayList<ChiTietSuDungDichVu> chiTietDV) {
		this.chiTietDV = chiTietDV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}

	public HoaDon(String maHD, NhanVien nv, KhachHang kh, LocalDate ngayLapHD) {
		super();
		this.maHD = maHD;
		this.nv = nv;
		this.kh = kh;
		this.ngayLapHD = ngayLapHD;
	}

	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHD, NhanVien nv, KhachHang kh, LocalDate ngayLapHD, ArrayList<ChiTietDatPhong> chiTietPhong,
			ArrayList<ChiTietSuDungDichVu> chiTietDV) {
		super();
		this.maHD = maHD;
		this.nv = nv;
		this.kh = kh;
		this.ngayLapHD = ngayLapHD;
		this.chiTietPhong = chiTietPhong;
		this.chiTietDV = chiTietDV;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", nv=" + nv + ", kh=" + kh + ", ngayLapHD=" + ngayLapHD + ", chiTietPhong="
				+ chiTietPhong + ", chiTietDV=" + chiTietDV + "]";
	}
}
