package interfaces;

import java.util.ArrayList;

import entity.ChiTietSuDungDichVu;

public interface I_ChiTietSuDungDichVu {
	public ArrayList<ChiTietSuDungDichVu> getAllChiTietDichVu();
	
	public ArrayList<ChiTietSuDungDichVu> getCTDichVuTheoMaHD(String idHD);
	
	public ArrayList<ChiTietSuDungDichVu> getCTDichVuTheoMaDV(String idDV);
	
	public boolean them(ChiTietSuDungDichVu dv);

	public boolean capNhat(ChiTietSuDungDichVu dv);

	public boolean xoa(String idHD, String idDV);
}
