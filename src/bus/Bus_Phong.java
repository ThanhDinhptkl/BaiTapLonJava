package bus;

import java.util.ArrayList;

import dao.Dao_Phong;
import entity.Phong;
import interfaces.I_Phong;

public class Bus_Phong implements I_Phong{
	private Dao_Phong dao_phong = new Dao_Phong();
	@Override
	public ArrayList<Phong> getAllPhong() {
		return dao_phong.getAllPhong();
	}

	@Override
	public Phong getPhongTheoMaPhong(String id) {
		return dao_phong.getPhongTheoMaPhong(id);
	}

	@Override
	public ArrayList<Phong> getPhongTheoLoaiPhong(String ten) {
		return dao_phong.getPhongTheoLoaiPhong(ten);
	}

	@Override
	public ArrayList<Phong> getPhongTheoTrangThai(Boolean tt) {
		return dao_phong.getPhongTheoTrangThai(tt);
	}

	@Override
	public ArrayList<Phong> getPhongTheoMaTang(int idTang) {
		return dao_phong.getPhongTheoMaTang(idTang);
	}

	@Override
	public boolean them(Phong phong) {
		return dao_phong.them(phong);
	}

	@Override
	public boolean capNhat(Phong phong) {
		return dao_phong.capNhat(phong);
	}

	@Override
	public boolean xoa(String idPhong) {
		return dao_phong.xoa(idPhong);
	}

	@Override
	public boolean capNhatTrangThai(boolean tt, String maP) {
		return dao_phong.capNhatTrangThai(tt, maP);
	}

}
