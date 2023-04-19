package bus;

import java.util.ArrayList;

import dao.Dao_ChiTietDatPhong;
import entity.ChiTietDatPhong;
import interfaces.I_ChiTietDatPhong;

public class Bus_ChiTietDatPhong implements I_ChiTietDatPhong{
	private Dao_ChiTietDatPhong dao_ct = new Dao_ChiTietDatPhong();

	@Override
	public ArrayList<ChiTietDatPhong> getAllCTDatPhong() {
		return dao_ct.getAllCTDatPhong();
	}

	@Override
	public ArrayList<ChiTietDatPhong> getCTDatPhongTheoMaHD(String idHD) {
		return dao_ct.getCTDatPhongTheoMaHD(idHD);
	}

	@Override
	public ArrayList<ChiTietDatPhong> getCTDatPhongTheoMaPhong(String idPhong) {
		return dao_ct.getCTDatPhongTheoMaPhong(idPhong);
	}

	@Override
	public boolean them(ChiTietDatPhong dp) {
		return dao_ct.them(dp);
	}

	@Override
	public boolean capNhat(ChiTietDatPhong dp) {
		return dao_ct.capNhat(dp);
	}

	@Override
	public boolean xoa(String idHD, String idMP) {
		return dao_ct.xoa(idHD, idMP);
	}
}
