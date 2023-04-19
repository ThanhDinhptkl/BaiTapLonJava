package interfaces;

import java.util.ArrayList;

import entity.ChiTietDatPhong;

public interface I_ChiTietDatPhong {
	public ArrayList<ChiTietDatPhong> getAllCTDatPhong();
	
	public ArrayList<ChiTietDatPhong> getCTDatPhongTheoMaHD(String idHD);
	
	public ArrayList<ChiTietDatPhong> getCTDatPhongTheoMaPhong(String idPhong);
	
	public boolean them(ChiTietDatPhong dp);

	public boolean capNhat(ChiTietDatPhong dp);

	public boolean xoa(String idHD, String idMP);
}
