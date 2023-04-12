package bus;

import java.util.ArrayList;

import dao.Dao_TaiKhoan;
import entity.TaiKhoan;
import interfaces.I_TaiKhoan;

public class Bus_TaiKhoan implements I_TaiKhoan{
	private Dao_TaiKhoan dao_tk = new Dao_TaiKhoan();
	
	@Override
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		// TODO Auto-generated method stub
		return dao_tk.getAllTaiKhoan();
	}

	@Override
	public TaiKhoan getTheoMaTK(String maTK) {
		// TODO Auto-generated method stub
		return dao_tk.getTheoMaTK(maTK);
	}

	@Override
	public ArrayList<TaiKhoan> getTheoMaNV(String maNV) {
		// TODO Auto-generated method stub
		return dao_tk.getTheoMaNV(maNV);
	}

	@Override
	public boolean them(TaiKhoan tk) {
		// TODO Auto-generated method stub
		return dao_tk.them(tk);
	}

	@Override
	public boolean capNhat(TaiKhoan tk) {
		// TODO Auto-generated method stub
		return dao_tk.capNhat(tk);
	}

	@Override
	public boolean xoa(String maTK) {
		// TODO Auto-generated method stub
		return dao_tk.xoa(maTK);
	}

}
