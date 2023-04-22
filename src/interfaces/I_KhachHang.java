package interfaces;

import java.util.ArrayList;

import entity.KhachHang;

public interface I_KhachHang {
	public ArrayList<KhachHang> getAllKhachHang();

	public KhachHang getKhachHangTheoMaKH(String id);

	public ArrayList<KhachHang> getKhachHangTheoSDT(String sdt);
	
	public KhachHang getKhachHangTheoCCCD(String cccd);

	public boolean them(KhachHang kh);

	public boolean capNhat(KhachHang kh);

	public boolean xoa(String idKH);
}
