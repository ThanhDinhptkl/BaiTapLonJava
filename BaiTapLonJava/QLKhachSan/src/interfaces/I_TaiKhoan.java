package interfaces;

import java.util.ArrayList;

import entity.TaiKhoan;

public interface I_TaiKhoan {
	public ArrayList<TaiKhoan> getAllTaiKhoan();
	
	public TaiKhoan getTheoMaTK(String maTK);
	
	public ArrayList<TaiKhoan> getTheoMaNV(String maNV);
	
	public boolean them(TaiKhoan tk);
	
	public boolean capNhat(TaiKhoan tk);
	
	public boolean xoa(String maTK);
	
}
