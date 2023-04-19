package bus;

import java.util.ArrayList;

import dao.Dao_KhachHang;
import entity.KhachHang;
import interfaces.I_KhachHang;

public class Bus_KhachHang implements I_KhachHang{
	private Dao_KhachHang dao_kh = new Dao_KhachHang();
	@Override
	public ArrayList<KhachHang> getAllKhachHang() {		
		return dao_kh.getAllKhachHang();
	}

	@Override
	public KhachHang getKhachHangTheoMaKH(String id) {
		return dao_kh.getKhachHangTheoMaKH(id);
	}

	@Override
	public ArrayList<KhachHang> getKhachHangTheoSDT(String sdt) {
		return dao_kh.getKhachHangTheoSDT(sdt);
	}

	@Override
	public KhachHang getKhachHangTheoCCCD(String cccd) {
		return dao_kh.getKhachHangTheoCCCD(cccd);
	}

	@Override
	public boolean them(KhachHang kh) {
		return dao_kh.them(kh);
	}

	@Override
	public boolean capNhat(KhachHang kh) {
		return dao_kh.capNhat(kh);
	}

	@Override
	public boolean xoa(String idKH) {
		return dao_kh.xoa(idKH);
	}

}
