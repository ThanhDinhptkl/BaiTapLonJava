package bus;

import java.util.ArrayList;

import dao.Dao_ThietBi;
import entity.ThietBi;
import interfaces.I_ThietBi;

public class Bus_ThietBi implements I_ThietBi{
	private Dao_ThietBi dao_Thietbi = new Dao_ThietBi();

	@Override
	public ArrayList<ThietBi> getAllThietBi() {
		return dao_Thietbi.getAllThietBi();
	}

	@Override
	public ThietBi getThietBiTheoMaTB(String id) {
		return dao_Thietbi.getThietBiTheoMaTB(id);
	}

	@Override
	public boolean them(ThietBi tb) {
		return dao_Thietbi.them(tb);
	}

	@Override
	public boolean capNhat(ThietBi tb) {
		return dao_Thietbi.capNhat(tb);
	}

	@Override
	public boolean xoa(String id) {
		return dao_Thietbi.xoa(id);
	}

}
