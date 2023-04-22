package bus;

import java.util.ArrayList;

import dao.Dao_HoaDon;
import entity.HoaDon;
import interfaces.I_HoaDon;

public class Bus_HoaDon implements I_HoaDon{
	private Dao_HoaDon dao_hd = new Dao_HoaDon();
	@Override
	public ArrayList<HoaDon> getAllHoaDon() {
		return dao_hd.getAllHoaDon();
	}

	@Override
	public HoaDon getHoaDonTheoMa(String id) {
		return dao_hd.getHoaDonTheoMa(id);
	}

	@Override
	public ArrayList<HoaDon> getHoaDonTheoMaNV(String id) {
		return dao_hd.getHoaDonTheoMaNV(id);
	}

	@Override
	public ArrayList<HoaDon> getHoaDonTheoMaKH(String id) {
		return dao_hd.getHoaDonTheoMaKH(id);
	}

	@Override
	public boolean them(HoaDon hd) {
		return dao_hd.them(hd);
	}

	@Override
	public boolean capNhat(HoaDon hd) {
		return dao_hd.capNhat(hd);
	}

	@Override
	public boolean xoa(String idHD) {
		return dao_hd.xoa(idHD);
	}

}
