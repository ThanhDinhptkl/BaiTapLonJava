package bus;

import java.util.ArrayList;

import dao.Dao_TaiKhoan;
import entity.TaiKhoan;
import interfaces.I_TaiKhoan;

public class Bus_TaiKhoan implements I_TaiKhoan{
	private Dao_TaiKhoan dao_tk = new Dao_TaiKhoan();
	
	@Override
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		return dao_tk.getAllTaiKhoan();
	}

	@Override
	public TaiKhoan getTheoMaTK(String maTK) {
		return dao_tk.getTheoMaTK(maTK);
	}

	@Override
	public boolean them(TaiKhoan tk) {
		return dao_tk.them(tk);
	}

	@Override
	public boolean capNhat(TaiKhoan tk) {
		return dao_tk.capNhat(tk);
	}

	@Override
	public boolean xoa(String maTK) {
		return dao_tk.xoa(maTK);
	}

}
