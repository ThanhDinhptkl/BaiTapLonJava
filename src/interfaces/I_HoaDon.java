package interfaces;

import java.util.ArrayList;

import entity.HoaDon;

public interface I_HoaDon {
	public ArrayList<HoaDon> getAllHoaDon();

	public HoaDon getHoaDonTheoMa(String id);

	public ArrayList<HoaDon> getHoaDonTheoMaNV(String id);
	
	public ArrayList<HoaDon> getHoaDonTheoMaKH(String id);

	public boolean them(HoaDon hd);

	public boolean capNhat(HoaDon hd);

	public boolean xoa(String idHD);
}
