package bus;

import java.util.ArrayList;

import dao.Dao_DichVu;
import entity.DichVu;
import interfaces.I_DichVu;

public class Bus_DichVu implements I_DichVu{
	private Dao_DichVu dao_dv = new Dao_DichVu();
	
	@Override
	public ArrayList<DichVu> getAllDichVu() {
		return dao_dv.getAllDichVu();
	}

	@Override
	public boolean them(DichVu dv) {
		return dao_dv.them(dv);
	}

	@Override
	public boolean capNhat(DichVu dv) {
		return dao_dv.capNhat(dv);
	}

	@Override
	public boolean xoa(String id) {
		return dao_dv.xoa(id);
	}


	@Override
	public DichVu getDichVuTheoMa(String ma) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
