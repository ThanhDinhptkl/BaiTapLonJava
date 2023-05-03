package interfaces;

import java.util.ArrayList;

import entity.Phong;

public interface I_Phong {
	public ArrayList<Phong> getAllPhong();

	public Phong getPhongTheoMaPhong(String id);

	public ArrayList<Phong> getPhongTheoLoaiPhong(String ten);
	
	public ArrayList<Phong> getPhongTheoTrangThai(Boolean tt);
	
	public ArrayList<Phong> getPhongTheoMaTang(int idTang);

	public boolean them(Phong phong);

	public boolean capNhat(Phong phong);
	
	public boolean capNhatTrangThai(boolean tt, String maP);

	public boolean xoa(String idPhong);
}
