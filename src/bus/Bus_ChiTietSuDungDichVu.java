package bus;

import java.util.ArrayList;

import dao.Dao_ChiTietSuDungDichVu;
import entity.ChiTietSuDungDichVu;
import interfaces.I_ChiTietSuDungDichVu;

public class Bus_ChiTietSuDungDichVu implements I_ChiTietSuDungDichVu{
	private Dao_ChiTietSuDungDichVu dao_ct = new Dao_ChiTietSuDungDichVu();
	@Override
	public ArrayList<ChiTietSuDungDichVu> getAllChiTietDichVu() {
		return dao_ct.getAllChiTietDichVu();
	}

	@Override
	public ArrayList<ChiTietSuDungDichVu> getCTDichVuTheoMaHD(String idHD) {
		return dao_ct.getCTDichVuTheoMaHD(idHD);
	}

	@Override
	public ArrayList<ChiTietSuDungDichVu> getCTDichVuTheoMaDV(String idDV) {
		return dao_ct.getCTDichVuTheoMaDV(idDV);
	}

	@Override
	public boolean them(ChiTietSuDungDichVu dv) {
		return dao_ct.them(dv);
	}

	@Override
	public boolean capNhat(ChiTietSuDungDichVu dv) {
		return dao_ct.capNhat(dv);
	}

	@Override
	public boolean xoa(String idHD, String idDV) {
		return dao_ct.xoa(idHD, idDV);
	}

}
