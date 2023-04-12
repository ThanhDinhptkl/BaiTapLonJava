package interfaces;

import java.util.ArrayList;

import entity.NhanVien;

public interface I_NhanVien {
public ArrayList<NhanVien> getAllNhanVien();
	
	public NhanVien getTheoMaNV(String maNV);
	
	public ArrayList<NhanVien> getTheoHoTen(String hoTen);
	
	public ArrayList<NhanVien> getTheoSDT(String sdt);
	
	public ArrayList<NhanVien> getTheoPhai(Boolean phai);
	
	public ArrayList<NhanVien> getTheoTuoi(int tuoi);
	
	public ArrayList<NhanVien> getTheoLuong(float luong);
	
	public boolean them(NhanVien nv);
	
	public boolean capNhat(NhanVien nv);
	
	public boolean xoa(String maNV);
}
