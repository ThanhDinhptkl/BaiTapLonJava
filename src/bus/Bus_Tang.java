package bus;

import java.util.ArrayList;

import dao.Dao_Tang;
import entity.Tang;
import interfaces.I_Tang;

public class Bus_Tang implements I_Tang {
	private Dao_Tang dao_tang = new Dao_Tang();

	@Override
	public ArrayList<Tang> getAllTang() {
		return dao_tang.getAllTang();
	}

	@Override
	public Tang getTangTheoMaTang(int id) {
		return dao_tang.getTangTheoMaTang(id);
	}

	@Override
	public boolean them(Tang t) {
		return dao_tang.them(t);
	}

	@Override
	public boolean capNhat(Tang t) {
		return dao_tang.capNhat(t);
	}

	@Override
	public boolean xoa(int id) {
		return dao_tang.xoa(id);
	}

}
